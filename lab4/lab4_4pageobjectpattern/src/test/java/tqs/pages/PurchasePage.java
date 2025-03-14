package tqs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {

    @FindBy(xpath = "//*[@id='inputName']")
    private WebElement name;

    @FindBy(xpath = "//*[@id='creditCardNumber']")
    private WebElement creditCardNumber;

    @FindBy(xpath = "//*[@id='creditCardMonth']")
    private WebElement creditCardMonth;

    @FindBy(xpath = "//*[@id='creditCardYear']")
    private WebElement creditCardYear;

    @FindBy(xpath = "/html/body/div[2]/form/div[11]/div/input")
    private WebElement purchaseFlightButton;

    @FindBy(xpath = "/html/body/div[2]/h2")
    private WebElement title;

    public PurchasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Preencher os campos obrigatórios
    public void setFormData(String name, String creditCardNumber, String creditCardMonth, String creditCardYear) {
        this.name.sendKeys(name);
        this.creditCardNumber.sendKeys(creditCardNumber);
        this.creditCardMonth.sendKeys(creditCardMonth);
        this.creditCardYear.sendKeys(creditCardYear);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getName() {
        return name.getAttribute("value");
    }

    public String getCreditCardNumber() {
        return creditCardNumber.getAttribute("value");
    }

    public String getCreditCardMonth() {
        return creditCardMonth.getAttribute("value");
    }

    public String getCreditCardYear() {
        return creditCardYear.getAttribute("value");
    }

    public void clickPurchaseFlight() {
        purchaseFlightButton.click();
    }
}
