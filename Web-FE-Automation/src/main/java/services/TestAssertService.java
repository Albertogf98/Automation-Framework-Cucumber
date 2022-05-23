package services;

import managers.ScenarioManager;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * This class contains the functions for asserting the actions of a test.
 */

public class TestAssertService {

    public TestAssertService() { }

    public static boolean isPresent(WebElement element) {
        return element != null;
    }

    public static void checkTrue(boolean condition, String message) {
        if (message != null)
            ScenarioManager.writeLogInfo(message);

        Assert.assertTrue(condition);
    }

    public static void checkEquals(String current, String expected, String message) {
        ScenarioManager.writeLogInfo(message + " -> current value " + current + " and expected value " + expected);
        Assert.assertEquals(current, expected);
    }

    public static void checkEqualsByAttribute(WebElement element, String expected, String message) {
        String info = " -> current value " + element.getAttribute("value") + " and expected value " + expected;
        ScenarioManager.writeLogInfo(message + info);
        Assert.assertEquals(element.getAttribute("value"), expected);
    }
}
