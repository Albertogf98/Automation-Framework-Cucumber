package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class BlazeLaptopsPageObject extends BasePage {

    private By loProductTitle           = By.xpath("//h2[@class = 'name']");
    private By locAddToCartButton       = By.xpath("//div[@id = 'tbodyid']//a[contains(@class, 'btn')]");

    private List<By> initPageLocators   = Arrays.asList(
            loProductTitle,
            locAddToCartButton
    );

    public BlazeLaptopsPageObject(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement waitUntilAddToCartButtonAreClickable() {
        return waitUntilElementAreClickable(locAddToCartButton);
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
