package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ElementUtils;
import utils.WaitUtils;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void goToSite(){
        driver.get(ConfigReader.getProperties("url"));
        System.out.println("title:"+ driver.getTitle());
        System.out.println(
                "Opening login page"
        );
    }

    public String fillField(String un, String pw){
        System.out.println(
                "Entering Credentials"
        );
        WebElement username = getUserName();
        WebElement password = getPassword(driver);
        WebElement button = getButton();
        ElementUtils.TypeText(username, un);
        ElementUtils.TypeText(password,pw);
        String value = getText(username);
        System.out.println(
                "Clicking login button"
        );
        ElementUtils.ClickElement(button);
        return value;
    }

    public WebElement getUserName(){
        WaitUtils wait = new WaitUtils(driver);
        return wait.waitUntilVisible(By.name("username"));
    }

    public WebElement getPassword(WebDriver driver){
        return driver.findElement(By.id("password"));
    }

    public WebElement getButton(){
        return driver.findElement(By.cssSelector("button[type='submit']"));
    }

    public String getText(WebElement element){
        String text = element.getAttribute("value");
        return text;
    }

    public By getLogoutButton(){
        By logout = By.cssSelector("a[href='/logout']");
        return logout;
    }

    public Boolean isLogoutDisplayed(){
        return driver.findElements(getLogoutButton()).size() > 0;
    }

    public String getSuccessFlash(){
        return driver.findElement(By.id("flash")).getText();
    }
}
