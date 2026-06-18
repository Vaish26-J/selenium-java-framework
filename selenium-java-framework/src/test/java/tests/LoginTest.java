package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;


@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {
    private LoginPage login;
    private WaitUtils wait;
    private String uname = ConfigReader.getProperties("username");
    private String pword = ConfigReader.getProperties("password");
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = getDriver();
        login = new LoginPage(driver);
        wait = new WaitUtils(driver);
    }

    @BeforeMethod
    public void navigateBase(){
        login.goToSite();
        logger.info(
                "Thread ID: " +
                        Thread.currentThread().getId()
        );
    }

    @Test
    public void loginTest() {
        String text1 = login.fillField(uname, pword);
        Assert.assertEquals(text1, "tomsmith");
        wait.waitUntilVisible(login.getLogoutButton());
        Assert.assertTrue(login.isLogoutDisplayed());
        Assert.assertTrue(login.getSuccessFlash().contains("You logged into a secure area!"));
        logger.info("Successfully Logged into Application");
    }

    @Test
    public void invalidUsername() {
        login.fillField("tomsmsith", "SuperSecretPassword!");
        Assert.assertFalse(login.isLogoutDisplayed(), "Login Error");
    }

    @Test
    public void invalidPassword() throws InterruptedException {
        login.fillField("tomsmith", "SuperSecretPasswords!");
        Assert.assertFalse(login.isLogoutDisplayed(), "Login error");
    }

    @Test
    public void emptyValuesValidation() throws InterruptedException {
        login.fillField("", "");
        Assert.assertFalse(login.isLogoutDisplayed(), "Login error");
    }
}
