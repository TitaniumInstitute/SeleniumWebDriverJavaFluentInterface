package com.ti.testcases;

import com.ti.base.BrowserType;
import com.ti.base.DriverFactory;
import com.ti.pages.LoginPage;
import com.ti.pages.StudentsPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    Map<String, String> userCredentials = new HashMap<>();
    Map<String,String> studentAccountInfo = new HashMap<>();
    String[] studentPersonalDetails = {"Female", "TestStudent", "StudentLastName", "14", "TestAddress"};
    LoginPage loginPage;
    StudentsPage studentsPage;

    @BeforeTest
    @Parameters("browser")
    void setup(String browser){
        //Arrange
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        DriverFactory.getInstance().getDriver().navigate().to(baseURL);

        userCredentials.put("username", "admin");
        userCredentials.put("userpass","G3-ySzY%");

        studentAccountInfo.put("email","test@email.com");
        studentAccountInfo.put("user", "testuser");
        studentAccountInfo.put("password", "test123");

        loginPage = new LoginPage();
        studentsPage = new StudentsPage();
    }

    @AfterTest
    void turnDown(){
        studentsPage.deleteStudent();
        DriverFactory.getInstance().removeDriver();
    }
}
