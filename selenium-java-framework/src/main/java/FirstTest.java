import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class FirstTest {


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = createDriver();
        LoginPage login = new LoginPage(driver);
        login.goToSite();
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        String text1 = login.fillField("tomsmith", "SuperSecretPassword!");
        if(text1.equals("tomsmith")){
            WebElement button = driver.findElement(By.cssSelector("Button[type='submit']"));
            button.click();
            System.out.println("Passed");
        }else{
            System.out.println("Failed");
        }
        driver.quit();
    }

    public static WebDriver createDriver(){
        return new ChromeDriver();
    }
}
