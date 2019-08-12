package pages;

import data.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class RestaurantPage {

    @FindAll
            (@FindBy(how = How.CSS, using = "*[id='menu-content'] *[itemprop='name']"))
    private List<WebElement> items;

    @FindBy(how = How.XPATH, using = "//*[text()='Checkout']")
    private WebElement checkout;

    public void addItemsToCart(List<Model.Item> itemNames) throws Exception {
        for (WebElement item : items) {
            for (Model.Item itemName : itemNames) {
                if (item.getText().equals(itemName.getName())) {
                    WebElement button = item.findElement(By.xpath(".//parent::*/parent::*/preceding-sibling::*"));
                    int quantity = itemName.getQuantity();
                    while (quantity > -1) {
                        checkAvailabilityAndAdd(button);
                        quantity--;
                    }
                    Thread.sleep(3000);
                }
            }
        }
    }

    private void checkAvailabilityAndAdd(WebElement button) throws Exception {
        System.out.println(button.getText());
        if (button.getText().contains("Unavailable")) {
//            throw new Exception("Item not available");
        } else if (button.getText().contains("+")){
            button.findElement(By.xpath(".//*[text()='+']")).click();
        }else {
            button.click();
        }
    }

    public void checkout() {
        checkout.click();
    }
}
