package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TransitTimeCalculatorPage {
    // TODO: move that in ENUM when tests grow
    private final int waitDuration = 20;

    private final Base base = Base.getInstance();
    private final WebDriver driver  = base.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));

    // TODO: introduce static data loader when tests grow
    private final By originalCountryDropdown = By.id("origin-country");
    private final By originalCountryPostCode = By.id("origin-postcode");
    private final By destinationCountryDropdown = By.id("destination-country");
    private final By destinationCountryPostCode = By.id("destination-postcode");
    private final By calculateButton = By.xpath("//span[text()=\"Calculate\"]");
    private final By consentData =  By.id("onetrust-accept-btn-handler");
    private final By choosePickUpDate = By.xpath("//label[text()=\"Choose pick-up date:\"]");
    private final By deliveryOnOptions =  By.xpath("//span[text()=\" delivery on\"]//strong");

    public TransitTimeCalculatorPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void navigateTo(String url) throws IOException {
        String tempUrl = base.getProp().get(url).toString();
        driver.get(tempUrl);
    }

    public void consentData() {
        // TODO: refactor to handle more cases
        WebElement element = driver.findElement(consentData);
        element.click();
    }

    public void selectDestinationCountry(String country) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationCountryDropdown));
        wait.until(ExpectedConditions.elementToBeClickable(destinationCountryDropdown));

        Select dropdown = new Select(driver.findElement(destinationCountryDropdown));
        dropdown.selectByVisibleText(country);
    }

    public void selectOriginCountry(String country) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(originalCountryDropdown));
        wait.until(ExpectedConditions.elementToBeClickable(originalCountryDropdown));

        Select dropdown = new Select(driver.findElement(originalCountryDropdown));
        dropdown.selectByVisibleText(country);
    }

    public void sendOriginCountryPostalCode(String countryCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(originalCountryPostCode));
        wait.until(ExpectedConditions.elementToBeClickable(originalCountryPostCode));


        WebElement element = driver.findElement(originalCountryPostCode);
        element.click();
        element.clear();
        element.sendKeys(countryCode);
    }

    public void sendDestinationCountryPostalCode(String countryCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationCountryPostCode));
        wait.until(ExpectedConditions.elementToBeClickable(destinationCountryPostCode));

        WebElement element = driver.findElement(destinationCountryPostCode);
        element.click();
        element.clear();
        element.sendKeys(countryCode);
    }

    public void clickCalculateButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(calculateButton));
        wait.until(ExpectedConditions.elementToBeClickable(calculateButton));

        WebElement element = driver.findElement(calculateButton);
        new Actions(driver).moveToElement(element).click().perform();
        // That is left by purpose. Need to be sure the button is clicked after movement to element is completed.
        // Double click will not do the same behaviour. From time to time, the other widgets are overlapping over
        // the button.
        element.click();
    }

    public void compareMessagesDisplayed(String messageOne, String messageTwo) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(choosePickUpDate));
        wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryOnOptions));

        WebElement elementChoosePickUpDate = driver.findElement(choosePickUpDate);
        List<WebElement> elementsDeliveryOnOptions = driver.findElements(deliveryOnOptions);

        assertTrue(elementChoosePickUpDate.isDisplayed());
        assert (elementChoosePickUpDate.getText().equals(messageOne));

        for (WebElement webEl : elementsDeliveryOnOptions) {
            assertTrue(webEl.isDisplayed());
            assert (webEl.getText().equals(messageTwo));
        }

    }

}
