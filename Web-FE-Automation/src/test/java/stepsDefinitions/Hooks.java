package stepsDefinitions;

import bases.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.ScenarioManager;

/**
 * This class contains the initial and final action of the tests.
 * */

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        ScenarioManager.setScenario(scenario);
        TestBase.createDriver();
    }

    @After
    public void tearDown() {
        TestBase.closeDriver();
        TestBase.quitDriver();
    }
}
