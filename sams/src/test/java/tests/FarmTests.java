package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import SmartAgricultural.sams.BaseTest;
import pages.FarmPage;
import pages.LoginPage;

public class FarmTests extends BaseTest {


    // Test data
    String farmName = "Automation Farm";
    String updatedFarmName = "Automation Farm Updated";
    String description = "Selenium Test Farm";
    String code = "FARM01";
    String address = "Cairo, Egypt";
    double lat = 30.0444;
    double lon = 31.2357;
    

    
   
   


    @Test(priority = 1)
    public void FA01_addFarm_AllFieldsValid() {
    	LoginPage.loging(driver);

        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        WebElement farmsBtn = new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.elementToBeClickable(farmPage.farmsMenuBtn));
        farmsBtn.click();

        // Wait until Create Farm button is visible
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

        // Click Create Farm
        farmPage.clickCreateFarm();

        farmPage.enterName(farmName);
        farmPage.enterDescription(description);
        farmPage.enterCode(code);
        farmPage.enterAddress(address);
        farmPage.enterCoordinates(lat, lon);
        farmPage.saveFarm();

        // Wait for farm row to appear
        WebElement farmRow = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'bg-card-light')]//h3[text()='" + farmName + "']")));
        Assert.assertTrue(farmRow.isDisplayed());
    }


    @Test(priority = 2)
    public void FA02_editFarm_ChangeName() {
    	LoginPage.loging(driver);


        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        WebElement farmsBtn = new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.elementToBeClickable(farmPage.farmsMenuBtn));
        farmsBtn.click();

        // Wait until Create Farm button is visible
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

          
        farmPage.clickEdit(farmName);
        farmPage.enterName(updatedFarmName);
        farmPage.saveFarm();

        WebElement updatedRow = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[text()='" + updatedFarmName + "']")));
        Assert.assertTrue(updatedRow.isDisplayed());
    }

    @Test(priority = 3)
    public void FA03_deleteFarm() {
    	LoginPage.loging(driver);

          FarmPage farmPage = new FarmPage(driver);
          
        farmPage.openFarmPage();
        farmPage.clickDelete(updatedFarmName);
        farmPage.confirmDelete();

        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//h3[text()='" + updatedFarmName + "']")));

boolean deleted = driver.findElements(By.xpath("//h3[text()='" + updatedFarmName + "']")).isEmpty();
Assert.assertTrue(deleted, "Farm was not deleted successfully");
    }

    // ---------- Field Validation Tests ----------

    @Test(priority = 4)
    public void FA04_blankNameValidation() {
    	LoginPage.loging(driver);

        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        farmPage.openFarmPage();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

        // Click Create Farm
        farmPage.clickCreateFarm();

        farmPage.enterName("");  // Blank
        farmPage.enterCode("FARM02");
        farmPage.enterAddress(address);
        farmPage.enterCoordinates(lat, lon);

        // Trigger browser validation on Name field
        WebElement nameField = driver.findElement(By.name("name"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", nameField);

        // Read browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", nameField);

        System.out.println("Validation message: " + validationMessage);
        Assert.assertTrue(validationMessage.contains("fill"));
    }


    @Test(priority = 5)
    public void FA05_blankCodeValidation() {
    	LoginPage.loging(driver);

        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        farmPage.openFarmPage();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

        // Click Create Farm
        farmPage.clickCreateFarm();

        farmPage.enterName("3232");  
        farmPage.enterCode("");
        farmPage.enterAddress(address);
        farmPage.enterCoordinates(lat, lon);

        // Trigger browser validation on Name field
        WebElement codeField = driver.findElement(By.name("code"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", codeField);

        // Read browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", codeField);

        System.out.println("Validation message: " + validationMessage);
        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    @Test(priority = 5)
    public void FA06_SpcessblankCodeValidation() {
    	LoginPage.loging(driver);

        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        farmPage.openFarmPage();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

        // Click Create Farm
        farmPage.clickCreateFarm();

        farmPage.enterName("3232");  
        farmPage.enterCode("      ");
        farmPage.enterAddress(address);
        farmPage.enterCoordinates(lat, lon);

        // Trigger browser validation on Name field
        WebElement codeField = driver.findElement(By.name("code"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", codeField);

        // Read browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", codeField);

        System.out.println("Validation message: " + validationMessage);
        Assert.assertTrue(validationMessage.contains("fill"));
    }

    @Test(priority = 6)
    public void FA07_blankAddressValidation() {
    	LoginPage.loging(driver);


        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        farmPage.openFarmPage();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

        // Click Create Farm
        farmPage.clickCreateFarm();

        farmPage.enterName("ahmad");  
        farmPage.enterCode("123123");
        farmPage.enterAddress("");
        farmPage.enterCoordinates(lat, lon);

        // Trigger browser validation on Name field
        WebElement addressField = driver.findElement(By.name("address"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", addressField);

        // Read browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", addressField);

        System.out.println("Validation message: " + validationMessage);
        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    @Test(priority = 6)
    public void FA08_SpacesblankAddressValidation() {
    	LoginPage.loging(driver);

        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        farmPage.openFarmPage();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

        // Click Create Farm
        farmPage.clickCreateFarm();

        farmPage.enterName("ahmad");  
        farmPage.enterCode("123123");
        farmPage.enterAddress("    ");
        farmPage.enterCoordinates(lat, lon);

        // Trigger browser validation on Name field
        WebElement addressField = driver.findElement(By.name("address"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", addressField);

        // Read browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", addressField);

        System.out.println("Validation message: " + validationMessage);
        Assert.assertTrue(validationMessage.contains("fill"));
    }
   
    @Test(priority = 8)
    public void FA09_maxLengthNameValidation() {
        String longName = "A".repeat(301); // assuming max is 300 chars

    	LoginPage.loging(driver);


        FarmPage farmPage = new FarmPage(driver);

        // Open Farms page
        farmPage.openFarmPage();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(farmPage.createFarmBtn));

        // Click Create Farm
        farmPage.clickCreateFarm();

        farmPage.enterName(longName);  
        farmPage.enterCode("123123");
        farmPage.enterAddress("Cairo"); // valid address
        farmPage.enterCoordinates(lat, lon);

        // Trigger browser validation on Name field
        WebElement nameField = driver.findElement(By.name("name"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].reportValidity();", nameField);

        // Read browser-native validation message
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", nameField);

        System.out.println("Validation message: " + validationMessage);
        Assert.assertTrue(validationMessage.contains("too long") || validationMessage.contains("exceed"),
                "Browser max length validation did not trigger as expected");
    }

    
    
    
    

    @Test(priority = 9)
    public void FA10_duplicateCodeValidation() {
    	LoginPage.loging(driver);


        FarmPage farmPage = new FarmPage(driver);

        farmPage.openFarmPage();
        farmPage.clickCreateFarm();
        farmPage.enterName("Duplicate Farm");
        farmPage.enterCode(code); // code from FA001
        farmPage.enterAddress(address);
        farmPage.enterCoordinates(lat, lon);
        farmPage.saveFarm();

        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Code already exists')]")));
        Assert.assertTrue(errorMessage.isDisplayed());
    }
   }
