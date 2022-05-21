package bases;

import com.microsoft.edge.seleniumtools.EdgeOptions;
import managers.ScenarioManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import readers.PropertiesReader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class contains the functions to start the automation
 */

public class TestBase {

    private final static String ARGUMENT_IGNORE_CERTIFICATE_ERROR       = "--ignore-certificate-errors";
    private final static String ARGUMENT_IGNORE_SSL_ERROR               = "--ignore-ssl-errors";
    private final static String ARGUMENT_LANG_EN_US                     = "--lang=en-US";
    private final static String ARGUMENT_DISABLE_EXTENSION_BLACKLIST    = "--safebrowsing-disable-extension-blacklist";
    private final static String HEADLESS_MODE                           = "headless";

    private final static String SELENIUM_MODE                           = PropertiesReader.getProperty("SELENIUM_MODE");
    private final static String SELENIUM_URL                            = PropertiesReader.getProperty("SELENIUM_URL");

    private static WebDriver driver;

    public TestBase() { }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }

    public static void closeDriver() {
        driver.close();
    }

    private static WebDriver initChromeDriver() {
        WebDriver webDriver = null;

        ChromeOptions chromeOptions = new ChromeOptions().addArguments();
        chromeOptions.addArguments(
                ARGUMENT_IGNORE_CERTIFICATE_ERROR,
                ARGUMENT_IGNORE_SSL_ERROR,
                ARGUMENT_LANG_EN_US,
                ARGUMENT_DISABLE_EXTENSION_BLACKLIST
        );
        chromeOptions.setHeadless(HEADLESS_MODE.equalsIgnoreCase(SELENIUM_MODE));
        chromeOptions.merge(DesiredCapabilities.chrome());

        try {
             webDriver = new RemoteWebDriver(new URL(SELENIUM_URL), chromeOptions);
        } catch (MalformedURLException e) {
            ScenarioManager.writeLogInfo("Exception thrown  " + e.getMessage());
        }

        return webDriver;
    }

    private static WebDriver initEdgeDriver() {
        WebDriver webDriver = null;

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("UserChromium", true);
        edgeOptions.setCapability("user-agent", "SiteBot");
        edgeOptions.setHeadless(HEADLESS_MODE.equalsIgnoreCase(SELENIUM_MODE));

        try {
            webDriver = new RemoteWebDriver(new URL(SELENIUM_URL), edgeOptions);
        } catch (MalformedURLException e) {
            ScenarioManager.writeLogInfo("Exception thrown  " + e.getMessage());
        }

        return webDriver;
    }

    private static WebDriver initFirefoxDriver() {
        WebDriver webDriver = null;

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(
                ARGUMENT_IGNORE_CERTIFICATE_ERROR,
                ARGUMENT_IGNORE_SSL_ERROR
        );
        firefoxOptions.setHeadless(HEADLESS_MODE.equalsIgnoreCase(SELENIUM_MODE));

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("intl.accept_languages", "en-US");
        firefoxProfile.setPreference("general.useragent.override", "SiteBot");

        try {
            webDriver = new RemoteWebDriver(new URL(SELENIUM_URL), firefoxOptions);
        } catch (MalformedURLException e) {
            ScenarioManager.writeLogInfo("Exception thrown  " + e.getMessage());
        }

        return webDriver;
    }

    public static void createDriver() {
        driver = switch (PropertiesReader.getProperty("SELENIUM_BROWSER").toLowerCase()) {
            case "chrome"   -> initChromeDriver();
            case "edge"     -> initEdgeDriver();
            case "firefox"  -> initFirefoxDriver();
            default         -> null;
        };

        driver.manage().window().maximize();

        if (SELENIUM_MODE.equalsIgnoreCase("headless"))
            driver.manage().window().setSize(new Dimension(1920, 1080));
    }
}