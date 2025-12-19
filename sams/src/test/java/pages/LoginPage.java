package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By createAccountButton = By.xpath("//button[text()='Create a new account']");

    static public void loging ( WebDriver driver) {
  	  driver.get("http://localhost:5173/");

        LoginPage login = new LoginPage(driver);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        login.enterEmail("sun2007san@gmail.com");
        login.enterPassword("12345678");
        login.clickLogin();

        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".hidden.md\\:block.ms-4.font-medium")));
  	
  	
  }
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }
}