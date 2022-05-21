package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class BlazeModalThankPurchasePageObject extends BasePage {

    private By locOkButton              = By.xpath("//button[text() = 'OK']");
    private By locPurchaseDataText      = By.xpath("//div[//button[text() = 'OK']]/following-sibling::p[br]");
    private List<By> initPageLocators   = Arrays.asList(
            locOkButton,
            locPurchaseDataText
    );

    public BlazeModalThankPurchasePageObject(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement waitUntilOkButtonAreClikable() {
        return waitUntilElementAreClickable(locOkButton);
    }

    public WebElement waitUntilPurchaseDataTextAppear() {
        return fluentWait(locPurchaseDataText);
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
