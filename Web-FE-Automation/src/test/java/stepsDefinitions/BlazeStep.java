package stepsDefinitions;

import bases.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.ScenarioManager;
import org.openqa.selenium.WebDriver;
import pages.*;
import services.NavigationService;
import services.TestService;
import utils.Constants;
import utils.Functions;
import utils.Webs;

import java.util.ArrayList;
import java.util.List;

public class BlazeStep {

    private WebDriver driver;

    private BlazeDashboardPageObject dashboardPage;
    private BlazeLaptopsPageObject laptopsPage;
    private BlazeCartPageObject cartPage;
    private BlazeFormPageObject formPage;
    private BlazeModalThankPurchasePageObject modalThankPurchasePage;

    private List<String> products;
    private String totalPrice;
    private String[] purchases;

    public BlazeStep() {
        driver                  = TestBase.getDriver();

        dashboardPage           = new BlazeDashboardPageObject(driver);
        laptopsPage             = new BlazeLaptopsPageObject(driver);
        cartPage                = new BlazeCartPageObject(driver);
        formPage                = new BlazeFormPageObject(driver);
        modalThankPurchasePage  = new BlazeModalThankPurchasePageObject(driver);

        products                = new ArrayList<String>();
    }

    @Given("^URL to go to blaze page without login confirmation$")
    public void urlToGoToBlazePageWithoutLoginConfirmation() {
        NavigationService.goTo(Webs.WEB_BLAZE_HOME.getUrl(), driver);
    }

    @Then("^Dashboard is displayed$")
    public void dashboardIsDisplayed() {
        dashboardPage.waitUntilInitElementsAppear();
        TestService.checkTrue(dashboardPage.isDisplayed(), "Blaze page is displayed");
        ScenarioManager.addScreenshot("Blaze dashboard");
    }

    @When("^Click on phone category$")
    public void clickOnPhoneCategory() {
        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilPhoneButtonAppear()),
                "Phone button is present");
        dashboardPage.getPhoneButton().click();
    }

    @Then("Verify is displayed")
    public void verifyIsDisplayed() {
        dashboardPage.waitUntilCardsTitlesAppear()
                .forEach(card -> TestService.checkTrue(
                        TestService.isPresent(card), "Card " + card.getText() + " is present")
                );

        ScenarioManager.addScreenshot();
    }

    @When("^Click on Laptops category$")
    public void clickOnLaptopsCategory() {
        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilLaptopsButtonAppear()),
                "Laptops button is present");
        dashboardPage.getLaptopsButton().click();
    }

    @When("^Click on monitors category$")
    public void clickOnMonitorsCategory() {
        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilMonitorsButtonAppear()),
                "Monitors button is present");
        TestService.clickWithActions(driver, dashboardPage.getMonitorsButton(), "Monitors button");
    }

    @Then("^Verify monitors category is displayed$")
    public void verifyMonitorscategoryIsDisplayed() {
        dashboardPage.waitUntilCardsTitlesAppear()
                .forEach(card -> TestService.checkTrue(
                        TestService.isPresent(card), "Card " + card.getText() + " is present")
                );
        ScenarioManager.addScreenshot("Monitors category");
    }

    @When("^Click on product (.*?)$")
    public void clickOnLaptop(String laptop) {
        if (laptop.equalsIgnoreCase(Constants.PRODUCT_DEL_I7_8GB)) {
            int pixels = 1000;

            TestService.writeAnInfo("Go to next page");
            /*------ SHORT WAIT------*/
            try {
                Thread.sleep(Constants.TIME_TO_SLEEP - 300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            /*------------------*/
            TestService.doScroll(driver, true, pixels);


            dashboardPage.getNextPageButton().click();
            TestService.checkTrue(dashboardPage.waitUntilNextStepButtonNotVisible(), "Next page is displayed");

            dashboardPage.waitUntilCardsTitlesAppear();
            TestService.doScroll(driver,false, pixels);
        }

        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilCardTitleByNameAreVisible(laptop)),
                "Laptop link " + laptop);
        TestService.clickWithActions(driver, dashboardPage.waitUntilCardTitleAreClickable(laptop),
                "Laptop link " + laptop);
    }

    @Then("^Laptop page is displayed$")
    public void laptopPageIsDisplayed() {
        laptopsPage.waitUntilInitElementsAppear();
        TestService.checkTrue(laptopsPage.isDisplayed(), "Laptops page is displayed");
        ScenarioManager.addScreenshot();
    }

    @When("^Click add to cart button$")
    public void clickAddToCartButton() {
        TestService.clickWithActions(driver, laptopsPage.waitUntilAddToCartButtonAreClickable(),
                "Add to cart button");
    }

    @Then("^Accept pop up is confirmation displayed$")
    public void acceptPopUpIsConfirmationDisplayed() {
        ScenarioManager.addScreenshot("Alert");
        TestService.checkEquals(BasePage.getAlertPopUp(driver).getText(), Constants.EXPECTED_ALERT_TITLE,
                "Alert title");
        BasePage.getAlertPopUp(driver).accept();

        TestService.clickWithActions(driver, dashboardPage.getLogoLink(), "Add to cart button");
    }

    @And("^Click on cart button$")
    public void clickOnCartButton() {
        TestService.clickWithActions(driver, dashboardPage.waitUntilCartButtonAppear(), "Cart button");
    }

    @Then("^Cart page is displayed$")
    public void cartPageIsDisplayed() {
        cartPage.waitUntilInitElementsAppear();
        TestService.checkTrue(cartPage.isDisplayed(), "Cart page is displayed");
        ScenarioManager.addScreenshot("Cart page");
    }

    @When("^Click on delete button of the product (.*?)$")
    public void clickOnDeleteButton(String productName) {
        cartPage.waitUntilRowProductsTitlesAreVisibles().forEach(product -> products.add(product.getText()));
        TestService.clickWithActions(driver, cartPage.waitUntilRowDeleteButtonAreClickableByProductName(productName),
                "Delete button");
    }

    @Then("^The product disappears from the list$")
    public void theProductDisappearsFromTheList() {
        TestService.checkTrue(
                cartPage.waitUntilTheNumberOfProductsReduces(products.size()).size() < products.size(),
                "The list has reduced the number of products"
        );
        ScenarioManager.addScreenshot();
    }

    @When("^Click on place order button$")
    public void clickOnPlaceOrderButton() {
        TestService.clickWithActions(driver, cartPage.getPlaceOlderButton(), "Place older button");
    }

    @Then("^Form page is displayed$")
    public void formPageIsDisplayed() {
        formPage.waitUntilInitElementsAppear();
        TestService.checkTrue(formPage.isDisplayed(), "Form page is displayed");

        TestService.checkTrue(TestService.isPresent(formPage.waitUntilTotalPriceLabelAppear()),
                "Total price is present");

        totalPrice = formPage.getTotalPriceLabel().getText();

        ScenarioManager.addScreenshot("Form page");
    }

    @And("^Fill all the inputs$")
    public void fillAllTheInputs() {
        String random = Functions.generateRandomWord.apply(5);
        float card = Functions.generateRandomNumbers.apply(16);

        TestService.setInput(formPage.waitUntilNameInputAppear(), random, "Name field", false);
        TestService.checkEqualsByAttribute(formPage.getNameInput(), random, "Name");

        TestService.setInput(formPage.getCountryInput(), random, "Country field", false);
        TestService.checkEqualsByAttribute(formPage.getCountryInput(), random, "Country");

        TestService.setInput(formPage.getCityInput(), random, "City field", false);
        TestService.checkEqualsByAttribute(formPage.getCountryInput(), random, "Country");


        TestService.setInput(formPage.getCardInput(), String.valueOf(card),"Card field", false);
        TestService.checkEqualsByAttribute(formPage.getCardInput(), String.valueOf(card), "Card");

        TestService.setInput(formPage.getMonthInput(), Constants.MONTH, "Month field", false);
        TestService.checkEqualsByAttribute(formPage.getMonthInput(), Constants.MONTH, "Month");

        TestService.setInput(formPage.getYearInput(), Constants.YEAR, "Year field", false);
        TestService.checkEqualsByAttribute(formPage.getYearInput(), Constants.YEAR, "Year");

        ScenarioManager.addScreenshot();
    }

    @Then("^Click on purchase button$")
    public void clickOnPurchaseButton() {
        TestService.clickWithActions(driver, formPage.getButtonPurchase(), "Purchase button");
    }

    @When("^Modal thank you purchase is displayed$")
    public void modalThankYouPurchaseIsDisplayed() {
        modalThankPurchasePage.waitUntilInitElementsAppear();
        TestService.checkTrue(modalThankPurchasePage.isDisplayed(), "Modal thank you purchase is displayed");
    }

    @Then("^Capture and log purchase Id and Amount$")
    public void captureAndLogPurchaseIdAndAmount() {
        purchases = modalThankPurchasePage.waitUntilPurchaseDataTextAppear().getText().split(":");
        TestService.checkTrue(purchases.length > 1, "Purcharse values");
       TestService.writeAnInfo(
               "ID " + purchases[1]
               .replace("Amount", "")
               .trim() +  "\n" + " Amount " + purchases[3]
               .replace("name", "")
               .trim()
       );
        ScenarioManager.addScreenshot();
    }

    @And("^Assert purchase amount equals expected$")
    public void assertPurchaseAmountEqualsExpected() {
        TestService.checkEquals(purchases[3].split(" ")[0], totalPrice, "Purchase amount");
        ScenarioManager.addScreenshot("Form disappeared");
    }

    @When("^Click OK button$")
    public void clickOKButton() {
        TestService.clickWithActions(driver, modalThankPurchasePage.waitUntilOkButtonAreClikable(),
                "Ok button");
    }

    @Then("^The form disappears$")
    public void theFormDisappears() {
        TestService.checkTrue(modalThankPurchasePage.waitUntilInitElementsAreNotVisibles(),
                "The form has disappeared");
        ScenarioManager.addScreenshot("Form disappeared and dashboard appear");
    }
}
