package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.util.List;
@Disabled
public class BookSearchTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeEach
    public void setUp() {
        // Definir o caminho do perfil do Firefox
        String profilePath = "/home/hugao/snap/firefox/common/.mozilla/firefox/25a5bclp.selenium";

        // Configurar o Firefox com o perfil específico
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-profile", profilePath);

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
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
        WebElement bookTitle = driver.findElement(By.cssSelector("span[class^='SearchList_bookTitle']"));
        assertNotNull(bookTitle, "O livro 'Harry Potter and the Sorcerer's Stone' não foi encontrado!");
    }
}
