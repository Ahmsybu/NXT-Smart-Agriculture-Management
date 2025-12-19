package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SmartAgricultural.sams.BaseTest;
import pages.LoginPage;

import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void LI_01_validEmail() {
        driver.get("http://localhost:5173/");
        LoginPage login = new LoginPage(driver);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        login.enterEmail("sun2007san@gmail.com");
        login.clickLogin();
        WebElement passwordField = driver.findElement(By.id("password"));

     // Get browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", passwordField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("fill"));
    }

    

    @Test
    public void LI_02_invalidEmail() {
        driver.get("http://localhost:5173/");
        LoginPage login = new LoginPage(driver);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        login.enterEmail("invalidemail");
        login.clickLogin();
        WebElement emailField = driver.findElement(By.id("email"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("@"));
    }

    
    

    @Test
    public void LI_03_blankEmail() {
        driver.get("http://localhost:5173/");
        LoginPage login = new LoginPage(driver);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        login.enterEmail("");
        login.clickLogin();
        WebElement emailField = driver.findElement(By.id("email"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);

        System.out.println("Browser validation message: " + validationMessage);

        Assert.assertTrue(validationMessage.contains("fill"));
    }

    

    @Test
    public void LI_04_loginSuccses() {
        driver.get("http://localhost:5173/");
        LoginPage login = new LoginPage(driver);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        login.enterEmail("sun2007san@gmail.com");
        login.enterPassword("12345678");
        login.clickLogin();
    
        WebElement userProfile = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".hidden.md\\:block.ms-4.font-medium")
                ));

        Assert.assertTrue(userProfile.isDisplayed());

    }


    @Test
    public void LI_05_invalidPassword() {
        driver.get("http://localhost:5173/");
        LoginPage login = new LoginPage(driver);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        login.enterEmail("sun2007san@gmail.com");
        login.enterPassword("023456111");
        login.clickLogin();

        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
        	    .until(ExpectedConditions.visibilityOfElementLocated(
        	        By.xpath("//div[contains(text(),'Invalid credentials.')]")
        	    ));

        	String text = errorMessage.getText();
        	System.out.println("Error message displayed: " + text);
        	Assert.assertEquals(text, "Invalid credentials.");
    }
    

    @Test
    public void LI_06_blankPassword() {
        driver.get("http://localhost:5173/");
        LoginPage login = new LoginPage(driver);
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        login.enterEmail("sun2007san@gmail.com");

        login.clickLogin();
        
      
        WebElement passwordField = driver.findElement(By.id("password"));
    
        
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", passwordField);
                   
               


        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
 

    @Test
    public void LI_07_emailFormatValidation() {
    	driver.get("http://localhost:5173/");

        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));

        String invalidEmail = "sun2007sagmail"; // missing '@'
        emailField.clear();
        emailField.sendKeys(invalidEmail);
        passwordField.sendKeys("123456778");
        loginButton.click();

        // Get browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("@"));
    }
}