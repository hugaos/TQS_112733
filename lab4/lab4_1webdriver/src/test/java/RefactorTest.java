import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.slf4j.Logger;

@ExtendWith(SeleniumJupiter.class) 
public class RefactorTest {

    static final Logger log = getLogger(lookup().lookupClass());

    @Test
    void testNavigationToSlowCalculator(FirefoxOptions options) { 
        String profilePath = "/home/hugao/snap/firefox/common/.mozilla/firefox/25a5bclp.selenium"; 
        options.addArguments("-profile", profilePath);

        // Criar o WebDriver com o perfil correto
        WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver(options);
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        log.debug("Pagina inicial", driver.getCurrentUrl());

        WebElement slowCalculatorLink = driver.findElement(By.linkText("Slow calculator"));
        slowCalculatorLink.click();
        log.debug("!!SLOW CALCULATOR!!!");

        String actualUrl = driver.getCurrentUrl();
        log.debug("URL atual: {}", actualUrl);

        assertThat(actualUrl).isEqualTo("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
    }
}
