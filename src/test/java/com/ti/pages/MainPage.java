package com.ti.pages;

import com.ti.base.DriverFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class MainPage {
    WebDriver driver = DriverFactory.getInstance().getDriver();

    @FindBy(className = "wpsp-preLoading")
    private WebElement dvPreloading;

    @FindBy(className = "wpsp-schoolname")
    private WebElement spnTI;

    @FindBy(xpath = "//*[text()=' Create New']")
    private WebElement btnCreateNew;

    @FindBy(id = "d_teacher")
    private List<WebElement> icnDelete;

    @FindBy(xpath = "//a[contains(text(),'Ok')]")
    private WebElement btnOk;

    protected MainPage(){
        PageFactory.initElements(driver, this);
    }

    public MainPage preLoading(){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOf(dvPreloading));
        }catch (TimeoutException te){
            driver.navigate().refresh();
            preLoading();
        }
        return this;
    }

    private String getSchoolName(){
        return spnTI.getText();
    }

    public MainPage verySchoolName(){
        Assert.assertEquals(getSchoolName(),"Titanium School");
        return this;
    }

    public MainPage andCreateNew(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(btnCreateNew));
        btnCreateNew.click();
        preLoading();
        return this;
    }

    public MainPage deleteRow(){
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfAllElements(icnDelete));
        //driver.findElements(""); --> Lista de web elements
        WebElement delete = icnDelete.get(icnDelete.size()-1);
        delete.click();
        return this;
    }

    public MainPage confirmationWindow(){
        new WebDriverWait(driver,Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(btnOk));
        btnOk.click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.invisibilityOf(btnOk));
        return this;
    }
}
