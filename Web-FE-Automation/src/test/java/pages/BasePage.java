package pages;

import managers.ScenarioManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * This class is the parent class of the pages objects class.
 * Contains most commonly used functions to obtain the WebElements.
 * */
public class BasePage {

    public WebDriver driver;

    public BasePage() { }

    public WebElement getElement(By locator) {
        try {
            return driver.findElement(locator);

        } catch (StaleElementReferenceException e) {
            return getElement(locator);

        } catch (Exception ex) {
            return null;
        }
    }

    public WebElement fluentWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>((WebDriver) driver)
                .withTimeout(Duration.ofSeconds(Constants.TIME_TO_WAIT))
                .pollingEvery(Duration.ofMillis(Constants.TIME_TO_SLEEP))
                .ignoring(NoSuchElementException.class);

        WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver1) {
                return driver1.findElement(locator);
            }
        });
        return webElement;
    }

    public WebElement waitUntilElementAreClickable(By locator) {
        return new WebDriverWait(
                driver,
                Constants.TIME_TO_WAIT
        ).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilElementAreClickable(WebElement webElement) {
        return new WebDriverWait(
                driver,
                Constants.TIME_TO_WAIT
        ).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public List<WebElement> getElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (NoSuchElementException e) {
            ScenarioManager.writeLogInfo("Exception from getElement: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<WebElement> waitUntilElementsArePresent(By locator) {
        return new WebDriverWait(
                driver,
                Constants.TIME_TO_WAIT
        ).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitUntilAllElementsAreVisible(By locator) {
        return new WebDriverWait(
                driver,
                Constants.TIME_TO_WAIT
        ).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitUntilTheNumberOfElementsReduces(By locator, int numberOfElements) {
        return new WebDriverWait(
                driver,
                Constants.TIME_TO_WAIT
        ).until(ExpectedConditions.numberOfElementsToBeLessThan(locator, numberOfElements));
    }

    public static Alert getAlertPopUp(WebDriver driver) {
        return new WebDriverWait(
                driver,
                Constants.TIME_TO_WAIT
        ).until(ExpectedConditions.alertIsPresent());
    }

    private boolean waitForPresence(long time, By locator) {

        try {
            WebElement element = new WebDriverWait(
                    driver,
                    time
            ).until(ExpectedConditions.presenceOfElementLocated(locator));
            return element != null;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitUntilInitElementsAppear(List<By> locators) {
        return locators
                .stream()
                .allMatch(locator -> this.waitForPresence(Constants.TIME_TO_WAIT, locator));
    }

    public boolean isDisplayed() {
        return false;
    }
}
