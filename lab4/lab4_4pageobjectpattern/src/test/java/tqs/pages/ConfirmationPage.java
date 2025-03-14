package tqs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(xpath = "/html/body/div[2]/div/table/tbody/tr[1]/td[2]")
    private WebElement confirmationId;

    @FindBy(xpath = "/html/body/div[2]/div/table/tbody/tr[3]/td[2]")
    private WebElement amount;

    public ConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getAmount() {
        return amount.getText();
    }
}
