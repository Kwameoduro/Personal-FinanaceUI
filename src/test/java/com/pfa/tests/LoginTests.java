package com.pfa.tests;

import com.pfa.base.BaseTest;
import com.pfa.model.User;
import com.pfa.pages.DashboardPage;
import com.pfa.pages.LoginPage;
import com.pfa.utils.TestData;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Authentication")
@Feature("Login")
public class LoginTests extends BaseTest {

    @Test
    @Story("Unsuccessful Login with Invalid Credentials")
    @DisplayName("Invalid login should show an error")
    @Description("Attempt login with incorrect credentials and verify error message is displayed")
    public void testInvalidLoginShowsError() {
        User user = TestData.invalidUser();
        LoginPage loginPage = new LoginPage();

        loginPage.loginAs(user.getEmail(), user.getPassword());

        assertTrue(loginPage.isErrorDisplayed(), "Expected error message to be displayed.");
        log.info("Error text displayed: {}", loginPage.getErrorText());
    }

    @Test
    @Story("Successful Login")
    @DisplayName("Valid login redirects to dashboard")
    @Description("Login with valid user and confirm user is redirected to dashboard")
    @Severity(SeverityLevel.NORMAL)
    public void testValidLoginRedirectsToDashboard() {
        User user = TestData.validUser();
        LoginPage loginPage = new LoginPage();

        loginPage.loginAs(user.getEmail(), user.getPassword());

        DashboardPage dashboardPage = new DashboardPage();
        assertTrue(dashboardPage.isVisible(), "Expected to land on dashboard after login.");
        log.info("Successfully landed on dashboard.");
    }

    @Test
    @DisplayName("User can logout from profile dropdown")
    @Description("Verify user logs out via profile dropdown and returns to login page")
    public void testLogoutFromProfileDropdown() {
        User user = TestData.validUser();
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs(user.getEmail(), user.getPassword());

        DashboardPage dashboard = new DashboardPage();
        assertTrue(dashboard.isVisible(), "Dashboard should be visible after login.");

        dashboard.clickLogoutThroughProfile();

        assertTrue(loginPage.isAtLoginPage(), "User should be on login page after logout.");
        log.info("User successfully logged out and returned to login page.");
    }
}
