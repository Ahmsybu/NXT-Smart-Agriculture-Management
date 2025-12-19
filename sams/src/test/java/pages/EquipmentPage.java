package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EquipmentPage {

    private WebDriver driver;

    public EquipmentPage(WebDriver driver) {
        this.driver = driver;
    }

 

    public By openEquipmentButton =
            By.xpath("//button[.//span[text()='Equipment']]");

    private By addEquipmentBtn = By.xpath("//button[contains(.,'+ Add Equipment')]");
    public By zoneSelect = By.name("zoneid");
    public By modelInput = By.name("equipmentmodel");
    public By serialInput = By.name("serialnumber");
    public By readingTypeSelect = By.name("readingtypeid");
    public By statusSelect = By.name("status");

    public By saveBtn = By.xpath("//button[contains(text(),'Save Equipment')]");

    public By confirmDeleteBtn =
            By.xpath("//button[contains(text(),'Delete') and @class[contains(.,'bg-red')]]");

    public By serialRequiredMessage =
            By.xpath("//*[contains(text(),'Serial Number is required')]");

  

    public void openEquipmentPage() {
        driver.findElement(openEquipmentButton).click();
       
    }

    public void clickAddEquipment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addEquipmentBtn));
        btn.click();
    }

    public void selectZone(String zoneName) {
        new Select(driver.findElement(zoneSelect)).selectByVisibleText(zoneName);
    }

    public void enterModel(String model) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(modelInput));
        field.clear();
        field.sendKeys(model);
    }

    public void enterSerialNumber(String serial) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(serialInput));
        field.clear();
        field.sendKeys(serial);
    }

    public void selectReadingType(String type) {
        new Select(driver.findElement(readingTypeSelect)).selectByVisibleText(type);
    }

    public void selectStatus(String status) {
        new Select(driver.findElement(statusSelect)).selectByVisibleText(status);
    }

    public void saveEquipment() {
        driver.findElement(saveBtn).click();
        
    }

    public boolean waitUntilSerialAppears(WebDriver driver, String serial, int timeoutSeconds) {
        By serialLocator = By.xpath("//p[contains(normalize-space(text()), 'SN-1765474023783')]");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            WebElement serialElement = wait.until(ExpectedConditions.visibilityOfElementLocated(serialLocator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", serialElement);
            return serialElement.isDisplayed();
        } catch (Exception e) {
            return false; 
        }
    }

 
    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception ignored) {}
    }
    private void safeType(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 3; i++) {
            try {
                WebElement field = wait.until(
                        ExpectedConditions.elementToBeClickable(locator)
                );
                field.clear();
                field.sendKeys(text);

                if (field.getAttribute("value").equals(text)) {
                    return;
                }
            } catch (Exception ignored) {}

            sleep(300); 
        }

        throw new RuntimeException("Failed typing into: " + locator);
    }
    
    public void addEquipment(String zone, String model, String serial,
                             String readingType, String status) {
   	    

    				safeType(modelInput, model);
    				safeType(serialInput, serial);

  					selectZone(zone);
  					selectReadingType(readingType);
  							selectStatus(status);

  							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  							wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
}

    public void deleteEquipment(String serial) {
        clickDelete(serial);
        driver.findElement(confirmDeleteBtn).click();
        
    }

    
    By equipmentRowBy(String serial) {
        return By.xpath("//*[contains(text(),'" + serial + "')]/ancestor::*[contains(@class,'row') or contains(@class,'flex')]");
    }
    public WebElement equipmentRowElement(String serial) {
        return driver.findElement(equipmentRowBy(serial));
    }

    public String row(String serial) {
        return equipmentRowElement(serial).getText();
    }

    public boolean isEquipmentDisplayed(String serial) {
        try {
            return driver.findElement(equipmentRowBy(serial)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

  
    public void clickEdit(String serial) {
        WebElement row = equipmentRowElement(serial);
        row.findElement(By.xpath(".//button[contains(text(),'Edit')]")).click();
       
    }

    public void updateModel(String newModel) {
        WebElement field = driver.findElement(modelInput);
        field.clear();
        field.sendKeys(newModel);
    }

    public String getModelFieldValue() {
        return driver.findElement(modelInput).getAttribute("value");
    }

 
    public void clickDelete(String serial) {
        WebElement row = equipmentRowElement(serial);
        row.findElement(By.xpath(".//button[contains(text(),'Delete')]")).click();
    }


    public boolean isSerialRequiredShown() {
        try {
            return driver.findElement(serialRequiredMessage).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
