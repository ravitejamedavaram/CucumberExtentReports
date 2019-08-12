package pages;

import data.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WelcomePage {

    @FindBy(how = How.ID, using = "location")
    private WebElement deliveryLocation;

    private WebElement getDeliveryLocationFromList(WebDriver driver, String expectedLocationName) {
        String xpath = "//*[text()='"+expectedLocationName+"']";
        return driver.findElement(By.xpath(xpath));

    }

    private WebElement enterDeliveryLocation(){
        return deliveryLocation;
    }


    public void searchLocation(WebDriver driver, Model model) {
        enterDeliveryLocation().sendKeys(model.getDeliveryLocation().getSearchString());
        getDeliveryLocationFromList(driver,model.getDeliveryLocation().getResultString()).click();
    }
}
