package managers;

import io.cucumber.java.Scenario;

/**
 * This class contains the functions of the scenarios
* */

public class ScenarioManager {

    private static Scenario scenario;

    public ScenarioManager() { }

    public static Scenario getScenario() {
        return  ScenarioManager.scenario;
    }

    public static void setScenario(Scenario scenario) {
        ScenarioManager.scenario = scenario;
    }

    public static void writeLogInfo(String info) {
        scenario.log(info);
    }
}
