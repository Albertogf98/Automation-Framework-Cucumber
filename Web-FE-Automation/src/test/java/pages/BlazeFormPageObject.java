package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class BlazeFormPageObject extends BasePage {

    private By locNameInput             = By.id("name");
    private By locCountryInput          = By.id("country");
    private By locCityInput             = By.id("city");
    private By locCardInput             = By.id("card");
    private By locMonthInput            = By.id("month");
    private By locYearInput             = By.id("year");
    private By locTotalPriceLabel       = By.xpath("//form/label[@id = 'totalm']");
    private By locButtonPurchase        = By.cssSelector("#orderModal > div > div > div.modal-footer > button.btn.btn-primary");
    private List<By> initPageLocators   = Arrays.asList(
            locNameInput,
            locCountryInput,
            locCityInput,
            locCardInput,
            locMonthInput,
            locYearInput,
            locButtonPurchase,
            locTotalPriceLabel
    );

    public BlazeFormPageObject(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement waitUntilNameInputAppear() {
        return waitUntilElementAreVisible(fluentWait(locNameInput));
    }

    public WebElement getNameInput() {
        return getElement(locNameInput);
    }

    public WebElement getCountryInput() {
        return getElement(locCountryInput);
    }

    public WebElement getCityInput() {
        return getElement(locCityInput);
    }

    public WebElement getCardInput() {
        return getElement(locCardInput);
    }

    public WebElement getMonthInput() {
        return getElement(locMonthInput);
    }

    public WebElement getYearInput() {
        return getElement(locYearInput);
    }

    public WebElement waitUntilTotalPriceLabelAppear() {
        return waitUntilElementAreVisible(fluentWait(locTotalPriceLabel));
    }

    public WebElement getButtonPurchase() {
        return getElement(locButtonPurchase);
    }

    public boolean waitUntilInitElementsAppear() {
        return super.waitUntilInitElementsAppear(initPageLocators);
    }

    @Override
    public boolean isDisplayed() {
        return initPageLocators
                .stream()
                .noneMatch(locator -> getElement(locator) == null);
    }
}
