package services;

import org.testng.Assert;

public class TestService {

    public TestService() { }


    public static void checkTrue(boolean isTrue, String message) {
        if (!isTrue)
            Assert.fail(message + " is not true");

        else
            Assert.assertTrue(isTrue, message);
    }

    public static void checkEquals(String current, String expected, String message) {
        Assert.assertEquals(current, expected, message);
    }
}
