package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UtilitiesTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(UtilitiesTest.class);
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = getDriver();
    }

    @Test
    public void infinityScroll() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0; i<5; i++){
            js.executeScript("window.scrollBy(0,1000);");
            Thread.sleep(3000);
        }
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.google.com"));
    }
}
