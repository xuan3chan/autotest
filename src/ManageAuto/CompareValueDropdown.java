package ManageAuto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import dev.failsafe.internal.util.Assert;
import graphql.org.antlr.v4.runtime.misc.EqualityComparator;
public class CompareValueDropdown {
public static void main(String[] args) throws InterruptedException {
// TODO Auto-generated method stub
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
WebDriver driver=  new ChromeDriver();
driver.manage().window().maximize();
driver.get("https://chercher.tech/practice/dropdowns");
// Kết quả mong đợi
List<String> expect_result = Arrays.asList(new String[] {"Bing", "Google", "Iphone 14", "Yahoo"});
// Kết quả thực tế
WebElement ActualDropdown= driver.findElement(By.id("order-same"));
Select actual = new Select(ActualDropdown);
ActualDropdown.click();
Thread.sleep(2000);
ActualDropdown.click();
List<WebElement> actualListElements= actual.getOptions();
List<String> actual_result = new ArrayList<String>();
for (WebElement options: actualListElements) {
actual_result.add(options.getText());}
if ((expect_result).equals(actual_result)) {
 System.out.println("Kết quả thực tế đúng với Kết quả mong đợi");
} else {
System.out.println("Kết quả thực tế khác kết quả mong đợi");
System.out.println("Kết quả mong đợi: " + expect_result.toString());
System.out.println("Kết quả thực tế: + actual_result.toString()");}
//Kiểm tra giá trị mặc định của DropDownlist khi mở trang web
String default_value_expect = "Samsung";
if(actual.getFirstSelectedOption().getText().equals(default_value_expect))
{
System.out.println("Giá trị mặc định của DropDown List đúng - PASS");
}
else {
System.out.println("Giá trị mặc định của DropDown List không đúng - FAIL");
System.out.println("Kết quả mong đợi là: " + default_value_expect);
System.out.println("Kết quả thực tế là:" + actual.getFirstSelectedOption().getText());


}
driver.close();
}

private static WebDriver driver() {
	// TODO Auto-generated method stub
	return null;
}
}


