package utils;

import org.openqa.selenium.WebElement;

public class ElementUtils {
    public static void TypeText(WebElement element, String message){
        element.clear();
        element.sendKeys(message);
    }

    public static void ClickElement(WebElement element){
        element.click();
    }
}
