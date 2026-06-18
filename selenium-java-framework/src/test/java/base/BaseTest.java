package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    @BeforeClass
    public static void testSetup(){
        driver.set(DriverFactory.initialiseDriver());
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    @AfterClass
    public static void tearDown(){
        getDriver().quit();
        driver.remove();
    }
}
