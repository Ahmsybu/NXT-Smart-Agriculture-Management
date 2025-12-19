package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SmartAgricultural.sams.BaseTest;

import java.time.Duration;
import pages.LoginPage;
import pages.ZonePage;

public class ZoneTests extends BaseTest {

    String farmName = "Duplicate Farm"; 
    String zoneName = "Zone A";
    String updatedZoneName = "Zone B";
    String soilType = "Loamy";
    String area = "10";

    @Test(priority = 1)
    public void ZN01_addZone_ValidData() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);

        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();

        zonePage.enterName(zoneName);
        zonePage.enterArea(area);
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement row = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(zonePage.zoneRow(zoneName)));
        Assert.assertTrue(row.isDisplayed());
    }

    @Test(priority = 2)
    public void ZN02_blankNameValidation() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();

        zonePage.enterName("");
        zonePage.enterArea(area);
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement nameField = driver.findElement(zonePage.input_name);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", nameField);
        Assert.assertTrue(message.contains("fill"));
    }

    @Test(priority = 2)
    public void ZN02_SpacesblankNameValidation() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();

        zonePage.enterName("       ");
        zonePage.enterArea(area);
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement nameField = driver.findElement(zonePage.input_name);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", nameField);
        Assert.assertTrue(message.contains("fill"));
    }
    
    @Test(priority = 3)
    public void ZN03_areaRequiredValidation() {
        LoginPage.loging(driver);

               ZonePage zonePage = new ZonePage(driver);
               ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();

        zonePage.enterName(zoneName);
        zonePage.enterArea("");  // blank
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement areaField = driver.findElement(zonePage.input_area);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", areaField);
        Assert.assertTrue(message.contains("empty"));
    }
    
    @Test(priority = 3)
    public void ZN03dicmalAreaValidation() {
        LoginPage.loging(driver);

               ZonePage zonePage = new ZonePage(driver);
               ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();


        zonePage.enterName(zoneName);
        zonePage.enterArea("0.1"); 
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement areaField = driver.findElement(zonePage.input_area);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", areaField);
        Assert.assertTrue(message.isEmpty());
    }
    
    
    @Test(priority = 3)
    public void ZN03_areaCannotBeNegative() {
        LoginPage.loging(driver);

               ZonePage zonePage = new ZonePage(driver);
               ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();


        zonePage.enterName(zoneName);
        zonePage.enterArea("-0.01"); 
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement areaField = driver.findElement(zonePage.input_area);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", areaField);
        Assert.assertTrue(message.contains("positive"));
    }



    @Test(priority = 6)
    public void ZN06_areaMustBeNumeric() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();

        zonePage.enterName(zoneName);
        zonePage.enterArea("1ten");  // letters
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement areaField = driver.findElement(zonePage.input_area);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", areaField);
        Assert.assertTrue(message.contains("number"));
    }

 

    @Test(priority = 8)
    public void ZN08_invalidCharactersInName() {
        LoginPage.loging(driver);
      
        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();

        zonePage.enterName("@@Zone");
        zonePage.enterArea(area);
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement nameField = driver.findElement(zonePage.input_name);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", nameField);
        Assert.assertTrue(message.contains("pattern") || message.contains("invalid"));
    }

    @Test(priority = 9)
    public void ZN09_nameMaxLength() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);
        zonePage.clickCreateZone();

        String longName = "A".repeat(300);
        zonePage.enterName(longName);
        zonePage.enterArea(area);
        zonePage.selectSoilType(soilType);
        zonePage.saveZone();

        WebElement nameField = driver.findElement(zonePage.input_name);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", nameField);
        Assert.assertTrue(message.contains("too long") || message.contains("length"));
    }

    @Test(priority = 10)
    public void ZN10_editZone_ChangeName() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);

        zonePage.clickEdit(zoneName);
        zonePage.enterName(updatedZoneName);
        zonePage.saveZone();

        WebElement row = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(zonePage.zoneRow(updatedZoneName)));
        Assert.assertTrue(row.isDisplayed());
    }

    @Test(priority = 11)
    public void ZN11_editZone_BlankName() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);

        zonePage.clickEdit(updatedZoneName);
        zonePage.enterName("");
        zonePage.saveZone();

        WebElement nameField = driver.findElement(zonePage.input_name);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", nameField);
        Assert.assertTrue(message.contains("fill"));
    }
    
    @Test(priority = 11)
    public void ZN11_editZone_SpacesBlankName() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);

        zonePage.clickEdit(updatedZoneName);
        zonePage.enterName("       ");
        zonePage.saveZone();

        WebElement nameField = driver.findElement(zonePage.input_name);
        String message = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", nameField);
        Assert.assertTrue(message.contains("fill"));
    }

    @Test(priority = 12)
    public void ZN12_deleteZone() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);

        zonePage.clickDelete(updatedZoneName);
        zonePage.confirmDelete();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(zonePage.zoneRow(updatedZoneName)));
        
        WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Delete')]")));
        confirm.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(zonePage.zoneRow(updatedZoneName)));
        
        boolean deleted = driver.findElements(zonePage.zoneRow(updatedZoneName)).isEmpty();
        Assert.assertTrue(deleted);
    }

    @Test(priority = 13)
    public void ZN13_deleteZoneWithDependencies() {
        LoginPage.loging(driver);

        ZonePage zonePage = new ZonePage(driver);
        ZonePage.openZonePage(driver);
        zonePage.selectFarm(farmName);

        zonePage.clickDelete("Zone1");  
        zonePage.confirmDelete();

        boolean exists = !driver.findElements(zonePage.zoneRow("Zone1")).isEmpty();
        Assert.assertTrue(exists);
    }
}
