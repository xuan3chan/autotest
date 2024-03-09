package TH5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DateTime_Ex2 {
	@SuppressWarnings("deprecation")
	
	
	public static void main(String[] args) throws InterruptedException {
// TODO Auto-generated method stub 
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/datepicker/#dropdown-month-year");
// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement framedate = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
		driver.switchTo().frame(framedate);
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		Thread.sleep(5000);
// choose month from dropdown
		WebElement month = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
		month.click();
		Thread.sleep(2000);
		Select chon_thang = new Select(month);
		chon_thang.selectByVisibleText("May");
		Thread.sleep(5000);
		WebElement year = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
		year.click();
		Thread.sleep(2000);
		Select chon_nam = new Select(year);
		chon_nam.selectByVisibleText("2015");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[6]")).click();
		Thread.sleep(5000);
		driver.close();
	}
}
