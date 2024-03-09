package TH5;

import java.awt.AWTException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DateTimePicker {
	public static void main(String[] args) throws AWTException, InterruptedException {
		// TODO Auto-generated method stub
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/test/");
// Find the date time picker control 
		WebElement dateBox = driver.findElement(By.xpath("//form/input[@name='bdaytime']"));
// Fill date as mm/dd/yyyy as 10/30/2019 
		dateBox.sendKeys("30102019");
// Press tab to shift focus to time field 
		dateBox.sendKeys(Keys.TAB);
// Fill time as 01:32 PM 
		dateBox.sendKeys("0132CH");

		driver.findElement(By.xpath("/html/body/form/input[2]")).click();
	}
}
