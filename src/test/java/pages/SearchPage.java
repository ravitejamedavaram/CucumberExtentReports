package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage  {

    @FindBy(how = How.CSS, using = "*[class='global-nav'] *[href='/search'] span")
    private WebElement searchRestaurantsIcon;

    @FindBy(how = How.CSS,using = "*[placeholder='Search for restaurants or dishes']")
    private WebElement searchRestaurantsField;

    public void searchRestaurant(WebDriver driver,String restaurantName){
        WebDriverWait wait = new WebDriverWait(driver, 35);

        wait.until(ExpectedConditions.elementToBeClickable(searchRestaurantsIcon));
        searchRestaurantsIcon.click();
        searchRestaurantsField.sendKeys(restaurantName);
        searchRestaurantsField.sendKeys(Keys.RETURN);
        clickRestaurantTile(driver,restaurantName);
    }

    private void clickRestaurantTile(WebDriver driver,String restaurantName) {
        String hrefContent = restaurantName.replace(" ","-").toLowerCase();
        String finalXpath = "//*[contains(@href,'restaurants/"+hrefContent+"')]";
        driver.findElement(By.xpath(finalXpath)).click();
    }

}
