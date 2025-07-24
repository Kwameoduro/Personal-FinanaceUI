package com.pfa.base;

import com.pfa.utils.ConfigReader;
import com.pfa.utils.LoggerUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public abstract class BaseTest {

    protected WebDriver driver;
    protected final Logger log = LoggerUtil.getLogger(this.getClass());

    @BeforeEach
    public void setUp() {
        String browser = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        int implicitWait = Integer.parseInt(ConfigReader.get("implicit.wait"));
        String baseUrl = ConfigReader.get("base.url");

        log.info("Setting up WebDriver with browser: {}, headless: {}", browser, headless);
        WebDriverFactory.setDriver(browser, headless, implicitWait);
        driver = WebDriverFactory.getDriver();

        log.info("Navigating to {}", baseUrl);
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        log.info("Quitting WebDriver...");
        WebDriverFactory.quitDriver();
    }
}