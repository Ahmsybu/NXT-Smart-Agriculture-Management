package pages;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FarmPage {

    WebDriver driver;

    public By farmsMenuBtn = By.xpath("//button[.//span[text()='Farms']]");
    public By createFarmBtn = By.xpath("//button[contains(text(),'+ Create Farm')]");

    public By input_name = By.name("name");
    public By input_description = By.name("description");
    public By input_code = By.name("code");
    public By input_address = By.name("address");
    public By input_lat = By.name("lat");
    public By input_lon = By.name("lon");
    public By saveBtn = By.xpath("//button[@type='submit' and contains(text(),'Save Farm')]");
    public By cancelBtn = By.xpath("//button[contains(text(),'Cancel')]");

    public By farmRow = By.xpath("//button[contains(@class,'flex items-center')]");
    public By editBtn = By.xpath("//button[text()='Edit']");
    public By deleteBtn = By.xpath("//button[contains(text(),'Delete')]");
    public By confirmDeleteBtn = By.xpath("//button[contains(text(),'Delete') and contains(@class,'bg-red-600')]");

    
    public FarmPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFarmPage() {
        driver.findElement(farmsMenuBtn).click();
    }

    public void clickCreateFarm() {
        driver.findElement(createFarmBtn).click();
    }

    public void enterName(String value) {
        driver.findElement(input_name).clear();
        driver.findElement(input_name).sendKeys(value);
    }

    public void enterDescription(String value) {
        driver.findElement(input_description).clear();
        driver.findElement(input_description).sendKeys(value);
    }

    public void enterCode(String value) {
        driver.findElement(input_code).clear();
        driver.findElement(input_code).sendKeys(value);
    }

    public void enterAddress(String value) {
        driver.findElement(input_address).clear();
        driver.findElement(input_address).sendKeys(value);
    }

    public void enterCoordinates(double lat, double lon) {
        driver.findElement(input_lat).clear();
        driver.findElement(input_lat).sendKeys(String.valueOf(lat));

        driver.findElement(input_lon).clear();
        driver.findElement(input_lon).sendKeys(String.valueOf(lon));
    }

    public void saveFarm() {
        driver.findElement(saveBtn).click();
    }

    public void selectFarmByName(String name) {
        driver.findElement(By.xpath("//h3[text()='" + name + "']")).click();
    }

    public void clickEdit(String name) {
        driver.findElement(By.xpath("//h3[text()='" + name + "']/../..//button[text()='Edit']")).click();
    }

    public void clickDelete(String name) {
        driver.findElement(By.xpath("//h3[text()='" + name + "']/../..//button[contains(text(),'Delete')]")).click();
    }

    public void confirmDelete() {
        driver.findElement(confirmDeleteBtn).click();
    }
}
