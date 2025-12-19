package tests;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SmartAgricultural.sams.BaseTest;
import pages.LoginPage;
import pages.RegisterPage;

public class RegisterTests extends BaseTest {

 
    @Test
    public void RF_01_validName() {
    	 driver.get("http://localhost:5173/");
         driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

         LoginPage loginPage = new LoginPage(driver);
         loginPage.clickCreateAccount();
         
        RegisterPage reg = new RegisterPage(driver);
        WebElement nameField = driver.findElement(By.name("fullname"));

        reg.enterName("ahmad hatme");

        
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", nameField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", nameField);


        Assert.assertTrue(validationMessage.isEmpty());
    }

    


    @Test
    public void RF_02_invalidName() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("123@");

        
        WebElement nameField = driver.findElement(By.name("fullname"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", nameField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", nameField);


        Assert.assertTrue(validationMessage.contains("number"));
    }
    
    @Test
    public void RF_03_blankName() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("");

        
        WebElement nameField = driver.findElement(By.name("fullname"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", nameField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", nameField);


        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    @Test
    public void RF_04_SpacesblankName() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("    ");

        
        WebElement nameField = driver.findElement(By.name("fullname"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", nameField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", nameField);


        Assert.assertTrue(validationMessage.contains("fill"), "Validation appeared for valid name → " + validationMessage);
    }

    @Test
    public void RF_05_validEmail() {
    	 
    	        driver.get("http://localhost:5173/");
    	        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

    	        LoginPage loginPage = new LoginPage(driver);
    	        loginPage.clickCreateAccount();

    	        RegisterPage reg = new RegisterPage(driver);
    	        reg.enterName("ahmad");
    	        reg.enterEmail("ahmad@hatme.com");
    	        
    	        WebElement emailField = driver.findElement(By.name("email"));
    	        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", emailField);

    	        String validationMessage = (String) ((JavascriptExecutor) driver)
    	                .executeScript("return arguments[0].validationMessage;", emailField);


    	        Assert.assertTrue(validationMessage.isEmpty());
    	    }

    @Test
    public void RF_06_invalidEmail() {

        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("ahmad");
        reg.enterEmail("ahmadhatme.com");
        
        WebElement emailField = driver.findElement(By.name("email"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", emailField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);


        Assert.assertTrue(validationMessage.contains("@"));
    }
    @Test
    public void RF_07_blankEmail() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("ahmad");
        reg.enterEmail("");
        
        WebElement emailField = driver.findElement(By.name("email"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", emailField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);


        Assert.assertTrue(validationMessage.contains("fill"));
    }
    @Test
    public void RF_08_SpacesblankEmail() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("ahmad");
        reg.enterEmail("    ");
        
        WebElement emailField = driver.findElement(By.name("email"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", emailField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);


        Assert.assertTrue(validationMessage.contains("fill"));
    }

    @Test
    public void RF_10_validPhoneNUmber() {
    	  driver.get("http://localhost:5173/");
          driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

          LoginPage loginPage = new LoginPage(driver);
          loginPage.clickCreateAccount();

          RegisterPage reg = new RegisterPage(driver);
          reg.enterName("ahmad");
          reg.enterEmail("ahm@hatme.com");
          reg.enterPhone("01072634875945");
          
          WebElement phoneNUMberField = driver.findElement(By.name("phonenumber"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", phoneNUMberField);

          String validationMessage = (String) ((JavascriptExecutor) driver)
                  .executeScript("return arguments[0].validationMessage;", phoneNUMberField);


          Assert.assertTrue(validationMessage.isEmpty());
      }
    
    @Test
    public void RF_11_blanckPhoneNUmber() {
    	  driver.get("http://localhost:5173/");
          driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

          LoginPage loginPage = new LoginPage(driver);
          loginPage.clickCreateAccount();

          RegisterPage reg = new RegisterPage(driver);
          reg.enterName("ahmad");
          reg.enterEmail("ahm@hatme.com");
          reg.enterPhone("");
          
          
          WebElement phoneNUMberField = driver.findElement(By.name("phonenumber"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", phoneNUMberField);

          String validationMessage = (String) ((JavascriptExecutor) driver)
                  .executeScript("return arguments[0].validationMessage;", phoneNUMberField);


          Assert.assertTrue(validationMessage.contains("fill"));
      }
    
    @Test
    public void RF_12_spacesblanckPhoneNUmber() {
    	  driver.get("http://localhost:5173/");
          driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

          LoginPage loginPage = new LoginPage(driver);
          loginPage.clickCreateAccount();

          RegisterPage reg = new RegisterPage(driver);
          reg.enterName("ahmad");
          reg.enterEmail("ahm@hatme.com");
          reg.enterPhone("      ");
          
          
          WebElement phoneNUMberField = driver.findElement(By.name("phonenumber"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", phoneNUMberField);

          String validationMessage = (String) ((JavascriptExecutor) driver)
                  .executeScript("return arguments[0].validationMessage;", phoneNUMberField);


          Assert.assertTrue(validationMessage.contains("fill"));
      }
    
    @Test
    public void RF_13_validPassword() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("ahmad");
        reg.enterEmail("ahm@hatme.com");
        reg.enterPassword("12345678");
        
        WebElement passwordField = driver.findElement(By.name("password"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", passwordField);

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", passwordField);


        Assert.assertTrue(validationMessage.isEmpty());
    }

    @Test
    public void RF_13_invalidPassword() {
    	  driver.get("http://localhost:5173/");
          driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

          LoginPage loginPage = new LoginPage(driver);
          loginPage.clickCreateAccount();

          RegisterPage reg = new RegisterPage(driver);
          reg.enterName("ahmad");
          reg.enterEmail("ahm@hatme.com");
          reg.enterPassword("1234");
          
          WebElement passwordField = driver.findElement(By.name("password"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", passwordField);

          String validationMessage = (String) ((JavascriptExecutor) driver)
                  .executeScript("return arguments[0].validationMessage;", passwordField);


          Assert.assertTrue(validationMessage.contains("number"));
      }

    @Test
    public void RF_14_blankPassword() {
    	  driver.get("http://localhost:5173/");
          driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

          LoginPage loginPage = new LoginPage(driver);
          loginPage.clickCreateAccount();

          RegisterPage reg = new RegisterPage(driver);
          reg.enterName("ahmad");
          reg.enterEmail("ahm@hatme.com");
          reg.enterPassword("");
          
          WebElement passwordField = driver.findElement(By.name("password"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", passwordField);

          String validationMessage = (String) ((JavascriptExecutor) driver)
                  .executeScript("return arguments[0].validationMessage;", passwordField);


          Assert.assertTrue(validationMessage.contains("fill"));
      }
    
    @Test
    public void RF_15_SpacesblankPassword() {
    	  driver.get("http://localhost:5173/");
          driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

          LoginPage loginPage = new LoginPage(driver);
          loginPage.clickCreateAccount();

          RegisterPage reg = new RegisterPage(driver);
          reg.enterName("ahmad");
          reg.enterEmail("ahm@hatme.com");
          reg.enterPassword("              ");
          
          WebElement passwordField = driver.findElement(By.name("password"));
          ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", passwordField);

          String validationMessage = (String) ((JavascriptExecutor) driver)
                  .executeScript("return arguments[0].validationMessage;", passwordField);


          Assert.assertTrue(validationMessage.contains("fill"));
      }
    
    @Test
    public void RF_16_validConfirmPassword() {
    	 driver.get("http://localhost:5173/");
         driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

         LoginPage loginPage = new LoginPage(driver);
         loginPage.clickCreateAccount();

         RegisterPage reg = new RegisterPage(driver);
         reg.enterName("ahmad");
         reg.enterEmail("ahm@hatme.com");
         reg.enterPassword("12345678");
         reg.enterConfirmPassword("12345678");
         
         WebElement ConfirmPasswordField = driver.findElement(By.name("password"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", ConfirmPasswordField);

         String validationMessage = (String) ((JavascriptExecutor) driver)
                 .executeScript("return arguments[0].validationMessage;", ConfirmPasswordField);


         Assert.assertTrue(validationMessage.isEmpty());
     }
   

    @Test
    public void RF_17_invalidConfirmPassword() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("ahmad");
        reg.enterEmail("ahm@hatme.com");
        reg.enterPhone("010121212121");
        reg.enterPassword("123456781");       
        reg.enterConfirmPassword("1232245678"); 
        reg.clickRegister();
        
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Passwords do not match')]")
                ));

        String text = errorMessage.getText();

        Assert.assertEquals(text.trim(), "Passwords do not match");
    }

    @Test
    public void RF_18_blankConfirmPassword() {
     	 driver.get("http://localhost:5173/");
         driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

         LoginPage loginPage = new LoginPage(driver);
         loginPage.clickCreateAccount();

         RegisterPage reg = new RegisterPage(driver);
         reg.enterName("ahmad");
         reg.enterEmail("ahm@hatme.com");
         reg.enterPhone("010203012312");
         reg.enterPassword("12345678");
         reg.enterConfirmPassword("");
         

         WebElement confirmPassField = driver.findElement(
                 By.xpath("//input[contains(@placeholder,'Confirm')]")
         );

         // Trigger browser validation
         ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", confirmPassField);

         // Read validation message from browser
         String validationMessage = (String) ((JavascriptExecutor) driver)
                 .executeScript("return arguments[0].validationMessage;", confirmPassField);

         System.out.println("Validation: " + validationMessage);

         Assert.assertTrue(validationMessage.contains("fill"));
         }
    
    @Test
    public void RF_19_SpacecesblankConfirmPassword() {
     	 driver.get("http://localhost:5173/");
         driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

         LoginPage loginPage = new LoginPage(driver);
         loginPage.clickCreateAccount();

         RegisterPage reg = new RegisterPage(driver);
         reg.enterName("ahmad");
         reg.enterEmail("ahm@hatme.com");
         reg.enterPhone("010203012312");
         reg.enterPassword("12345678");
         reg.enterConfirmPassword("     ");
         

         WebElement confirmPassField = driver.findElement(
                 By.xpath("//input[contains(@placeholder,'Confirm')]")
         );

         // Trigger browser validation
         ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", confirmPassField);

         // Read validation message from browser
         String validationMessage = (String) ((JavascriptExecutor) driver)
                 .executeScript("return arguments[0].validationMessage;", confirmPassField);

         System.out.println("Validation: " + validationMessage);

         Assert.assertTrue(validationMessage.contains("fill"));
         }
    
    @Test
    public void RF_20_addNewRegister() {
   	 driver.get("http://localhost:5173/");
     driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

     LoginPage loginPage = new LoginPage(driver);
     loginPage.clickCreateAccount();

     RegisterPage reg = new RegisterPage(driver);
     reg.enterName("ahmad");
     reg.enterEmail("ahm9@hatme.com");
     reg.enterPhone("0102030129312");
     reg.enterPassword("12345678");
     reg.enterConfirmPassword("12345678");
     reg.clickRegister();

    
        WebElement verifyHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Verify Your Account')]"))
                );

        Assert.assertTrue(verifyHeader.isDisplayed());
        System.out.println(" Registration successful → Verification page opened.");
    }

    @Test
    public void RF_21_registerExistingOrInvalid() {
    	driver.get("http://localhost:5173/");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateAccount();

        RegisterPage reg = new RegisterPage(driver);
        reg.enterName("dummy tester"); // existing
        reg.enterEmail("sun2007san@gmail.com"); // existing
        reg.enterPhone("+491112223333");
        reg.enterPassword("12345678");
        reg.enterConfirmPassword("12345678");
        reg.clickRegister();
        
        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'This email is already registered.')]")
                ));

        String text = error.getText();

        Assert.assertEquals(text, "This email is already registered.");
        
    }
}