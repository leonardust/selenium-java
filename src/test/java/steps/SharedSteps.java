package steps;

import io.qameta.allure.Step;
import pages.LoginPage;

public class SharedSteps {
    @Step("Login with credentials")
    public static void login(LoginPage loginPage, String username, String password) {
        loginPage.login(username, password);
    }
}