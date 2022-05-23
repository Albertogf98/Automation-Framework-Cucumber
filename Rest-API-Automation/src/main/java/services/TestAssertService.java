package services;

import managers.ScenarioManager;
import org.testng.Assert;

/**
 * This class contains the functions for asserting the actions of a test.
 */

public class TestAssertService {

    public TestAssertService() { }

    public static void checkTrue(boolean condition, String message) {
        if (message != null)
            ScenarioManager.writeLogInfo("<b> " + message + " </b>");

        Assert.assertTrue(condition);
    }

    public static void checkEquals(Object current, Object expected, String message) {
        String info = " -> <b> current value " + current + " and expected value " + expected + " </b>";
        if (message != null)
            ScenarioManager.writeLogInfo(message + info);

        Assert.assertEquals(current, expected);
    }
}
