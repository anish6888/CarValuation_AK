package steps;

import pages.CarValuationPage;
import utils.FileUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CarValuationSteps {

    private List<String> registrationNumbers;
    private Map<String, String> actualCarDetails;

    @Given("I read the input file {string}")
    public void readInputFile(String fileName) {
        registrationNumbers = FileUtil.extractRegistrationNumbers(fileName);
        System.out.println("REGISTRATION NUMBERS ----> " + registrationNumbers);
    }

    @When("I search for each registration number on the car valuation website")
    public void searchOnCarValuationWebsite() throws InterruptedException {
        CarValuationPage carValuationPage = new CarValuationPage();
        carValuationPage.navigateToUrl();
        actualCarDetails = carValuationPage.searchCarDetails(registrationNumbers);
    }

    @Then("I compare the results with the output file {string}")
    public void compareResults(String fileName) {
        Map<String, String> expectedCarDetails = FileUtil.readExpectedCarDetails(fileName);
        Assert.assertEquals("Car details mismatch", expectedCarDetails, actualCarDetails);
    }
}
