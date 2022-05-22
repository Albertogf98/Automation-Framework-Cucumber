package services;

import managers.ScenarioManager;
import org.testng.Assert;

/**
 * This class contains the functions for asserting the actions of a test.
 */

public class TestService {

    public TestService() { }

    public static void writeAnInfo(String message) {
        ScenarioManager.writeLogInfo(message);
    }

    public static void checkTrue(boolean condition, String message) {
        writeAnInfo(message);
        Assert.assertTrue(condition, message);
    }

    public static void checkEquals(String current, String expected, String message) {
        writeAnInfo(message + "current value " + current + " and expected value " + expected);
        Assert.assertEquals(current, expected, message);
    }

    public static void checkEquals(int current, int expected, String message) {
        writeAnInfo(message + " current value " + current + " and expected value " + expected);
        Assert.assertEquals(current, expected, message);
    }
}
