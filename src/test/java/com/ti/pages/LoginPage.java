package com.ti.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends MainPage{

    @FindBy(id = "user_login")
    private WebElement txtUser;

    @FindBy(name = "pwd")
    private WebElement txtPassword;

    @FindBy(css = "#rememberme")
    private WebElement chkRememberMe;

    @FindBy(xpath = "//input[contains(@value, 'Log')]")
    private WebElement btnLogin;

    public LoginPage loginAs(String userName){
        txtUser.clear();
        txtUser.sendKeys(userName);
        return this;
    }

    public LoginPage withPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public LoginPage andRememberMe(boolean checked){
        if (checked){
            chkRememberMe.click();
        }
        return this;
    }

    public LoginPage login(){
        btnLogin.click();
        preLoading();
        return this;
    }
}
