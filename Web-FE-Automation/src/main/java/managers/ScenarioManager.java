package managers;

import bases.TestBase;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * This class contains the functions of the scenarios
* */

public class ScenarioManager {

    private static Scenario scenario;

    public ScenarioManager() { }

    public static void setScenario(Scenario scenario) {
        ScenarioManager.scenario = scenario;
    }

    public static void writeLogInfo(String info) {
        scenario.log(info);
    }

    public static void addScreenshot(String imgName) {
        byte[] screenshotAs = ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshotAs, "image/png", imgName);
    }

    public static void addScreenshot() {
        addScreenshot("Image");
    }
}
