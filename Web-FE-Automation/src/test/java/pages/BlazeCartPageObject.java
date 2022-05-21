package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class BlazeCartPageObject extends BasePage {

    private By locTotalPriceLabel       = By.id("totalp");
    private By locProductTitleLabel     = By.xpath("//div[@class = 'col-lg-8']/h2");
    private By locTotalLabel            = By.xpath("//div[@class = 'col-lg-1']/h2");
    private By locPlaceOlderButton      = By.xpath("//div[@class = 'col-lg-1']/h2/following-sibling::button");
    private By locRowProductsTitles     = By.xpath("//tbody[@id = 'tbodyid']/tr/td[2]");

    private List<By> initPageLocators   = Arrays.asList(
            locTotalPriceLabel,
            locProductTitleLabel,
            locTotalLabel,
            locPlaceOlderButton
    );

    public BlazeCartPageObject(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement getPlaceOlderButton() {
        return getElement(locPlaceOlderButton);
    }

    public WebElement waitUntilRowDeleteButtonAreClickableByProductName(String productName) {
        String xpath = "//tbody[@id = 'tbodyid']/tr/td[text() = '"+productName+"']//following-sibling::td/a";
        return waitUntilElementAreClickable(By.xpath(xpath));
    }

    public List<WebElement> waitUntilRowProductsTitlesAreVisibles() {
        return waitUntilAllElementsAreVisible(locRowProductsTitles);
    }

    public List<WebElement> waitUntilTheNumberOfProductsReduces(int numberOfProducts) {
        return waitUntilTheNumberOfElementsReduces(locRowProductsTitles, numberOfProducts);
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
