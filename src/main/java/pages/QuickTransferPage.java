package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuickTransferPage extends BasePage {

    private static final By transferReceivers =  By.cssSelector("select option");
    private static final By amountInput = By.cssSelector("#widget_1_transfer_amount");
    private static final By titleInput = By.cssSelector("#widget_1_transfer_title");
    private static final By executeButton = By.cssSelector("#execute_btn");

    public QuickTransferPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void transferFunds(String receiver, String amount, String title) {
        click(By.cssSelector("#uniform-widget_1_transfer_receiver"));
        select(receiver, transferReceivers);
        type(amount, amountInput);
        type(title, titleInput);
        click(executeButton);
    }

    public String getDialogConfirmationText() {
        return getElementLocatedBy(By.cssSelector(".ui-dialog .ui-dialog-content")).getAttribute("innerText").replace("\n", " ");
    }
}
