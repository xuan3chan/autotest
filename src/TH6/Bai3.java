package TH6;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Bai3 {
    public static void main(String[] args) throws AWTException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // opening the url
        driver.get("https://demoqa.com/text-box");
        
        Actions actions = new Actions(driver);
        Robot robot = new Robot();
        
        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys("Mr.PHUOC PHAN");
        Thread.sleep(2000);

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("pltsolutions3010@gmail.com");
        Thread.sleep(2000);

        System.out.println("About to zoom in");
        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
        }

        Thread.sleep(2000);
        System.out.println("About to zoom out");
        for (int i = 0; i < 4; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
        }

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("T39 đường 14, Vĩnh Phú, Thuận An, Bình Dương");
        Thread.sleep(2000);
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("a");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();

        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("c");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        
        actions.sendKeys(Keys.TAB);
        actions.build().perform();
        
        actions.sendKeys(Keys.CONTROL);
        actions.sendKeys("v");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        Thread.sleep(2000);
        
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        if (currentAddress.getAttribute("value").equals(permanentAddress.getAttribute("value"))) {
        		System.out.println("PASS");
        }
        driver.close();
    }
}
