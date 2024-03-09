package TH5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTables2 {
	public static void main(String[] args) {
// TODO Auto-generated method stub
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
// opening the url
		driver.get("https://demo.guru99.com/test/web-table-element.php#");
		String Expect_Value = "A";
		for (int i = 1; i <= 5; i++) {
			String Actual_Value = null;
			Actual_Value = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[" + i + "]/td[2]"))
					.getText();
			if (Actual_Value.equalsIgnoreCase(Expect_Value)) {
// If the sValue match with the description, it will initiate one more inner loop for all the columns of 'i' row 
				for (int j = 1; j <= 5; j++) {
					String RowValues = driver
							.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[" + i + "]/td[" + j + "]"))
							.getText();
					System.out.print(RowValues + "---");
				}
				System.out.println();
//break;

			}
		}
//driver.close();
	}
}
