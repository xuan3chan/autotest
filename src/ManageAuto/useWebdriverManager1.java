package ManageAuto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class useWebdriverManager1 {

    static WebDriver driver;
    static WebDriver driver1;
    static WebDriver driver2;

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();

        driver = new ChromeDriver();
        driver1 = new FirefoxDriver();
        driver2 = new InternetExplorerDriver();

        driver.manage().window().maximize();
        driver1.manage().window().maximize();
        driver2.manage().window().maximize();

        driver.navigate().to("https://google.com");
        driver1.navigate().to("https://tuoitre.vn");
        driver2.navigate().to("https://xaydungphanmem.com/");
;
        driver.quit();
        driver1.quit();
        driver2.quit();
    }
}
