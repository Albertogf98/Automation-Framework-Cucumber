package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.ScenarioManager;

public class Hooks {
    @Before
    public void setUp(Scenario scenario) {
        ScenarioManager.setScenario(scenario);
    }

    @After
    public void tearDown() { }
}
