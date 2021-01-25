package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id="user-name")
    private WebElement username;

    @FindBy(xpath="//input[@placeholder='Password']")
    private WebElement password;

    @FindBy(className="btn_action")
    private WebElement loginButton;

    public LoginPage (WebDriver driver){
       super(driver);
    }

    public ProductListerPage login(String usernameField, String passwordField){
        username.sendKeys(usernameField);
        password.sendKeys(passwordField);
        loginButton.click();
        return new ProductListerPage(driver);
    }

}
