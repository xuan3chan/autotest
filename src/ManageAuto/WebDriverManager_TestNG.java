package ManageAuto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import
org.testng.annotations. AfterClass;
import org.testng.annotations. BeforeClass;
import org.testng.annotations. Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class WebDriverManager_TestNG {
private WebDriver driver;
@BeforeClass
public void setUp() {
WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
}
@Test
public void SeleniumTest1() {
System.out.println("In test 1");
driver.get("http://google.com"); String expectedPageTitle = "Google";
Assert.assertTrue(driver.getTitle().contains(expectedPageTitle), "Test Failed");
}
@Test
public void SeleniumTest2() {
System.out.println("In test 2");
driver.get("http://pltpro.net"); System.out.println(driver.getTitle());
}
@Test
public void SeleniumTest3() {
System.out.println("In test 3"); driver.get("http://xaydungphanmem.com");
System.out.println(driver.getCurrentUrl());
}
@AfterClass
public void tearDown() {
if (driver != null)
driver.quit();
}}