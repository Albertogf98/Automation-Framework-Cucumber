package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class BlazeDashboardPageObject extends BasePage {

    private By locLogoLink                  = By.id("nava");
    private By locCategoriesLink            = By.id("cat");
    private By locNextPageButton            = By.id("next2");
    private By locPhoneCategoryButton       = By.xpath("(//a[@id = 'itemc'])[1]");
    private By locLaptopsCategoryButton     = By.xpath("(//a[@id = 'itemc'])[2]");
    private By locMonitorsCategoryButton    = By.xpath("(//a[@id = 'itemc'])[last()]");
    private By locCardsTitlesLinks          = By.xpath("//div[@id = 'tbodyid']//h4[@class = 'card-title']");
    private By locCartButton                = By.xpath("//a[@class = 'nav-link' and contains(text(), 'Car')]");

    private List<By> initPageLocators       = Arrays.asList(
            locLogoLink,
            locCategoriesLink,
            locCartButton
    );

    public BlazeDashboardPageObject(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement waitUntilPhoneButtonAppear() {
        return fluentWait(locPhoneCategoryButton);
    }

    public WebElement waitUntilPhoneButtonAreClickable() {
        return waitUntilElementAreClickable(locPhoneCategoryButton);
    }

    public WebElement waitUntilLaptopsButtonAppear() {
        return fluentWait(locLaptopsCategoryButton);
    }

    public WebElement waitUntilLaptopsButtonAreClickable() {
        return waitUntilElementAreClickable(locLaptopsCategoryButton);
    }

    public WebElement waitUntilMonitorsButtonAppear() {
        return fluentWait(locMonitorsCategoryButton);
    }

    public WebElement waitUntilMonitorsButtonAreClickable() {
        return waitUntilElementAreClickable(locMonitorsCategoryButton);
    }

    public WebElement waitUntilCardTitleByNameAreVisible(String name) {
      try {
          waitUntilElementsArePresent(locCardsTitlesLinks);
          WebElement element = waitUntilAllElementsAreVisible(locCardsTitlesLinks)
                  .stream()
                  .filter(card -> card.getText().equals(name))
                  .findFirst()
                  .get();

          return element;

      } catch (StaleElementReferenceException e) {
          return waitUntilCardTitleByNameAreVisible(name);
      }
    }

    public WebElement waitUntilCardTitleAreClickable(String name) {
        return waitUntilElementAreClickable(waitUntilCardTitleByNameAreVisible(name));
    }

    public WebElement getLogoLink() {
        return getElement(locLogoLink);
    }

    public WebElement getNextPageButton() {
        return getElement(locNextPageButton);
    }

    public WebElement waitUntilCartButtonAppear() {
        return fluentWait(locCartButton);
    }

    public List<WebElement> waitUntilCardsTitlesAppear() {
        try {
            waitUntilElementsArePresent(locCardsTitlesLinks);
            return waitUntilAllElementsAreVisible(locCardsTitlesLinks);
        } catch (StaleElementReferenceException e) {
            return waitUntilCardsTitlesAppear();
        }
    }

    public boolean waitUntilNextStepButtonNotVisible() {
        return waitUntilElementNotVisible(locNextPageButton);
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
