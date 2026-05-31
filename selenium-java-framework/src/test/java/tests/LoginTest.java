package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtils;
import utils.WaitUtils;
//import org.testng.annotations.Listeners;
import java.time.Duration;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {
    private LoginPage login;
    private WaitUtils wait;
    private String uname = ConfigReader.getProperties("username");
    private String pword = ConfigReader.getProperties("password");

    @BeforeClass
    public void setup(){
        login = new LoginPage(driver);
        wait = new WaitUtils(driver);
    }

    @BeforeMethod
    public void navigateBase(){
        login.goToSite();
    }

    @Test
    public void loginTest() {
        String text1 = login.fillField("uname", pword);
        Assert.assertEquals(text1, "tomsmith");
        wait.waitUntilVisible(login.getLogoutButton());
        Assert.assertTrue(login.isLogoutDisplayed());
        Assert.assertTrue(login.getSuccessFlash().contains("You logged into a secure area!"));
        System.out.println(
                "Successfully logged into application"
        );
    }

    @Test
    public void invalidUsername() {
        login.fillField("tomsmith", "SuperSecretPassword!");
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
