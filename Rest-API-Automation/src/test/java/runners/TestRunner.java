package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*
*
* TODO: ADD REPORTS
*
* */
@CucumberOptions(
        features = "classpath:features",
        tags = "@GetAvailablePets",
        glue = "stepsDefinitions",
        monochrome = true,
        plugin = {
                "pretty",
                "json:reports/Cucumber.json",
                "junit:reports/Cucumber.xml",
                "html:reports/cucumber-reports.html"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests { }
