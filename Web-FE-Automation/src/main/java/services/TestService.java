package services;

import managers.ScenarioManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

/**
 * This class contains the functions for asserting the actions of a test.
 */

public class TestService {

    public TestService() { }

    public static boolean isPresent(WebElement element) {
        return element != null;
    }

    public static void writeAnInfo(String message) {
        ScenarioManager.writeLogInfo(message);
    }

    public static void checkTrue(boolean condition, String message) {
        writeAnInfo(message);
        Assert.assertTrue(condition, message);
    }

    public static void clickWithActions(WebDriver driver, WebElement element, String message) {
        Actions action = new Actions(driver);
        action.click(element)
                .build()
                .perform();

        writeAnInfo("Click " + message);
    }

    public static void checkEquals(String current, String expected, String message) {
        writeAnInfo(message);
        Assert.assertEquals(current, expected, message);
    }

    public static void doScroll(WebDriver driver, boolean scollDown, int pixels) {
        String script = scollDown ? "scroll(0, "+pixels+");": "scroll(0, -"+pixels+");";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(script);
    }

    public static void setInput(WebElement element, String value, String message, boolean clearInput) {
        if (clearInput)
            element.clear();

        element.sendKeys(value);

        if (clearInput)
            element.sendKeys(Keys.RETURN);

       writeAnInfo(message);
    }
}
