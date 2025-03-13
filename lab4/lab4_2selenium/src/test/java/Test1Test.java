import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

public class Test1Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
    public void setUp() {
        // Definir o caminho do perfil do Firefox
        String profilePath = "/home/hugao/snap/firefox/common/.mozilla/firefox/25a5bclp.selenium";
        
        // Configurar o Firefox com o perfil específico
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-profile", profilePath);

        driver = new FirefoxDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
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
