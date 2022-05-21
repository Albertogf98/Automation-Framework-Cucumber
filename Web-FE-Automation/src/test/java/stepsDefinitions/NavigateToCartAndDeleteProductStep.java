package stepsDefinitions;

import bases.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BlazeCartPageObject;
import pages.BlazeDashboardPageObject;
import services.TestService;

import java.util.ArrayList;
import java.util.List;

public class NavigateToCartAndDeleteProductStep {

    private WebDriver driver;

    private BlazeDashboardPageObject dashboardPage;
    private BlazeCartPageObject cartPage;

    private List<String> products;

    public NavigateToCartAndDeleteProductStep() {
        driver          = TestBase.getDriver();

        dashboardPage   = new BlazeDashboardPageObject(driver);
        cartPage        = new BlazeCartPageObject(driver);

        products        = new ArrayList<String>();
    }

    @And("^Click on cart button$")
    public void clickOnCartButton() {
        TestService.clickWithActions(driver, dashboardPage.waitUntilCartButtonAppear(), "Cart button");
    }

    @Then("^Cart page is displayed$")
    public void cartPageIsDisplayed() {
       cartPage.waitUntilInitElementsAppear();
       TestService.checkTrue(cartPage.isDisplayed(), "Cart page is displayed");
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
    }
}
