package tqs.hw1.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class BookreservationsandcancelTest {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        driver.manage().window().setSize(new Dimension(1920, 1053));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void bookreservationsandcancel() {
        driver.get("http://localhost:3000/");

        // Wait for the meal card to be clickable and click on it
        WebElement firstReserveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".meal-card-horizontal:nth-child(1) .reserve-btn")));
        firstReserveButton.click();

        // Wait for the second restaurant card to be clickable and click on it
        WebElement secondRestaurant = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".restaurant-card:nth-child(2) h3")));
        secondRestaurant.click();

        // Wait for the second meal card to be clickable and click on it
        WebElement secondReserveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".meal-card-horizontal:nth-child(2) .reserve-btn")));
        secondReserveButton.click();

        // Wait for the reservation tab to be clickable and click on it
        WebElement reservationTab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs > button:nth-child(2)")));
        reservationTab.click();

        // Wait for the cancel button of the reservation and click on it
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".reservation-card:nth-child(2) > .cancel-btn")));
        cancelButton.click();
    }
}
