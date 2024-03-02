package ManageAuto;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox. FirefoxDriver;
import org.openqa.selenium.ie. InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class UseWebdriverManager {
static WebDriver driver;
public static void main(String[] args) throws InterruptedException {
// TODO Auto-generated method stub
//WebDriverManager.chromedriver().setup(); 
WebDriverManager.firefoxdriver().setup();
//WebDriverManager.iedriver().setup();
//driver = new ChromeDriver();
driver = new FirefoxDriver();
//driver = new InternetExplorerDriver();
driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); 
driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
driver.navigate().to("https://pltpro.net");
Thread.sleep(2000);

driver.quit();
}}