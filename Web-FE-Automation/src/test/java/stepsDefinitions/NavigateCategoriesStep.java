package stepsDefinitions;

import bases.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.ScenarioManager;
import org.openqa.selenium.WebDriver;
import pages.BlazeDashboardPageObject;
import pages.Webs;
import services.NavigationService;
import services.TestService;

public class NavigateCategoriesStep {

    private WebDriver driver;

    private BlazeDashboardPageObject dashboardPage;

    public NavigateCategoriesStep() {
        driver          = TestBase.getDriver();
        dashboardPage   = new BlazeDashboardPageObject(driver);
    }
    @Given("^URL to go to blaze page without login confirmation$")
    public void urlToGoToBlazePageWithoutLoginConfirmation() {
        NavigationService.goTo(Webs.WEB_BLAZE_HOME.getUrl(), driver);
    }
    @Then("^Dashboard is displayed$")
    public void dashboardIsDisplayed() {
        dashboardPage.waitUntilInitElementsAppear();

        TestService.checkTrue(dashboardPage.isDisplayed(), "Blaze page is displayed");
    }
    @When("^Click on phone category$")
    public void clickOnPhoneCategory() {
        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilPhoneButtonAppear()),"Phone button");
        ScenarioManager.addScreenshot("Category dashboard");
        TestService.clickWithActions(driver, dashboardPage.waitUntilPhoneButtonAreClickable(), "Phone button");
    }
    @Then("^Phone category is displayed$")
    public void phoneCategoryIsDisplayed() {
        dashboardPage.waitUntilCardsTitlesAppear()
                .forEach(card -> TestService.checkTrue(TestService.isPresent(card), "Card " + card.getText()));
    }

    @When("^Click on Laptops category$")
    public void clickOnLaptopsCategory() {
        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilLaptopsButtonAppear()),
                "Laptops button");
        TestService.clickWithActions(driver, dashboardPage.waitUntilLaptopsButtonAreClickable(),
                "Laptops button");
    }
    @Then("^Verify Laptops category is displayed$")
    public void verifyLaptopscategoryIsDisplayed() {
        dashboardPage.waitUntilCardsTitlesAppear()
                .forEach(card -> TestService.checkTrue(TestService.isPresent(card), "Card " + card.getText()));
    }
    @When("^Click on monitors category$")
    public void clickOnMonitorsCategory() {
        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilMonitorsButtonAppear()),
                "Laptops button");
        TestService.clickWithActions(driver, dashboardPage.waitUntilMonitorsButtonAreClickable(),
                "Laptops button");
    }
    @Then("^Verify monitors category is displayed$")
    public void verifyMonitorscategoryIsDisplayed() {
        dashboardPage.waitUntilCardsTitlesAppear()
                .forEach(card -> TestService.checkTrue(TestService.isPresent(card), "Card " + card.getText()));
    }
}
