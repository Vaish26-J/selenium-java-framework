package tests;

import base.BaseTest;
import org.openqa.selenium.By;
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
        String text1 = login.fillField(uname, pword);
        Assert.assertEquals(text1, "tomsmith");
        wait.waitUntilVisible(login.getLogoutButton());
        Assert.assertTrue(login.isLogoutDisplayed());
        Assert.assertTrue(login.getSuccessFlash().contains("You logged into a secure area!"));
        logger.info("Successfully Logged into Application");
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

    @Test
    public void scrollTest() throws InterruptedException {
//        driver.get("https://automationexercise.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        WebElement ele = driver.findElement(By.id("accordian"));
        Thread.sleep(5000);
//        js.executeScript("arguments[0].scrollIntoView(true)", ele);
        Actions actions = new Actions(driver);
//        actions.scrollToElement(ele);
//        Boolean is_present = driver.findElements(By.xpath("//a[contains(@data-parent, '#accordian')]")).size()>0;
//        WebElement elementt = driver.findElement(By.xpath("//a[contains(@data-parent, '#accordian')]"));
//        Assert.assertTrue(elementt.isDisplayed());
        // Infinity scroll
//        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
//        for(int i=0; i<5; i++){
////            js.executeScript("window.scrollBy(0,1000)");
//            actions.scrollByAmount(0,1000).perform();
//            Thread.sleep(3000);
//        }
        // horizontal scroll
        driver.get("https://datatables.net/examples/basic_init/scroll_x.html");
        WebElement table = driver.findElement(By.className("dt-scroll-body"));
        WebElement footer = driver.findElement(By.className("dt-info"));
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(7000);
        js.executeScript("arguments[0].scrollLeft += 1000;", table);
    }
}
