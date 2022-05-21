package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * This class contains the cucumber configuration.
 * */

@CucumberOptions(
        features = "classpath:features",
        tags = "@NavigateCategories" +
                " or " +
                "@AddLaptopToCart" +
                " or " +
                "@NavigateToCartAndDeleteProduct" +
                " or " +
                "@PlaceOrder",
        glue = "stepsDefinitions",
        monochrome = true,
        plugin = {
                "pretty",
                "json:reports/Cucumber.json",
                "junit:reports/Cucumber.xml",
                "html:reports/cucumber-reports.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests { }
