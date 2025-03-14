package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class BookSearchRefactorTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Configurar o Firefox sem perfil específico
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchHarryPotter() {
        driver.get("https://cover-bookstore.onrender.com/");

        WebElement searchBox = driver.findElement(By.cssSelector("[data-testid='book-search-input']"));
        searchBox.sendKeys("Harry Potter");
        searchBox.sendKeys(Keys.ENTER);
        
        WebElement bookTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class, 'SearchList_bookTitle') and contains(text(), 'Harry Potter')]")));

        assertNotNull(bookTitle, "O livro 'Harry Potter and the Sorcerer's Stone' não foi encontrado!");
    }
}
