import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.*;

public class Test1Test {
  private WebDriver driver;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    // Configurar o Firefox sem perfil específico
    FirefoxOptions options = new FirefoxOptions();
    driver = new FirefoxDriver(options);
    js = (JavascriptExecutor) driver;
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void test1() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(550, 691));

    // Selecionar a cidade de origem
    WebElement fromDropdown = driver.findElement(By.name("fromPort"));
    fromDropdown.findElement(By.xpath("//option[. = 'Boston']")).click();

    // Selecionar a cidade de destino
    WebElement toDropdown = driver.findElement(By.name("toPort"));
    toDropdown.findElement(By.xpath("//option[. = 'London']")).click();

    // Submeter a pesquisa
    driver.findElement(By.cssSelector(".btn-primary")).click();

    // Escolher o primeiro voo
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();

    // Confirmar compra
    driver.findElement(By.cssSelector(".btn-primary")).click();

    // Verificar que a página de confirmação foi carregada
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}
