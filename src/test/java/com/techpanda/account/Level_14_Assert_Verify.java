package com.techpanda.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;


public class Level_14_Assert_Verify extends BaseTest {

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
    }

    @Test
    public void TC_01_Login_Empty_Email_And_Password() {
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("");
        loginPage.inputToPasswordTextbox("");
        loginPage.clickToLoginButton();

        verifyEquals(loginPage.getEmailAddressEmptyErrorMessage(), "This is a required field.");
        verifyEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
    }

    @Test
    public void TC_02_Login_Invalid_Email() {
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("123@456.789");
        loginPage.inputToPasswordTextbox("123456");
        loginPage.clickToLoginButton();

        verifyEquals(loginPage.getEmailAddressInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test(description = "Email not exist in application")
    public void TC_03_Login_Incorrect_Email() {
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox("123456");
        loginPage.clickToLoginButton();

        verifyEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }

    @Test(description = "Password less than 6 characters")
    public void TC_04_Login_Invalid_Password() {
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox("123");
        loginPage.clickToLoginButton();

        verifyEquals(loginPage.getPassWordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_05_Login_Incorrect_Password() {
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox((String.valueOf(getRandomNumber())));
        loginPage.clickToLoginButton();

        verifyEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }

    @Test
    public void TC_06_Login_Valid_Email_And_Password() {
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("automation@gmail.com");
        loginPage.inputToPasswordTextbox("123456");

        myDashboardPage = loginPage.clickToLoginButton();
        verifyTrue(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        verifyTrue(myDashboardPage.isContactInforDisplayed("automation@gmail.com"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    MyDashboardPageObject myDashboardPage;
    AccountInformationPageObject accountInformationPageObject;

}