package com.pfa.pages;

import com.pfa.base.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dashboardMarker = By.xpath("//*[contains(text(),'Welcome')]");
    private final By profileDropdown = By.cssSelector("div.user-info");
    private final By logoutOption = By.xpath("//div[contains(@class, 'dropdown-item') and normalize-space()='Logout']");

    public DashboardPage() {
        this.driver = WebDriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardMarker));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogoutThroughProfile() {
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(profileDropdown));
        profile.click();

        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutOption));
        Actions actions = new Actions(driver);
        actions.moveToElement(logout).click().perform();
    }
}
