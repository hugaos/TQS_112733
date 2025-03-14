package tqs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectFlightPage {
    private WebDriver driver;

    @FindBy(tagName = "h3")
    private WebElement title;

    @FindBy(css = "tr:nth-child(1) td input")
    private WebElement chooseFlightButton;

    @FindBy(css = "tr:nth-child(1) td:nth-child(2)")
    private WebElement flightNumber;

    @FindBy(css = "tr:nth-child(1) td:nth-child(3)")
    private WebElement airline;

    @FindBy(css = "tr:nth-child(1) td:nth-child(6)")
    private WebElement price;

    public SelectFlightPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public void chooseFlight() {
        chooseFlightButton.click();
    }

    public String getFlightInfo() {
        return flightNumber.getText() + " - " + airline.getText() + " - $" + price.getText();
    }
    
    public void clickChooseFlightButton() {
        chooseFlightButton.click();
    }
}
