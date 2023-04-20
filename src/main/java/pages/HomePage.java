package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private static final By logoutButton = By.cssSelector("#log_out");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getLogoutButton() {
        return getElementLocatedBy(logoutButton);
    }
}
