package testnextsp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import helper.ExcelHelper;
import page.AddProductPage;
import page.DeletePage;
import page.LoginPage;
import page.ViewListProductPage;

public class DeleteProduct {
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
        driver.manage().window().maximize();
        driver.navigate().to("https://admin.nextsp-server.id.vn/login");
        LoginPage lg = new LoginPage(driver);
        lg.login("admin2", "admin");
        Thread.sleep(2000);
        driver.navigate().to("https://admin.nextsp-server.id.vn/products");
        Assert.assertEquals(driver.getCurrentUrl(), "https://admin.nextsp-server.id.vn/products");
    }

   @Test 
   public void deleteProduct() throws InterruptedException {
	   WebDriverWait wait = new WebDriverWait(driver, 15);
	  //get product name
	   WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(ViewListProductPage.PRODUCT_NAME));
	   String name = productName.getText();
	   WebElement deleteButton = driver.findElement(By.xpath(DeletePage.DELETE_PRODUCT_BUTTON));
	   deleteButton.click();
	   Thread.sleep(2000);
	   wait.until(ExpectedConditions.alertIsPresent());
	   driver.switchTo().alert().accept();
	   driver.navigate().refresh();
	   //neu false thi pass, neu true thi fail
	   Assert.assertFalse(driver.getPageSource().contains(name));
	   
   }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}