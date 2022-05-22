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
import utils.CommonsFunctions;
import utils.Constants;
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
    private String totalPrice, productName;
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
        dashboardPage.waitUntilCardsTitlesAppear();
        TestService.checkTrue(dashboardPage.isDisplayed(), "<b> Blaze page </b> is displayed");
    }

    @When("^Click on phone category$")
    public void clickOnPhoneCategory() {
        dashboardPage.waitUntilPhoneButtonAppear().click();
    }

    @Then("^Verify (.*?) page is displayed$")
    public void verifyPageIsDisplayed(String categoryPage) {
        dashboardPage.waitUntilCardsTitlesAppear();
        TestService.checkTrue(dashboardPage.isDisplayed(), "<b> " + categoryPage + " page </b> is displayed");
        ScenarioManager.addScreenshot();
    }

    @When("^Click on laptops category$")
    public void clickOnLaptopsCategory() {
        dashboardPage.waitUntilLaptopsButtonAppear().click();
    }

    @When("^Click on monitors category$")
    public void clickOnMonitorsCategory() {
        TestService.checkTrue(TestService.isPresent(dashboardPage.waitUntilMonitorsButtonAppear()),
                "<b> Monitors button </b> is present");
        dashboardPage.getMonitorsButton().click();
    }

    @When("^Click on product (.*?)$")
    public void clickOnLaptop(String laptop) {
        if (laptop.equalsIgnoreCase(Constants.PRODUCT_DEL_I7_8GB)) {

            int pixels = 1000;
            BasePage.smallWait();
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
    }

    @When("^Click add to cart button$")
    public void clickAddToCartButton() {
        laptopsPage.waitUntilAddToCartButtonAreClickable().click();
    }

    @Then("^Accept pop up is confirmation displayed$")
    public void acceptPopUpIsConfirmationDisplayed() {
        TestService.checkEquals(BasePage.getAlertPopUp(driver).getText(), Constants.EXPECTED_ALERT_TITLE,
                "Alert title");
        BasePage.getAlertPopUp(driver).accept();
        dashboardPage.getLogoLink().click();
    }

    @And("^Click on cart button$")
    public void clickOnCartButton() {
        dashboardPage.waitUntilCartButtonAppear().click();
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
        cartPage.waitUntilRowDeleteButtonAreClickableByProductName(productName).click();
        this.productName = products
                .stream()
                .filter(product -> !product.equalsIgnoreCase(productName))
                .findFirst()
                .get();
    }

    @Then("^The product disappears from the list$")
    public void theProductDisappearsFromTheList() {
        BasePage.smallWait();
        cartPage.waitUntilRowProductNameByProductName(productName);
        TestService.checkTrue(
                cartPage.waitUntilTheNumberOfProductsReduces(products.size()).size() < products.size(),
                "The list has reduced the number of products");
    }

    @When("^Click on place order button$")
    public void clickOnPlaceOrderButton() {
        ScenarioManager.addScreenshot();
        cartPage.getPlaceOlderButton().click();
    }

    @Then("^Form page is displayed$")
    public void formPageIsDisplayed() {
        formPage.waitUntilInitElementsAppear();
        TestService.checkTrue(formPage.isDisplayed(), "Form page is displayed");
    }

    @And("^Fill all the inputs$")
    public void fillAllTheInputs() {
        totalPrice = formPage.waitUntilTotalPriceLabelAppear().getText();
        String card = CommonsFunctions.generateRandomNumbers.apply(1000, 100000).toString();

        TestService.setInput(formPage.waitUntilNameInputAppear(), Constants.NAME, "Name field", false);
        TestService.checkEqualsByAttribute(formPage.getNameInput(),  Constants.NAME, "Name");

        TestService.setInput(formPage.getCountryInput(), Constants.COUNTRY, "Country field", false);
        TestService.checkEqualsByAttribute(formPage.getCountryInput(), Constants.COUNTRY, "Country");

        TestService.setInput(formPage.getCityInput(), Constants.CITY, "City field", false);
        TestService.checkEqualsByAttribute(formPage.getCityInput(), Constants.CITY, "Country");


        TestService.setInput(formPage.getCardInput(), card,"Card field", false);
        TestService.checkEqualsByAttribute(formPage.getCardInput(), card, "Card");

        TestService.setInput(formPage.getMonthInput(), Constants.MONTH, "Month field", false);
        TestService.checkEqualsByAttribute(formPage.getMonthInput(), Constants.MONTH, "Month");

        TestService.setInput(formPage.getYearInput(), Constants.YEAR, "Year field", false);
        TestService.checkEqualsByAttribute(formPage.getYearInput(), Constants.YEAR, "Year");

        ScenarioManager.addScreenshot();
    }

    @Then("^Click on purchase button$")
    public void clickOnPurchaseButton() {
        formPage.getButtonPurchase().click();
    }

    @When("^Modal thank you purchase is displayed$")
    public void modalThankYouPurchaseIsDisplayed() {
        modalThankPurchasePage.waitUntilInitElementsAppear();
        TestService.checkTrue(modalThankPurchasePage.isDisplayed(), "Modal thank you purchase is displayed");
        ScenarioManager.addScreenshot();
    }

    @Then("^Capture and log purchase Id and Amount$")
    public void captureAndLogPurchaseIdAndAmount() {
       purchases = modalThankPurchasePage.waitUntilPurchaseDataTextAppear().getText().split("\\n");
       TestService.writeAnInfo(
               "ID " + purchases[0]
               .replace("Amount", "")
               .trim() +  "\n" + " Amount " + purchases[3]
               .replace("Name", "")
               .trim()
       );
    }

    @And("^Assert purchase amount equals expected$")
    public void assertPurchaseAmountEqualsExpected() {
        TestService.checkEquals(purchases[1].split(" ")[1], totalPrice.split(" ")[1],
                "Purchase amount");
        ScenarioManager.addScreenshot();
    }

    @When("^Click OK button$")
    public void clickOKButton() {
        modalThankPurchasePage.getOkButton().click();
    }

    @Then("^The form disappears$")
    public void theFormDisappears() {
        modalThankPurchasePage.waitUntilInitElementsAreNotVisibles();
        ScenarioManager.addScreenshot();
    }
}
