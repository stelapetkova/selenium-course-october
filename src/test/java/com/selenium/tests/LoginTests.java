package com.selenium.tests;

import com.opencsv.exceptions.CsvException;
import com.selenium.base.TestUtil;
import com.selenium.utils.CsvReader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends TestUtil {

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

    @Test(dataProvider = "login-data-file")
    public void executeSimpleTest(String user, String pass) throws InterruptedException {

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("user");

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("pass");

        WebElement loginButton = driver.findElement(By.className("btn_action"));
//        loginButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", loginButton);
        js.executeScript("arguments[0].click();", loginButton);

    }
}
