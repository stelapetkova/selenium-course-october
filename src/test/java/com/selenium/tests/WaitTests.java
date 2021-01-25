package com.selenium.tests;

import com.opencsv.exceptions.CsvException;
import com.selenium.utils.CsvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WaitTests {

    WebDriver driver = null;

    @DataProvider(name = "login-data")
    public static Object[][] dataProviderHardcodedData(){
        return new Object[][]{
                {"user1","pass1"},
                {"user2","pass2"},
                {"user3","pass3"},
        };
    }

    @DataProvider(name = "login-data-file")
    public static Object[][] dataProviderfromCsvFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @BeforeTest
    public void setupDriver(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void executeSimpleTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.className("btn_action"));
        loginButton.click();

        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement productlabel = driver.findElement(By.className("product_label"));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(productlabel));
        wait.until(ExpectedConditions.elementToBeClickable(productlabel));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("product_label")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.invisibilityOf(loginButton));
    }

    @AfterTest
    public void tearDownDriver(){
        driver.quit();

    }
}
