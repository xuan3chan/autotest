package democode;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaiTap3 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chrome");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://xaydungphanmem.com");
    }

    @Test
    public void TC_01_Url() throws InterruptedException {
        Assert.assertEquals(driver.getCurrentUrl(), "https://xaydungphanmem.com/");
        System.out.println(driver.getTitle());
    	Thread.sleep(1000);
    }

    @Test
    public void TC_02_testElement() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[6]/a")).click();
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"page\"]/main/section/div/div/div[1]/div/article[2]/div[2]/a/h2")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"page\"]/main/section/div/div/div[2]/div/aside[2]/div[2]/div/a/h3")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("search")).sendKeys("Kiem thu phan mem");
		Thread.sleep(2000);
		driver.findElement(By.className("search-icon")).submit();
		Thread.sleep(2000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
