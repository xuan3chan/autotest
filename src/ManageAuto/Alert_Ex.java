package ManageAuto;
import org.openqa.selenium.Alert; 
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
public class Alert_Ex {
public static void main(String[] args) throws InterruptedException {
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver(); 
	driver.manage().window().maximize(); 
	driver.get("https://demo.guru99.com/test/delete_customer.php"); 
	driver.findElement(By.name("cusid")).sendKeys("22558899"); 
	driver.findElement(By.name("submit")).submit();
// Switching to Alert 
	Alert alert= driver.switchTo().alert();
// Capturing alert message. 
	String alertMessage= driver.switchTo().alert().getText();
// Displaying alert message 
	System.out.println(alertMessage); Thread.sleep(3000);
// Accepting alert alert.accept();
driver.findElement(By.name("cusid")).sendKeys("12345"); 
driver.findElement(By.name("submit")).click(); Thread.sleep(3000);
// Cancel alert 
alert.dismiss(); Thread.sleep(3000); 
driver.findElement(By.name("res")).click();
}
}
