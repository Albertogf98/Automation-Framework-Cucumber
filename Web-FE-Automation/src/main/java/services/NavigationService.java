package services;

import org.openqa.selenium.WebDriver;

/**
 * This class has the navigation functions.
 */

public class NavigationService {

    public NavigationService() { }

    public static void goTo(String url, WebDriver driver) {
        driver.get(url);
    }
}
