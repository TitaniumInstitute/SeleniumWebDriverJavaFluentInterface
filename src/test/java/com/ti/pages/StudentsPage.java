package com.ti.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class StudentsPage extends MainPage{

    @FindBy(linkText = "Students")
    private WebElement navStudents;

    @FindBy(name = "s_gender")
    private List<WebElement> rdnGender;

    @FindBy(id = "firstname")
    private WebElement txtFirstName;

    @FindBy(id = "lastname")
    private WebElement txtLastName;

    @FindBy(id = "Dob")
    private WebElement dtpDateOfBirth;

    @FindBy(xpath = "//td[contains(@class,'day')]")
    private List<WebElement> tdSelectDay;

    @FindBy(id = "current_address")
    private WebElement txtCurrentAddress;

    @FindBy(id = "Email")
    private WebElement txtEmailAddress;

    @FindBy(name ="Username" )
    private WebElement txtUserName;

    @FindBy(name = "Password")
    private WebElement txtPassword;

    @FindBy(id = "ConfirmPassword")
    private WebElement txtConfirmPass;

    @FindBy(id = "Rollno")
    private WebElement txtRollNumber;

    @FindBy(xpath = "//tr[contains(@role,'row')]")
    private List<WebElement> trStudentsRows;

    public StudentsPage goToStudents(){
        navStudents.click();
        preLoading();
        return this;
    }

    public StudentsPage genderAs(String gender){
        for (WebElement optGender:rdnGender) {
            if (optGender.getAttribute("value").equals(gender)){
                optGender.click();
                break;
            }
        }
        return this;
    }

    public StudentsPage withFirstName(String firstName){
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);
        return this;
    }

    public StudentsPage withLastName(String lastName){
        txtLastName.clear();
        txtLastName.sendKeys(lastName);
        return this;
    }

    public StudentsPage withDayOfBirth(String selectedDay){
        dtpDateOfBirth.click();

        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfAllElements(tdSelectDay));
        for (WebElement day:tdSelectDay) {
            if (day.getText().equals(selectedDay)){
                day.click();
                break;
            }
        }
        return this;
    }

    public StudentsPage andCurrentAddress(String currentAddress){
        txtCurrentAddress.clear();
        txtCurrentAddress.sendKeys(currentAddress);
        return this;
    }

    public StudentsPage userEmail(String email){
        txtEmailAddress.clear();
        txtEmailAddress.sendKeys(email);
        return this;
    }

    public StudentsPage withUserName(String userName){
        txtUserName.clear();
        txtUserName.sendKeys(userName);
        return this;
    }

    public StudentsPage withPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public StudentsPage andConfirmPassword(String confPass){
        txtConfirmPass.clear();
        txtConfirmPass.sendKeys(confPass);
        return this;
    }

    public StudentsPage schoolDetails(String rollNumber){
        txtRollNumber.clear();
        txtRollNumber.sendKeys(rollNumber);
        txtRollNumber.submit();
        preLoading();
        return this;
    }

    public StudentsPage validateStudentIsAdded(String name){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(trStudentsRows));
        }catch (TimeoutException te){
            preLoading();
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(trStudentsRows));
        }

        WebElement newStudentRow = trStudentsRows.get(trStudentsRows.size()-1);
        Assert.assertTrue(newStudentRow.getText().contains(name));
        return this;
    }

    public StudentsPage deleteStudent(){
        deleteRow();
        confirmationWindow();
        return this;
    }
}
