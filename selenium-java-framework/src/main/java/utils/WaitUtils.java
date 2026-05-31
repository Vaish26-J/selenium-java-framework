package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private WebDriver driver;
    private WebDriverWait wait;
    public WaitUtils(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitUntilVisible(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilVisible(By locator, int duration){
        wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
