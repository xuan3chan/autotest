package TH5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTables {
	public static void main(String[] args) {
// TODO Auto-generated method stub
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
// opening the url
		driver.get("https://demo.guru99.com/test/web-table-element.php#");
//Here we are storing the value from the cell in to the string variable
		String sCellValue = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[5]/td[4]"))
				.getText();
		System.out.println(sCellValue);
// Here we are clicking on the link in Cell
		driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[4]/td[1]/a")).click();
	}
}
