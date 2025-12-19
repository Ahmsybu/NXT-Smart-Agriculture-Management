package tests;


import SmartAgricultural.sams.BaseTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EquipmentPage;
import pages.LoginPage;

public class EquipmentTests extends BaseTest {

    String baseModel = "Sen4sor-X";
    String zone = "Ahmad hatem";
    String readingType = "Electrical Conductivity (Salinity)";
    String status = "Active";


    private String generateSerial() {
        return "SN-" + System.currentTimeMillis();
    }

 
    @Test(priority = 1)
    public void EQP01_AddEquipment_ValidData() {
        LoginPage.loging(driver);
        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        String serial = generateSerial();

        eq.clickAddEquipment();
        eq.addEquipment(zone, baseModel, serial, readingType, status);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("p.text-xs.font-normal.text-text-light-secondary")
                )
        );
        
       eq.waitUntilSerialAppears(driver, serial, 15);
        Assert.assertTrue(snElements.get(0).isDisplayed());
      System.out.println(snElements.get(0).getText());
     Assert.assertEquals(snElements.get(0).getText(),"SN: "+serial);

    
    }
  
    @Test(priority = 2)
    public void EQP02_blanckSerial_Required() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        eq.clickAddEquipment();
        eq.selectZone(zone);
        eq.enterModel("Model-Y");
        eq.selectReadingType(readingType);
        eq.selectStatus(status);
        eq.saveEquipment();

   
        
        WebElement SerialnumField = driver.findElement(By.name("serialnumber"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", SerialnumField);

        System.out.println("Browser validation message: " + validationMessage);

        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    @Test(priority = 2)
    public void EQP02_spacesblanckSerial_Required() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        eq.clickAddEquipment();
        eq.selectZone(zone);
        eq.enterModel("Model-Y");
        eq.enterSerialNumber("      ");
        eq.selectReadingType(readingType);
        eq.selectStatus(status);
        eq.saveEquipment();

   
        
        WebElement SerialnumField = driver.findElement(By.name("serialnumber"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", SerialnumField);

        System.out.println("Browser validation message: " + validationMessage);

        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    
    @Test(priority = 2)
    public void EQP02_blanckmodel_Required() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        eq.clickAddEquipment();
        eq.selectZone(zone);
        eq.enterModel("");
        eq.enterSerialNumber(generateSerial());
        eq.selectReadingType(readingType);
        eq.selectStatus(status);
        eq.saveEquipment();

   
        
        WebElement equipmentmodelField = driver.findElement(By.name("equipmentmodel"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", equipmentmodelField);

        System.out.println("Browser validation message: " + validationMessage);

        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    @Test(priority = 2)
    public void EQP02_spacesblanckmodel_Required() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        eq.clickAddEquipment();
        eq.selectZone(zone);
        eq.enterModel("");
        eq.enterSerialNumber(generateSerial());
        eq.selectReadingType(readingType);
        eq.selectStatus(status);
        eq.saveEquipment();

   
        
        WebElement equipmentmodelField = driver.findElement(By.name("equipmentmodel"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", equipmentmodelField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("fill"));
    }
  
    @Test(priority = 3)
    public void EQP03_EditEquipment() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        String serial = generateSerial();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.xpath("//button[text()='Edit']")
                )
        );
        snElements.get(0).click();
        eq.enterSerialNumber(serial);
        eq.saveEquipment();
        eq.waitUntilSerialAppears(driver, serial, 15);
        Assert.assertTrue(snElements.get(0).isDisplayed());
        
     
        eq.saveEquipment();

        Assert.assertEquals(snElements.get(0).getText(),"SN: "+serial);

    }
    
    
    @Test(priority = 3)
    public void EQP03_blanckmodel_EditEquipment() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        String serial = generateSerial();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.xpath("//button[text()='Edit']")
                )
        );
        snElements.get(0).click();
        eq.enterModel("");
             
        
        WebElement equipmentmodelField = driver.findElement(By.name("equipmentmodel"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", equipmentmodelField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("fill"));
    }

    @Test(priority = 3)
    public void EQP03_sapces_blanckmodel_EditEquipment() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        String serial = generateSerial();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.xpath("//button[text()='Edit']")
                )
        );
        snElements.get(0).click();
        eq.enterModel("      ");
             
        
        WebElement equipmentmodelField = driver.findElement(By.name("equipmentmodel"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", equipmentmodelField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("fill"));
    }

    @Test(priority = 3)
    public void EQP03_blanckserialnumber_EditEquipment() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        String serial = generateSerial();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.xpath("//button[text()='Edit']")
                )
        );
        snElements.get(0).click();
        eq.enterSerialNumber("");             
        
        WebElement equipmentmodelField = driver.findElement(By.name("serialnumber"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", equipmentmodelField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    @Test(priority = 3)
    public void EQP03_spacesblanckserialnumber_EditEquipment() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        String serial = generateSerial();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.xpath("//button[text()='Edit']")
                )
        );
        snElements.get(0).click();
        eq.enterSerialNumber("    ");             
        
        WebElement equipmentmodelField = driver.findElement(By.name("serialnumber"));

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", equipmentmodelField);

        System.out.println("Browser validation message: " + validationMessage);

        // Assert message mentions '@'
        Assert.assertTrue(validationMessage.contains("fill"));
    }
    
    @Test(priority = 3)
    public void EQP03_status_EditEquipment() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();
        eq.clickAddEquipment();
        String serial = generateSerial();
        eq.addEquipment(zone, baseModel, serial, readingType, "Fault");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.xpath("//button[text()='Edit']")
                )
        );
        snElements.get(0).click();
        eq.selectStatus("Active");
        eq.saveEquipment();
        
        WebDriverWait waite = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> inactiveStatus = waite.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.xpath("//span[text()='Active']")
                )
        );
        
        Assert.assertTrue(inactiveStatus.get(0).getText().contains("Active"));
        Assert.assertEquals(snElements.get(0).getText(), "SN: " + serial);

     


    }
  
    @Test(priority = 4)
    public void EQP04_DeleteEquipment() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);
        eq.openEquipmentPage();

        String serial = generateSerial();
        eq.clickAddEquipment();

        eq.addEquipment(zone, baseModel, serial, readingType, status);
       eq.waitUntilSerialAppears(driver, serial, 12);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                		By.cssSelector("p.text-xs.font-normal.text-text-light-secondary")
                )
        );
        
     
          
        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.font-medium.text-red-500")
                )
        );
        deleteBtn.click();

        // Confirmation Delete button
        WebElement confirmDeleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.bg-red-600")
                )
        );
        confirmDeleteBtn.click();
        

        Assert.assertFalse((snElements.get(0)).getText().contains(serial));
                
    }


    @Test(priority = 5)
    public void EQP05_ListLoadsQuickly() {
        LoginPage.loging(driver);

        EquipmentPage eq = new EquipmentPage(driver);

        long start = System.currentTimeMillis();
        eq.openEquipmentPage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        List<WebElement> snElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("p.text-xs.font-normal.text-text-light-secondary")
                )
        );
        long end = System.currentTimeMillis();

        long loadTime = end - start;

        Assert.assertTrue(loadTime <= 2000
               );
    }}

  