package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    public WebDriver initialize() {
        String browser = System.getProperty("browser");
        String os = System.getProperty("os");
        String chromeDriverPath = os.equalsIgnoreCase("mac") ? "./drivers/chromedriver" : "./drivers/chromedriver.exe";
        String geckoDriverPath = os.equalsIgnoreCase("mac") ? "./drivers/geckodriver" : "./drivers/geckodriver.exe";
        if ("chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.swiggy.com/");
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return driver;
        } else if ("firefox".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
            WebDriver driver = new FirefoxDriver();
            driver.get("https://www.swiggy.com/");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver;
        }
        return null;
    }


}
