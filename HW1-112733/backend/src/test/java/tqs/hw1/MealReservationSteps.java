package tqs.hw1;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import io.cucumber.java.After;


public class MealReservationSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the homepage")
    public void givenIAmOnTheHomepage() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(1920, 1053));
        driver.get("http://localhost:3000");
    }

    @When("I reserve the meal {string}")
    public void whenIReserveTheMeal(String mealName) {
        WebElement firstReserveButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector(".meal-card-horizontal:nth-child(2) .reserve-btn")));
        firstReserveButton.click();
        
    }

    @When("I go to Minhas Reservas")
    public void whenIGoToMinhasReservas() {
        WebElement reservationTab = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs > button:nth-child(2)")));
        reservationTab.click();
    }

    @Then("I should see {string} in my reservations list")
    public void thenIShouldSeeInMyReservationsList(String mealName) {
        // Wait until the reservation list is visible
        WebElement reservationList = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".reservation-list")));

        // Find the third reservation in the list
        WebElement meal = reservationList.findElement(
                By.xpath(".//div[@class='reservation-card'][3]//p[strong[contains(text(), 'Refeição:')]]"));

        // Get the meal name text
        String actualMealName = meal.getText();

        // Check if the meal name matches the expected one
        assertTrue(actualMealName.contains(mealName),
                "Expected meal name to be in the reservations list but found: " + actualMealName);

        driver.quit();
    }


}
