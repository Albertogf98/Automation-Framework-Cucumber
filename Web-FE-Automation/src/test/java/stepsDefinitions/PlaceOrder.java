package stepsDefinitions;

import bases.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BlazeCartPageObject;
import pages.BlazeFormPageObject;
import pages.BlazeModalThankPurchasePageObject;
import services.TestService;
import utils.Constants;
import utils.Functions;

public class PlaceOrder {

    private WebDriver driver;

    private BlazeCartPageObject cartPage;
    private BlazeFormPageObject formPage;
    private BlazeModalThankPurchasePageObject modalThankPurchasePage;

    private String totalPrice;
    private String[] purchases;

    public PlaceOrder() {
        driver                  = TestBase.getDriver();

        cartPage                = new BlazeCartPageObject(driver);
        formPage                = new BlazeFormPageObject(driver);
        modalThankPurchasePage  = new BlazeModalThankPurchasePageObject(driver);
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
    }

    @And("^Fill all the inputs$")
    public void fillAllTheInputs() {
        String random = Functions.generateRandomWord.apply(5);

        TestService.setInput(formPage.getNameInput(), random, "Name field", false);
        TestService.setInput(formPage.getCountryInput(), random, "Country field", false);
        TestService.setInput(formPage.getCityInput(), random, "City field", false);
        TestService.setInput(formPage.getCardInput(), String.valueOf(Functions.generateRandomNumbers.apply(16)),
                "Card field", false);
        TestService.setInput(formPage.getMonthInput(), Constants.MONTH, "Month field", false);
        TestService.setInput(formPage.getYearInput(), Constants.YEAR, "Year field", false);
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
        TestService.writeAnInfo("ID " +  purchases[1] +  "\n" + "Amount " + purchases[3]);
    }

    @And("^Assert purchase amount equals expected$")
    public void assertPurchaseAmountEqualsExpected() {
        TestService.checkEquals(purchases[3].split(" ")[0], totalPrice, "Purchase amount");
    }

    @When("^Click OK button$")
    public void clickOKButton() {
        TestService.clickWithActions(driver, modalThankPurchasePage.waitUntilOkButtonAreClikable(),
                "Purchase button");
    }
}
