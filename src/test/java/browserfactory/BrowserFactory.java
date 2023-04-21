package browserfactory;

import lombok.extern.java.Log;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import services.EnvironmentReaderService;

@Log
public class BrowserFactory {
    private final WebDriver driver;

    public BrowserFactory() {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : EnvironmentReaderService.getProperty("browser");
        String browserMode = System.getProperty("browser.mode") != null ? System.getProperty("browser.mode") : EnvironmentReaderService.getProperty("browser.mode");

        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(browserMode);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(browserMode);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(browserMode);
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser value: " + browser);
        }

        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getBrowserVersion();
        log.info("Browser: " + browserName + " Version: " + browserVersion + " Mode: " + browserMode);
    }

    public WebDriver getDriver() {
        return driver;
    }

}