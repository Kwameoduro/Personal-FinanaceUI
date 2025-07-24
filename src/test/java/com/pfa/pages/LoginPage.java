package com.pfa.pages;

import com.pfa.base.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginForm = By.xpath("//form");

    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By errorMessage = By.xpath("//div[contains(@class,'global-error-message') and contains(.,'Invalid email or password')]");
    private final By dashboardMarker = By.cssSelector("app-dashboard, .dashboard");

    private final By emailError = By.xpath("//input[@id='email']/following-sibling::small");
    private final By passwordError = By.xpath("//input[@id='password']/following-sibling::small");

    public LoginPage() {
        this.driver = WebDriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    public void loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorDisplayed() {
        return !driver.findElements(errorMessage).isEmpty();
    }

    public String getErrorText() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isDashboardVisible() {
        return !driver.findElements(dashboardMarker).isEmpty();
    }

    public boolean isLoginButtonEnabled() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        return button.isEnabled();
    }

    public String getEmailErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(emailError));
            return error.getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public String getPasswordErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
            return error.getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public boolean isAtLoginPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
