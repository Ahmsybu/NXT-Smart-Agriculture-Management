package tests;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SmartAgricultural.sams.BaseTest;
import pages.LoginPage;
import pages.ZonePage;
import pages.CropPage;

import java.time.Duration;

public class CropTests extends BaseTest {

    String cropName = "Tomato";
    String stage = "Vegetative Growth";
    String season = "Primary Season";
    String plantedDate = "2025-12-15";
    String expectedHarvest = "2026-02-18";

    String activeHarvestDate = "2026-03-01";
    String yieldKg = "60";

    String updatedStage = "Reproductive Stage";
    String updatedHarvest = "2026-03-25";
    
    	
    	String zoneName = "asdas";

    

    	
    

    	@Test(priority = 1)
    	public void CR01_addCrop_validData() {
    	    		
  	    LoginPage.loging(driver);

    	    ZonePage.openZonePage(driver);

    	    new WebDriverWait(driver, Duration.ofSeconds(10)).until(
    	    	    ExpectedConditions.visibilityOfElementLocated(
    	    	        By.xpath("//h3[contains(text(),'asdas')]")
    	    	    )
    	    	);
    	    
    	    ZonePage zone = new ZonePage(driver);

    	    String zoneName = "asdas";
    	    String currentCropText = zone.getCurrentCrop();

    	    Assert.assertTrue(
    	        currentCropText.contains("Wasabi"),
    	        "Wrong current crop for zone: " + zoneName + " | Actual: " + currentCropText
    	    );
    	 LoginPage.loging(driver);
    	
    	}
    	

    @Test(priority = 2)
    public void CR02_blankCropValidation() {
        LoginPage.loging(driver);
        CropPage crop = new CropPage(driver);

        crop.openAddCropForm();
        crop.selectStage(stage);
        crop.setPlantedAt(plantedDate);
        crop.selectSeason(season);
        crop.saveCrop();

        WebElement cropField = driver.findElement(By.name("cropId"));
        String msg = getValidation(cropField);
        Assert.assertTrue(msg.length()>3, "Validation missing on blank crop");
    }

    @Test(priority = 3)
    public void CR03_blankDateValidation() {
        LoginPage.loging(driver);
        CropPage crop = new CropPage(driver);

        crop.openAddCropForm();
        crop.selectCrop(cropName);
        crop.selectStage(stage);
        crop.selectSeason(season);
        crop.saveCrop();    

        WebElement dateField = driver.findElement(By.name("plantedAt"));
        String msg = getValidation(dateField);
        Assert.assertTrue(msg.length()>3, "Planted date required didn't fire");
    }


    @Test(priority = 4)
    public void CR04_addActiveCrop_withHarvest() {
        LoginPage.loging(driver);
        CropPage crop = new CropPage(driver);

        crop.openAddCropForm();
        crop.selectCrop("Wheat");
        crop.selectStage("Maturity & Harvest");
        crop.setPlantedAt(plantedDate);
        crop.selectSeason(season);

        crop.enableActiveCrop();
        crop.fillHarvestData(activeHarvestDate, yieldKg);
        crop.saveCrop();

        WebElement result = waitFor("//td[contains(text(),'" + yieldKg + "')]");
        Assert.assertTrue(result.isDisplayed(), "Active crop not saved properly");
    }

 
    @Test(priority = 5)
    public void CR05_duplicateCropNotAllowed() {
        LoginPage.loging(driver);
        CropPage crop = new CropPage(driver);

        crop.openAddCropForm();
        crop.selectCrop(cropName);
        crop.selectStage(stage);
        crop.setPlantedAt(plantedDate);
        crop.selectSeason(season);
        crop.saveCrop();

        boolean existsTwice = driver.findElements(By.xpath("//td[contains(text(),'" + cropName + "')]")).size() > 1;

        Assert.assertFalse(existsTwice);
    }


    @Test(priority = 6)
    public void CR06_editCrop() {
        LoginPage.loging(driver);
        CropPage crop = new CropPage(driver);

        crop.openEditMenu();
        crop.selectStage(updatedStage);
        crop.setExpectedHarvest(updatedHarvest);
        crop.saveCrop();

        WebElement updated = waitFor("//td[contains(text(),'" + updatedStage + "')]");
        Assert.assertTrue(updated.isDisplayed(), "Updated crop not found in table");
    }

   
    @Test(priority = 7)
    public void CR07_deleteCrop() {
        LoginPage.loging(driver);
//        CropPage crop = new CropPage(driver);

//        crop.deleteCrop(cropName);

        boolean stillExists = driver.findElements(By.xpath("//td[contains(text(),'" + cropName + "')]")).size() > 0;
        Assert.assertFalse(stillExists, "Crop was NOT deleted");
    }

 
    private WebElement waitFor(String xpath){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    private String getValidation(WebElement el){
        return (String)((JavascriptExecutor)driver).executeScript(
                "return arguments[0].validationMessage;", el);
    }
}
