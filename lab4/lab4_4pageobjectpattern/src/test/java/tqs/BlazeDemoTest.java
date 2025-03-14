package tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import tqs.pages.HomePage;
import tqs.pages.SelectFlightPage;
import tqs.pages.PurchasePage;
import tqs.pages.ConfirmationPage;

@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {

    @Test
    @DisplayName("Test Flight Purchase")
    void testFlightPurchase() {
        String profilePath = "/home/hugao/snap/firefox/common/.mozilla/firefox/25a5bclp.selenium";
        
        // 🔹 Configurar o Firefox com o perfil específico
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-profile", profilePath);
        
        // 🔹 Criar o driver com as opções configuradas
        FirefoxDriver driver = new FirefoxDriver(options);
        HomePage homePage = new HomePage(driver);
        homePage.selectFromPort("Boston");
        homePage.selectToPort("Cairo");

        assertEquals("Welcome to the Simple Travel Agency!", homePage.getTitle());

        homePage.clickFindFlights();

        SelectFlightPage chooseFlightPage = new SelectFlightPage(driver);
        assertEquals("Flights from Boston to Cairo:", chooseFlightPage.getTitle());

        chooseFlightPage.clickChooseFlightButton();

        PurchasePage purchasePage = new PurchasePage(driver);

        // Preencher apenas os 4 elementos mantidos
        purchasePage.setFormData("Hugo", "123456789", "03", "2027");

        assertEquals("Your flight from TLV to SFO has been reserved.", purchasePage.getTitle());
        assertEquals("Hugo", purchasePage.getName());
        assertEquals("123456789", purchasePage.getCreditCardNumber());
        assertEquals("1103", purchasePage.getCreditCardMonth());
        assertEquals("20172027", purchasePage.getCreditCardYear());

        purchasePage.clickPurchaseFlight();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        assertEquals("Thank you for your purchase today!", confirmationPage.getTitle());
        assertEquals("555 USD", confirmationPage.getAmount());
        
    }
}
