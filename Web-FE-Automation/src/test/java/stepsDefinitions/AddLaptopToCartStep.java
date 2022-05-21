package stepsDefinitions;

import bases.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.BlazeDashboardPageObject;
import pages.BlazeLaptopsPageObject;
import services.TestService;
import utils.Constants;

public class AddLaptopToCartStep {

    private WebDriver driver;

    private BlazeDashboardPageObject dashboardPage;
    private BlazeLaptopsPageObject laptopsPage;

    public AddLaptopToCartStep() {
        driver          = TestBase.getDriver();

        dashboardPage   = new BlazeDashboardPageObject(driver);
        laptopsPage     = new BlazeLaptopsPageObject(driver);
    }

    @Then("^Verify Laptops page is displayed$")
    public void verifyLaptopsPageIsDisplayed() {
        dashboardPage.waitUntilCardsTitlesAppear()
                .forEach(card -> TestService.checkTrue(TestService.isPresent(card), "Card " + card.getText()));
    }

    @When("^Click on product (.*?)$")
    public void clickOnLaptop(String laptop) {
        if (laptop.equals(Constants.PRODUCT_DEL_I7_8GB)) {
            int pixels = 1000;

            TestService.writeAnInfo("Go to next page");
            TestService.doScroll(driver, true, pixels);

            dashboardPage.getNextPageButton().click();
            TestService.checkTrue(dashboardPage.waitUntilNextStepButtonNotVisible(), "Next page is displayed");

            TestService.doScroll(driver,false, pixels);
        }

        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilCardTitleByNameAreVisible(laptop)),
                "Phone link " + laptop);
        TestService.clickWithActions(driver, dashboardPage.waitUntilCardTitleAreClickable(laptop),
                "Phone link " + laptop);
    }

    @Then("^Laptop page is displayed$")
    public void laptopPageIsDisplayed() {
        laptopsPage.waitUntilInitElementsAppear();
        TestService.checkTrue(laptopsPage.isDisplayed(), "Laptops page is displayed");
    }

    @When("^Click add to cart button$")
    public void clickAddToCartButton() {
        TestService.clickWithActions(driver, laptopsPage.waitUntilAddToCartButtonAreClickable(),
                "Add to cart button");
    }

    @Then("^Accept pop up is confirmation displayed$")
    public void acceptPopUpIsConfirmationDisplayed() {
        TestService.checkEquals(BasePage.getAlertPopUp(driver).getText(), Constants.EXPECTED_ALERT_TITLE,
                "Alert title");
        BasePage.getAlertPopUp(driver).accept();
    }

    @When("^Click home button$")
    public void clickHomeButton() {
        TestService.clickWithActions(driver, dashboardPage.getLogoLink(), "Add to cart button");
    }
}
