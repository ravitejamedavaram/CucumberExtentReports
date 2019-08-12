package swiggy.ui;

import base.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.DataMapper;
import data.Model;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CheckoutPage;
import pages.RestaurantPage;
import pages.SearchPage;
import pages.WelcomePage;
import utils.ExtentReportsConfig;
import utils.Utilities;

import java.io.IOException;

import static utils.SignupField.PASSWORD;


public class StepDefinitions {

    private WebDriver driver;
    private Model model;
    private WelcomePage welcomePage;
    private SearchPage searchPage;
    private RestaurantPage restaurantPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setup() throws IOException {
        DriverManager driverManager = new DriverManager();
        System.out.println(System.getProperty("browser"));
        driver = driverManager.initialize();
        model = DataMapper.getTestData();
        System.out.println(model.getDeliveryLocation().getResultString());
        welcomePage = PageFactory.initElements(driver, WelcomePage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        restaurantPage = PageFactory.initElements(driver, RestaurantPage.class);
        checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    }


    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
        driver.quit();
        ExtentReportsConfig.getReports();
    }

    @Given("user selects a delivery location")
    public void userSelectsADeliveryLocation() {
        welcomePage.searchLocation(driver, model);
    }

    @When("selected a specific restaurant")
    public void selectedASpecificRestaurant() {
        searchPage.searchRestaurant(driver, model.getRestaurant());
    }

    @And("ordered items and does checkout")
    public void orderedItemsAndDoesCheckout() throws Exception {
        restaurantPage.addItemsToCart(model.getItems());
        restaurantPage.checkout();
    }

    @And("tries to signup with details")
    public void triesToSignupWithDetails() {
        checkoutPage.signUp();
        checkoutPage.enterCustomerDetailsAndContinue(model.getCustomerDetails());
    }

    @Then("verify the error messages")
    public void verifyTheErrorMessages() throws IOException {
        Assert.assertTrue(checkoutPage.checkForErrors(PASSWORD));
        Utilities.capture(driver, "errorMessages");
    }

    @Then("change quantity of item")
    public void changeQuantityOfItem() throws IOException {
        checkoutPage.increaseQuantityOnCheckOutPage(driver, "Orange Pop", 5);
        Utilities.capture(driver, "increaseQuantity");
    }
}
