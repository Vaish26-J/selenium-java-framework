package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    @BeforeClass
    public void testSetup(){
        driver = DriverFactory.initialiseDriver();
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
