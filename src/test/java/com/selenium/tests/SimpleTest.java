package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SimpleTest {
    WebDriver driver = null;

    @BeforeTest
    public void setupDriver(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void executeSimpleTest() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");

        Thread.sleep(3000);

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.className("btn_action"));
        loginButton.click();

        Thread.sleep(3000);

        Select productOrderList = new Select(driver.findElement(By.className("product_sort_container")));
        productOrderList.selectByValue("lohi");
        productOrderList.selectByVisibleText("Name (A to Z)");

        WebElement addToCart=driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']//button"));
        addToCart.click();

        WebElement cartItem = driver.findElement(By.xpath("//a[contains(@class,'shopping_cart_link')]"));
        cartItem.click();

        WebElement productInCart = driver.findElement(By.className("inventory_item_name"));
        String productName = productInCart.getText();

        Assert.assertEquals(productName, "Sauce Labs Backpack");
    }

    @AfterTest
    public void tearDownDriver(){
        driver.quit();
    }
}
