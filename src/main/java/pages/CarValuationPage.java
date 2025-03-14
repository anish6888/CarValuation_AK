package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarValuationPage {
    private final WebDriver driver;
    private final By enterReg = By.xpath("//input[@id='subForm1']");
    private final By checkNow = By.xpath("//button[normalize-space()='Check Now']");
    private final By generalInformation = By.xpath("//p[normalize-space()='General Information']");

    private final By carMake = By.xpath("//td[contains(text(), 'Make')]/following-sibling::td");
    private final By carModel = By.xpath("//td[contains(text(), 'Model')]/following-sibling::td");
    private final By carYear = By.xpath("//td[contains(text(), 'Year of manufacture')]/following-sibling::td");

    public CarValuationPage() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
    }

    public void navigateToUrl() {
        driver.get("https://car-checking.com");
    }

    public Map<String, String> searchCarDetails(List<String> registrationNumbers) throws InterruptedException {
        Map<String, String> carDetails = new HashMap<>();
        for (String regNumber : registrationNumbers) {
            driver.findElement(enterReg).clear();
            driver.findElement(enterReg).sendKeys(regNumber);
            driver.findElement(checkNow).click();
            waitForElementToBeDisplayed(driver.findElement(generalInformation), 5);

            String make = driver.findElement(carMake).getText();
            String model = driver.findElement(carModel).getText();
            String year = driver.findElement(carYear).getText();

            carDetails.put(regNumber, make + "," + model + "," + year);

            driver.navigate().back();
            waitForElementToBeDisplayed(driver.findElement(enterReg), 5);

        }
        driver.quit();
        return carDetails;
    }

    public void waitForElementToBeDisplayed(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
