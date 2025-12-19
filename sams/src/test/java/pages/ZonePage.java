package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZonePage {
    WebDriver driver;

    public By zonesMenuBtn = By.xpath("//span[text()='Zones']");
    public By createZoneBtn = By.xpath("//button[contains(text(),'+ Create Zone')]");

    public By input_name = By.name("name");
    public By input_area = By.name("area");
    public By select_soilType = By.name("soilType");
    public By saveBtn = By.xpath("//button[@type='submit' and contains(text(),'Save Zone')]");
    public By cancelBtn = By.xpath("//button[contains(text(),'Cancel')]");

    
    
    public By zoneRow(String name) {
        return By.xpath("//h3[text()='" + name + "']");
    }

    public By dropdownFarmBtn = By.xpath("//button[contains(@class,'flex items-center gap-2')]");
    public By selectFarmOption(String farmName) {
        return By.xpath("//span[text()='" + farmName + "']");
    }

    public By moreOptionsBtn(String zoneName) {
        return By.xpath("//h3[text()='" + zoneName + "']/../../..//button[contains(@class,'p-2 rounded-full')]");
    }

    public By editBtn = By.xpath("//button[text()='Edit Zone']");
    public By deleteBtn = By.xpath("//button[text()='Delete Zone']");
    public By confirmDeleteBtn = By.xpath("//button[contains(text(),'Delete')]");

    public ZonePage(WebDriver driver) {
        this.driver = driver;
    }

    static public void openZonePage(WebDriver driver) {
        driver.findElement(By.xpath("//span[text()='Zones']")).click();
    }

    public void clickCreateZone() {
        driver.findElement(createZoneBtn).click();
    }

    public void selectFarm(String farmName) {
        driver.findElement(dropdownFarmBtn).click();
        driver.findElement(selectFarmOption(farmName)).click();
    }

    public void enterName(String value) {
        driver.findElement(input_name).clear();
        driver.findElement(input_name).sendKeys(value);
    }

    public void enterArea(String value) {
        driver.findElement(input_area).clear();
        driver.findElement(input_area).sendKeys(value);
    }

    public void selectSoilType(String value) {
        driver.findElement(select_soilType).sendKeys(value);
    }

    public void saveZone() {
        driver.findElement(saveBtn).click();
    }

    public void clickEdit(String zoneName) {
        driver.findElement(moreOptionsBtn(zoneName)).click();
        driver.findElement(editBtn).click();
    }

    public void clickDelete(String zoneName) {
        driver.findElement(moreOptionsBtn(zoneName)).click();
        driver.findElement(deleteBtn).click();
    }

    public void confirmDelete() {
        driver.findElement(confirmDeleteBtn).click();
    }

	public String getCurrentCrop() {

		return null;
	}

	
}
