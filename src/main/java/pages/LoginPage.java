package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private static final By usernameInputLocator = By.id("login_id");
    private static final By passwordInputLocator = By.id("login_password");
    private static final By loginButtonLocator = By.id("login-btn");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getLoginButton()  {
        return getElementLocatedBy(loginButtonLocator);
    }

    public void login(String username, String password) {
        type(username, usernameInputLocator);
        type(password, passwordInputLocator);
        click(loginButtonLocator);
    }
}
