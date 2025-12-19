package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class CropPage {

    WebDriver driver;

    // Constructor
    public CropPage(WebDriver driver) {
        this.driver = driver;
    }



    private By viewDetailsBtn   = By.xpath("//button[contains(text(),'View Details')]");
    private By plantCropBtn     = By.xpath("//button[contains(text(),'Plant a Crop')]");
    private By cropNameDropdown = By.name("cropId");
    private By stageDropdown    = By.name("currentStageId");
    private By plantedAtDate    = By.name("plantedAt");
    private By expectHarvestDate= By.name("expectedHarvestAt");
    private By seasonDropdown   = By.name("seasonId");
    private By activeCropToggle = By.xpath("//div[contains(@class,'peer-checked:bg-primary')]/.."); // clickable parent

    private By actualHarvestAt  = By.name("actualHarvestAt");
    private By yieldKgField     = By.name("yieldWeightKg");

    private By saveBtn          = By.xpath("//button[text()='Save Crop']");

    private By optionsMenu      = By.xpath("//*[name()='svg' and @viewBox='0 0 20 20']");
    private By editCropBtn      = By.xpath("//button[contains(text(),'Edit Zone')]");


   

    public void openAddCropForm() {
        driver.findElement(viewDetailsBtn).click();
        driver.findElement(plantCropBtn).click();
    }

    public void selectCrop(String cropName) {
        new Select(driver.findElement(cropNameDropdown)).selectByVisibleText(cropName);
    }

    public void selectStage(String stage) {
        new Select(driver.findElement(stageDropdown)).selectByVisibleText(stage);
    }

    public void setPlantedAt(String date) {
        driver.findElement(plantedAtDate).clear();
        driver.findElement(plantedAtDate).sendKeys(date);
    }

    public void setExpectedHarvest(String date) {
        driver.findElement(expectHarvestDate).clear();
        driver.findElement(expectHarvestDate).sendKeys(date);
    }

    public void selectSeason(String season) {
        new Select(driver.findElement(seasonDropdown)).selectByVisibleText(season);
    }

    public void enableActiveCrop() {
        driver.findElement(activeCropToggle).click();
    }

    public void fillHarvestData(String actualDate, String weight) {
        driver.findElement(actualHarvestAt).sendKeys(actualDate);
        driver.findElement(yieldKgField).clear();
        driver.findElement(yieldKgField).sendKeys(weight);
    }

    public void saveCrop() {
        driver.findElement(saveBtn).click();
    }


    public void openEditMenu() {
        driver.findElement(optionsMenu).click();
        driver.findElement(editCropBtn).click();
    }

}
