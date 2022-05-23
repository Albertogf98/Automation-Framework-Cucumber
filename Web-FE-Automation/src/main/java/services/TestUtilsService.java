package services;

import managers.ScenarioManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * This class contains the functions such as clicking, scrolling...
 * */
public class TestUtilsService {

    public TestUtilsService() { }

    public static void doScroll(WebDriver driver, boolean scollDown, int pixels) {
        String script = scollDown ? "scroll(0, "+pixels+");" : "scroll(0, -"+pixels+");";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(script);
    }

    public static void setInput(WebElement element, String value, String message, boolean clearInput) {
        if (clearInput)
            element.clear();

        element.sendKeys(value);

        if (clearInput)
            element.sendKeys(Keys.RETURN);

        ScenarioManager.writeLogInfo(message + " with data: <b> " + value + "</b>");
    }

    public static void clickWithActions(WebDriver driver, WebElement element, String message) {
        Actions action = new Actions(driver);
        action.click(element)
                .build()
                .perform();

        ScenarioManager.writeLogInfo("Click " + message);
    }
}
