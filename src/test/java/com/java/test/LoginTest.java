package com.java.test;

import com.aventstack.extentreports.Status;
import com.java.pages.BasePage;
import com.java.util.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import com.java.pages.LoginPage;
import com.java.util.ExcelUtil;
import java.io.IOException;

public class LoginTest extends BasePage {

    public WebDriver driver;
    Logger log = Logger.getLogger(LoginTest.class);

    @BeforeMethod
    public void initialize() throws IOException {
        log.info("Initializing Web Driver");
        driver = initializeDriver();
    }

    @Test(dataProvider = "sendData")
    public void loginPageNavigation(String Username,String Password) throws IOException
    {
        ExtentTestManager.getTest().log(Status.INFO, "Running Excel Data based test");
        log.info("Getting url from Data.properties file");
        driver.navigate().to(properties.getProperty("url"));
        LoginPage loginPage = new LoginPage(driver);
        log.info("going to login page");
        loginPage.getLogin();
        ExtentTestManager.getTest().log(Status.INFO, "Username: "+Username);
        loginPage.getEmail().sendKeys(Username);
        ExtentTestManager.getTest().log(Status.INFO, "Password: "+Password);
        loginPage.getPassword().sendKeys(Password);
        log.info("Clicking on Log In button");
        loginPage.getLogin().click();
//		Assert.assertTrue(loginPage.getNavigationLabel().isDisplayed());
    }

    @DataProvider
    public Object[][] sendData()
    {
        Object[][] data = ExcelUtil.getTestData("login");
        return data;
    }

    @AfterMethod
    public void teardown()
    {
        driver.quit();
    }
}
