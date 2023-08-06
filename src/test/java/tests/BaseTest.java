package tests;

import annotations.SeleniumPage;
import browserfactory.BrowserFactory;
import lombok.Getter;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuBar;
import pages.QuickTransferPage;
import services.EnvironmentReaderService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Getter
@Log
public class BaseTest {

    private WebDriver driver;
    protected WebDriverWait wait;
    @SeleniumPage
    protected LoginPage loginPage;
    @SeleniumPage
    protected HomePage homePage;
    @SeleniumPage
    protected QuickTransferPage quickTransferPage;
    @SeleniumPage
    protected MenuBar menuBar;

    @BeforeEach
    public void setUp() {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.getDriver();
        initPage(this, driver, wait);
        driver.get(EnvironmentReaderService.getProperty("url"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Driver quit");
        }
    }

    private void initPage(Object object, WebDriver driver, WebDriverWait wait) {
        Class<?> clazz = object.getClass().getSuperclass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(SeleniumPage.class)) {
                Class<?>[] type = {WebDriver.class, WebDriverWait.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(driver, wait));
                    log.info("Call constructor for selenium page with name: " + field.getName());
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                         NoSuchMethodException e) {
                    log.info("Did not manage to call constructor for selenium page with name " + field.getName());
                }
            }
        }
    }

}
