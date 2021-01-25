package com.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final String IGNORE_CERTIFICATE = "--ignore-certificate-errors";
    public static WebDriver getFirefoxDriver(int implicitWait){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(IGNORE_CERTIFICATE);

        WebDriverManager.getInstance(FirefoxDriver.class).setup();
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

        return driver;
    }
}
