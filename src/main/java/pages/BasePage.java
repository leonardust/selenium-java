package pages;

import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Log
public class BasePage {

    private final WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private WebDriverWait getWait(Long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement getElementLocatedBy(By locator) {
        getWait(5L).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public List<WebElement> getElementsLocatedBy(By locator) {
        getWait(5L).until(ExpectedConditions.visibilityOfAllElementsLocatedBy((locator)));
        return driver.findElements(locator);
    }

    public void click(By locator) {
        getElementLocatedBy(locator).click();
        log.info("Click element " + locator);
    }

    public void type(String text, By locator) {
        getElementLocatedBy(locator).sendKeys(text);
        log.info("Type " + text + " into element " + locator);
    }

    public void select(String value, By locator) {
        List<WebElement> options = getElementsLocatedBy(locator);
        for (WebElement option : options) {
            if (value.equals(option.getText())) {
                option.click();
                log.info("Select " + value + " from element " + locator);
            }
        }
    }
}
