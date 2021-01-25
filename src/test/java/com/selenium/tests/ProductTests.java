package com.selenium.tests;

import com.selenium.base.TestUtil;
import com.selenium.pages.LoginPage;
import com.selenium.pages.ProductListerPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTests extends TestUtil {

    @Test
    public void executeProductTest(){

        LoginPage loginPage = new LoginPage(driver);
        ProductListerPage productListerPage = loginPage.login("standard_user", "secret_sauce");

        productListerPage.addToCart("Sauce Labs Backpack");

        //Hard Assert
        Assert.assertTrue(productListerPage.isProductPriceCorrect("Sauce Labs Backpack", "$29.99"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productListerPage.isProductPriceCorrect("Sauce Labs Backpack", "$29.99"));
//        softAssert.assertTrue(productListerPage.isProductPriceCorrect("Sauce Labs Bike Light", "$222"));
        softAssert.assertFalse(productListerPage.isProductPriceCorrect("Sauce Labs Bike Light", "$29.99"));
        softAssert.assertEquals(productListerPage.getProductPrice("Sauce Labs Bike Light"), "$19.99");


        softAssert.assertAll();
            }
}
