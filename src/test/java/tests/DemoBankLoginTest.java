package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import steps.SharedSteps;
import utils.ScreenshotUtils;
import utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoBankLoginTest extends BaseTest {

    @Test
    void loginTestPositive(TestInfo testInfo) {
        try {
            SharedSteps.login(loginPage, TestUtils.generateRandomString(8), TestUtils.generateRandomString(8));
            assertLoginPositive();
        } catch (Throwable t) {
            ScreenshotUtils.captureScreenshot(getDriver(), testInfo.getDisplayName());
            throw t;
        }
    }

    @Test
    void loginTestNegative(TestInfo testInfo) {
        try {
            SharedSteps.login(loginPage, TestUtils.generateRandomString(8), "");
            assertLoginNegative();
        } catch (Throwable t) {
            ScreenshotUtils.captureScreenshot(getDriver(), testInfo.getDisplayName());
            throw t;
        }
    }

    @Step("Assert positive login")
    void assertLoginPositive() {
        assertTrue(homePage.getLogoutButton().isDisplayed());

    }

    @Step("Assert invalid login")
    void assertLoginNegative() {
        assertTrue(loginPage.getLoginButton().isDisplayed());
    }

}
