package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuBar extends BasePage {

    private static final By quickTransferLink = By.cssSelector("#quick_btn");

    public MenuBar(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickQuickTransferLink() {
        click(quickTransferLink);
    }
}
