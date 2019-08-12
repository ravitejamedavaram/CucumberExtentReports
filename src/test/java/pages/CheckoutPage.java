package pages;

import data.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.SignupField;

public class CheckoutPage {

    String invalidPhoneNumberError = "Enter your phone number";
    String invalidNameError = "Enter your name";
    String invalidEmailError = "Invalid email address";
    String invalidPasswordError = "Password should be min 6 chars";

    String signUpFieldLabels = ".//following::label";


    @FindBy(how = How.XPATH, using = "//*[text()='New to Swiggy?']")
    private WebElement signUpButton;

    @FindBy(how = How.ID, using = "mobile")
    private WebElement mobileNumber;

    @FindBy(how = How.ID, using = "name")
    private WebElement name;

    @FindBy(how = How.ID, using = "email")
    private WebElement email;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.XPATH, using = "//*[text()='Have a referral code?']")
    private WebElement referralQuestion;

    @FindBy(how = How.XPATH, using = "//*[text()='CONTINUE']")
    private WebElement continueButton;

    public void signUp() {
        signUpButton.click();
    }


    public void enterCustomerDetailsAndContinue(Model.CustomerDetails customerDetails) {
        mobileNumber.sendKeys(customerDetails.getPhone());
        name.sendKeys(customerDetails.getName());
        email.sendKeys(customerDetails.getEmail());
        password.sendKeys(customerDetails.getPassword());
        referralQuestion.click();
        continueButton.click();
    }

    public boolean checkForErrors(SignupField field) {
        switch (field) {
            default:
            case NAME:
            case EMAIL:
            case MOBILE:
                break;
            case PASSWORD:
                return password.findElement(By.xpath(signUpFieldLabels)).getText().equalsIgnoreCase(invalidPasswordError);
        }
        return false;
    }

    public void increaseQuantityOnCheckOutPage(WebDriver driver, String itemName, int quantity) {
        String finalXpath = "//*[text()='" + itemName + "']/parent::*/following::*/*[text()='+']";
        while (quantity > 0) {
            driver.findElement(By.xpath(finalXpath)).click();
            quantity--;
        }
    }
}
