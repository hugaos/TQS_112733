import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.openqa.selenium.firefox.FirefoxOptions;


class HelloWorldFirefoxJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup(); 
    }

    @BeforeEach
    void setup() {
        // Configuração genérica do Firefox sem perfil específico
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    @Test
    void test() {
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
        System.out.println("Título da página: " + title);
        
        WebElement slowCalculatorLink = driver.findElement(By.linkText("Slow calculator"));
        slowCalculatorLink.click();

        System.out.println("!!! SLOW CALCULATOR!!!");
        String actualUrl=driver.getCurrentUrl();
        assertThat(actualUrl).isEqualTo("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
    }
    
    @AfterEach
    void teardown() {
        driver.quit();
    }
}
