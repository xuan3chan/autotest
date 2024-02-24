package testnextsp;

import static org.testng.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class statisticsTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chrome");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://admin.nextsp-server.id.vn/login");
		driver.findElement(By.id("accountName")).sendKeys("admin2");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		passwordField.sendKeys("admin");

		WebElement submitButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/button")));
		submitButton.click();
		Thread.sleep(3000);
		// Chuyển hướng đến trang sản phẩm
		driver.navigate().to("https://admin.nextsp-server.id.vn/Dashboard");
		// Kiểm tra URL hiện tại
	

	}

@Test 
public void TC_01_checkUrl() {
	// Kiểm tra URL hiện tại
	String currentUrl = driver.getCurrentUrl();
	Assert.assertEquals(currentUrl, "https://admin.nextsp-server.id.vn/Dashboard");
}
@Test
//check text sidebar
public void TC_02_TextOnSideBar() throws InterruptedException {
	
	// Kiểm tra text của các mục trên sidebar
	WebElement sidebar = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/span[2]"));
	String sidebarText = sidebar.getText();
	Assert.assertEquals(sidebarText, "Dashboard");
	Thread.sleep(2000);
}
@Test
public void TC_03_selectYear() throws InterruptedException {
  // Click button "Select Year"
  WebElement selectYearButton = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/select"));
  
  // Scroll đến phần tử
  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectYearButton);
  
  // Click vào phần tử
  selectYearButton.click();//bị div đè lên nên case fail

 
}
@Test
public void TC_04_selectMonth() throws InterruptedException {
  // Click button "Select Month"
  WebElement selectMonthButton = driver.findElement(By.xpath("/html/body/div/section/div[2]/div/div[2]/select[2]"));
  
  // Scroll đến phần tử
  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectMonthButton);
  
  // Click vào phần tử
  selectMonthButton.click();//bị div đè lên nên case fail
}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
