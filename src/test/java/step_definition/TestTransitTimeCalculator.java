package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import page_objects.TransitTimeCalculatorPage;

public class TestTransitTimeCalculator {
    private TransitTimeCalculatorPage tp = new TransitTimeCalculatorPage();;

    @Given("User is on {string} page")
    public void user_is_on_page(String pageUrl) throws IOException {
        tp.navigateTo(pageUrl);
    }

    @When("I consent data privacy")
    public void user_consent_data_privacy() {
        tp.consentData();
    }

    @When("I put {string} and {string} into original country")
    public void user_put_origin_country_and_postal_code(String originCountry, String originPostalCode) {
        tp.selectOriginCountry(originCountry);
        tp.sendOriginCountryPostalCode(originPostalCode);
    }

    @When("I put {string} and {string} into destination country")
    public void user_put_destination_country_and_postal_code(String destinationCountry, String destinationPostalCode) {
        tp.selectDestinationCountry(destinationCountry);
        tp.sendDestinationCountryPostalCode(destinationPostalCode);
    }

    @When("I press calculate button")
    public void close_browser() {
        tp.clickCalculateButton();
    }

    @Then("I should see {string} and {string}")
    public void Login_page_is_opened(String messageOne, String messageTwo) {
        tp.compareMessagesDisplayed(messageOne, messageTwo);
    }

}
