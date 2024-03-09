package testnextsp;

import java.util.List;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class updateProductTest {
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
	// click update button
	public void TC_01_openForm() {
		// Click button "Update Product"
		WebElement updateProductButton = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
		updateProductButton.click();
		// Check form is opened
		WebElement form = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[5]/div/form"));
		Assert.assertTrue(form.isDisplayed());
	}

	@Test
  //check update Product Name *
  public void TC_02_checkProductName() throws InterruptedException {
		String productText = "Test case update Product Name";
		
		WebElement productNameField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[1]/div/input"));
		//xóa text cũ 
		productNameField.clear();
		Thread.sleep(2000);
		productNameField.sendKeys(productText);
		WebElement updateButton = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
		updateButton.click();
		Thread.sleep(4000);
		//check update success
		WebElement productName = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[2]"));
		Assert.assertEquals(productName.getText(), productText);//fail bởi vì danh sách k hiện hết tên sản phẩm
  }
@Test
//check	 update Price *
public void TC_03_checkPrice() throws InterruptedException {
	WebElement updateProductButton = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
	updateProductButton.click();
	// Check form is opened
	WebElement form = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[5]/div/form"));
	Assert.assertTrue(form.isDisplayed());
	String priceText = "1234000";
	String formatPrice = "1.234.000đ";
	WebElement priceField = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[2]/div[1]/input"));
	// xóa text cũ
	priceField.clear();
	Thread.sleep(2000);
	priceField.sendKeys(priceText);
	WebElement updateButton = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
	updateButton.click();
	Thread.sleep(4000);
	// check update success
	WebElement price = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[7]"));
	Assert.assertEquals(price.getText(), formatPrice);
}
@Test
//check update Old Price *
public void TC_04_checkOldPrice() throws InterruptedException {
	WebElement updateProductButton = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
	updateProductButton.click();
	// Check form is opened
	WebElement form = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[5]/div/form"));
	Assert.assertTrue(form.isDisplayed());
	String oldPriceText = "200000";
	String formatOldPrice = "200.000đ";
	WebElement oldPriceField = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[2]/div[2]/input"));
	// xóa text cũ
	oldPriceField.clear();
	Thread.sleep(2000);
	oldPriceField.sendKeys(oldPriceText);
	WebElement updateButton = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
	updateButton.click();
	Thread.sleep(4000);
	// check update success
	WebElement oldPrice = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[8]"));
	Assert.assertEquals(oldPrice.getText(), formatOldPrice);
}
@Test
//check update Brand *
public void TC_05_checkBrand() throws InterruptedException {
	WebElement updateProductButton = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
	updateProductButton.click();

	WebElement brandDropdown = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[2]/div[1]/select"));
	// select random value
	List<WebElement> options = brandDropdown.findElements(By.tagName("option"));
	int randomIndex = (int) (Math.random() * (options.size() - 1)) + 1;
	brandDropdown.click();
	WebElement updateButton = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
	updateButton.click();
	Thread.sleep(4000);
	// check update success
	WebElement brand = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[4]"));
	Assert.assertTrue(brand.isDisplayed());
}
@Test
//check update Category *
public void TC_06_checkCategory() throws InterruptedException {
	WebElement updateProductButton = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
	updateProductButton.click();

	WebElement categoryDropdown = driver.findElement(
			By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[2]/div[2]/select"));
	// select random value
	List<WebElement> options = categoryDropdown.findElements(By.tagName("option"));
	int randomIndex = (int) (Math.random() * (options.size() - 1)) + 1;
	categoryDropdown.click();
	WebElement updateButton = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
	updateButton.click();
	Thread.sleep(4000);
	// check update success
	WebElement category = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[5]"));
	Assert.assertTrue(category.isDisplayed());
}
@Test
//check update Description *
public void TC_07_checkDescription() throws InterruptedException {
	WebElement updateProductButton = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
	updateProductButton.click();
	String descriptionText = "Test case update description";
	WebElement descriptionField = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[3]/textarea"));
	// xóa text cũ
	descriptionField.clear();
	Thread.sleep(2000);
	descriptionField.sendKeys(descriptionText);
	WebElement updateButton = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
	updateButton.click();
	Thread.sleep(4000);
	// check update success
	WebElement description = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[3]/table/tbody/tr[1]/td[3]"));
	Assert.assertEquals(description.getText(), descriptionText);//fail bởi vì danh sách k hiện hết mô tả sản phẩm
}
@Test
//check dropdown
public void TC_08_checkDropdown() throws InterruptedException {
	WebElement updateProductButton = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
	updateProductButton.click();
	WebElement brandDropdown = driver.findElement(
			By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[2]/div[1]/select"));
	// select random value
	List<WebElement> options = brandDropdown.findElements(By.tagName("option"));
	int randomIndex = (int) (Math.random() * (options.size() - 1)) + 1;
	brandDropdown.click();
	WebElement categoryDropdown = driver.findElement(
			By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[2]/div[2]/select"));
	// select random value
	List<WebElement> options1 = categoryDropdown.findElements(By.tagName("option"));
	int randomIndex1 = (int) (Math.random() * (options1.size() - 1)) + 1;
	categoryDropdown.click();
	WebElement updateButton = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
	updateButton.click();
	Thread.sleep(4000);
	// check update success
	WebElement brand = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[4]"));
	WebElement category = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[5]"));
	Assert.assertTrue(brand.isDisplayed());
	Assert.assertTrue(category.isDisplayed());
}
@Test
//check update symbol all
public void TC_09checkSymbol() throws InterruptedException {
	WebElement updateProductButton = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
	updateProductButton.click();
	String symbolText = "Test case update symbol";
	WebElement symbolField = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[2]/div[3]/input"));
	// xóa text cũ
	symbolField.clear();
	Thread.sleep(2000);
	symbolField.sendKeys(symbolText);
	WebElement updateButton = driver
			.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button"));
	updateButton.click();
	Thread.sleep(4000);
	// check update success
	WebElement symbol = driver
			.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[6]"));
	Assert.assertEquals(symbol.getText(), symbolText);
}
//check max length
	@Test
	public void TC_10_productNameFieldMaxLengthCheck() {
		// Test for productNameField max length validation
		WebElement productNameField = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[5]/div/form/div[1]/div/input"));
		productNameField.sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
		Assert.assertEquals(productNameField.getAttribute("validationMessage"),
				"Tên sản phẩm không được quá 100 ký tự.");
	}

	@Test
	public void TC_11_priceFieldMaxLengthCheck() {
		// Test for priceField max length validation
		WebElement priceField = driver
				.findElement(By.xpath("//*[@id=\"price\"]"));
		priceField.sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được quá 100 ký tự.");
	}

	@Test
	public void TC_12_oldPriceFieldMaxLengthCheck() {
		// Test for oldPriceField max length validation
		WebElement oldPriceField = driver
				.findElement(By.xpath("//*[@id=\"oldprice\"]"));
		oldPriceField.sendKeys("123456789" + "01234567890123456789012345678901234567890123456789012345678901234567890");
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được quá 100 ký tự.");

	}

	@Test
	public void TC_13_descriptionFieldMaxLengthCheck() {
		// Test for descriptionField max length validation
		WebElement descriptionField = driver
				.findElement(By.xpath("//*[@id=\"description\"]"));
		descriptionField
				.sendKeys("123456" + "78901234567890123456789012345678901234567890123456789012345678901234567890"
						+ "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
						+ "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
						+ "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
						+ "12345678901234567890123456789012345678901234567890123456789012345678901234567890");
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được quá 500 ký tự.");
	}

	@Test
	public void TC_14_checkminlength() {
		// Test for priceField min length validation
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys("1");
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được ít hơn 2 ký tự.");
	}

	@Test
	public void TC_15_oldPriceFieldMinLengthCheck() {
		// Test for oldPriceField min length validation
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys("1");
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được ít hơn 2 ký tự.");
	}

	@Test
	public void TC_16_descriptionFieldMinLengthCheck() {
		// Test for descriptionField min length validation
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField.sendKeys("1");
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được ít hơn 2 ký tự.");
		
	}

	@Test
	public void TC_17_priceFieldMinValueCheck() {
		// Test for priceField min value validation
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys("0");
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được nhỏ hơn 1.");
	}

	@Test
	public void TC_18_oldPriceFieldMinValueCheck() {
		// Test for oldPriceField min value validation
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys("0");
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được nhỏ hơn 1.");
		
	}

	@Test
	public void TC_19_priceFieldMaxValueCheck() {
		// Test for priceField max value validation
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys("100 000 000 000");
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được lớn hơn 99 999 999 999.");
		
	}

	@Test
	public void TC_20_oldPriceFieldMaxValueCheck() {
		// Test for oldPriceField max value validation
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys("100 000 000 000");
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được lớn hơn 99 999 999 999.");
		
	}

	@Test
	public void TC_21_descriptionFieldMinValueCheck() {
		// Test for descriptionField min value validation
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField.sendKeys("0");
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được nhỏ hơn 1.");
	}

	@Test
	public void TC_22_descriptionFieldMaxValueCheck() {
		// Test for descriptionField max value validation
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField.sendKeys("100 000 000 000");
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được lớn hơn 99 999 999 999.");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
