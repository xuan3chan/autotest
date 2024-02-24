package testnextsp;

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

public class AddProductTest {
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
		driver.navigate().to("https://admin.nextsp-server.id.vn/products");
		// Kiểm tra URL hiện tại
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://admin.nextsp-server.id.vn/products");
		Thread.sleep(3000);
	}

	@Test
	public void TC_01_openform() {
		// Click button "Add Product"
		WebElement addProductButton = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[1]/span"));
		addProductButton.click();
		// Check form is opened
		WebElement form = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]"));
		Assert.assertTrue(form.isDisplayed());
	}

	@Test
	public void TC_02_AddProduct() throws InterruptedException {
		// Fill in the form fields with appropriate values
		WebElement productNameField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[1]/div/input"));
		productNameField.sendKeys("Test case Product Name");

		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys("1000000");
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys("200000");

		// Handle dropdowns using Select class
		Select brandDropdown = new Select(driver.findElement(
				By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[1]/select")));
		// select random value
		List<WebElement> options = brandDropdown.getOptions();
		int randomIndex = (int) (Math.random() * (options.size() - 1)) + 1;
		brandDropdown.selectByIndex(randomIndex);
		Select categoryDropdown = new Select(driver.findElement(
				By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[2]/select")));
		// select random value
		List<WebElement> options1 = categoryDropdown.getOptions();
		int randomIndex1 = (int) (Math.random() * (options1.size() - 1)) + 1;
		categoryDropdown.selectByIndex(randomIndex1);
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField.sendKeys("Test description");
		Select statusDropdown = new Select(driver.findElement(
				By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[2]/select")));
		// select random value
		List<WebElement> options2 = statusDropdown.getOptions();
		int randomIndex2 = (int) (Math.random() * (options2.size() - 1)) + 1;
		statusDropdown.selectByIndex(randomIndex2);

		// Upload an image (if applicable) using a relative path to the image file
		WebElement imageUploadButton = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[1]/input"));
		imageUploadButton.sendKeys("C:\\Users\\xuann\\Desktop\\videoposet\\dmd.png");

		// Click the "Add Product" button
		WebElement addProductButton = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[6]/button"));
		addProductButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_03_CheckProduct() throws InterruptedException {
		// Wait for the product table to be updated
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/section/div[2]/div/div[3]/table/tbody/tr[1]/td[2]")));

		// Verify the product is added
		WebElement productName = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[2]"));
		Assert.assertEquals(productName.getText(), "Test case Product Name");// lỗi không hiên tên đầy đủ ở danh sách
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
