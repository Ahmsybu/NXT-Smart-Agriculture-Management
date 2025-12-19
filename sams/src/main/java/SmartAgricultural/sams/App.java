package SmartAgricultural.sams;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) {
        // 1️⃣ Path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");

        // 2️⃣ Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // open maximized
        // options.addArguments("--headless"); // optional headless mode

        // 3️⃣ Launch Chrome
        WebDriver driver = new ChromeDriver(options);

        // 4️⃣ Navigate to your site
        driver.get("http://localhost:5173/");

        // 5️⃣ Print page title
        System.out.println("Page title: " + driver.getTitle());

        // 6️⃣ Keep browser open for 10 seconds so you can see it
        try { Thread.sleep(10000); } catch (InterruptedException e) {}

        // 7️⃣ Close browser
        driver.quit();
    }
}