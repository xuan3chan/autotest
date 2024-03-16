package testnextsp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import Helper.ExcelHelper;

public class AddProductTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	// Excel data
	ExcelHelper excelHelper = new ExcelHelper();
	List<List<String>> excelData = excelHelper.useDataFromExcel();

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

		WebDriverWait wait = new WebDriverWait(driver, 15);
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
		Thread.sleep(4000);
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
	public void TC_02_AddProduct() throws InterruptedException, IOException {
		// get data from excel
		String productName = excelData.get(0).get(2);
		String price = excelData.get(0).get(3);
		String oldPrice = excelData.get(0).get(4);
		String description = excelData.get(0).get(7);
		String image = excelData.get(0).get(8);
		

		// Fill in the form fields with appropriate values
		WebElement productNameField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[1]/div/input"));
		productNameField.sendKeys(productName);

		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys(price);
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys(oldPrice);

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
		imageUploadButton.sendKeys(image);

		// Click the "Add Product" button
		WebElement addProductButton = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[6]/button"));
		addProductButton.click();
		Thread.sleep(4000);

	}

	@Test
	public void TC_03_CheckProduct() throws InterruptedException {
		// Wait for the product table to be updated
		String productData = excelData.get(0).get(2);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/section/div[2]/div/div[3]/table/tbody/tr[1]/td[2]")));

		// Verify the product is added
		WebElement productName = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[2]"));
		Assert.assertEquals(productName.getText(),productData );// lỗi không hiên tên đầy đủ ở danh sách
		Thread.sleep(3000);
	}

	// bỏ rỗng các field
	@Test
	public void TC_04_EmptyField() throws InterruptedException {
		// Click button "Add Product"
		WebElement addProductButton = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[1]/span"));
		addProductButton.click();
		Thread.sleep(2000);
		// Click the "Add Product" button
		WebElement addProductButton1 = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[6]/button"));
		addProductButton1.click();
		Thread.sleep(2000);
		// check error message require of field
		WebElement productNameField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[1]/div/input"));
		Assert.assertEquals(productNameField.getAttribute("validationMessage"), "Vui lòng điền vào trường này.");
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Vui lòng điền vào trường này.");

		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Vui lòng điền vào trường này.");
		WebElement imageUploadButton = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[1]/input"));
		Assert.assertEquals(imageUploadButton.getAttribute("validationMessage"), "Vui lòng chọn một hoặc nhiều tệp.");

	}

	// check select dropdown
	@Test
	public void TC_05_CheckDropdown() throws InterruptedException {

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
		brandDropdown.selectByIndex(0);
		Select categoryDropdown = new Select(driver.findElement(
				By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[2]/select")));
		categoryDropdown.selectByIndex(0);
		Select statusDropdown = new Select(driver.findElement(
				By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[2]/select")));
		statusDropdown.selectByIndex(0);
		// Click the "Add Product" button
		WebElement addProductButton1 = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[6]/button"));
		addProductButton1.click();
		Thread.sleep(2000);
		// check error message require of field
		WebElement brandDropdown1 = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[1]/select"));
		Assert.assertEquals(brandDropdown1.getAttribute("validationMessage"), "Vui lòng chọn một mục trong danh sách.");
		WebElement categoryDropdown1 = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[2]/select"));
		Assert.assertEquals(categoryDropdown1.getAttribute("validationMessage"),
				"Vui lòng chọn một mục trong danh sách.");
		WebElement statusDropdown1 = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[2]/select"));
		Assert.assertEquals(statusDropdown1.getAttribute("validationMessage"),
				"Vui lòng chọn một mục trong danh sách.");
	}

	// check symbol in field
	@Test
	public void TC_06_productNameFieldSymbolCheck() {
		String productName = excelData.get(2).get(2);
		// Test for productNameField symbol validation
		WebElement productNameField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[1]/div/input"));
		productNameField.sendKeys(productName);
		Assert.assertEquals(productNameField.getAttribute("validationMessage"),
				"Tên sản phẩm không được chứa ký tự đặc biệt.");
	}

	@Test
	public void TC_07_priceFieldSymbolCheck() {
		// Test for priceField symbol validation
		String price = excelData.get(3).get(3);
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys(price);
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được chứa ký tự đặc biệt.");
	}

	@Test
	public void TC_08_oldPriceFieldSymbolCheck() {
		String oldPrice = excelData.get(4).get(4);
		// Test for oldPriceField symbol validation
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys(oldPrice);
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được chứa ký tự đặc biệt.");
	}

	@Test
	public void TC_9_descriptionFieldSymbolCheck() {
		String description = excelData.get(5).get(7);
		// Test for descriptionField symbol validation
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField.sendKeys(description);
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"),
				"Mô tả không được chứa ký tự đặc biệt.");
	}

	// check max length
	@Test
	public void TC_10_productNameFieldMaxLengthCheck() {
		String productName = excelData.get(6).get(2);
		// Test for productNameField max length validation
		WebElement productNameField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[1]/div/input"));
		productNameField.sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
		Assert.assertEquals(productNameField.getAttribute("validationMessage"),
				"Tên sản phẩm không được quá 100 ký tự.");
	}

	@Test
	public void TC_11_priceFieldMaxLengthCheck() {
		// Test for priceField max length validation
		String price = excelData.get(7).get(3);
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys(price);
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được quá 100 ký tự.");
	}

	@Test
	public void TC_12_oldPriceFieldMaxLengthCheck() {
		// Test for oldPriceField max length validation
		String oldPrice = excelData.get(8).get(4);
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys(oldPrice);
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được quá 100 ký tự.");

	}

	@Test
	public void TC_13_descriptionFieldMaxLengthCheck() {
		// Test for descriptionField max length validation
		String description = excelData.get(9).get(7);
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField
				.sendKeys(description);
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được quá 500 ký tự.");
	}

	@Test
	public void TC_14_checkminlength() {
		// Test for priceField min length validation
		String price = excelData.get(10).get(3);
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys("1");
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được ít hơn 2 ký tự.");
	}

	@Test
	public void TC_15_oldPriceFieldMinLengthCheck() {
		// Test for oldPriceField min length validation
		String oldPrice = excelData.get(11).get(4);
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys(oldPrice);
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được ít hơn 2 ký tự.");

	}

	@Test
	public void TC_16_descriptionFieldMinLengthCheck() {
		String description = excelData.get(12).get(7);
		// Test for descriptionField min length validation
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField.sendKeys(description);
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được ít hơn 2 ký tự.");
	}

	@Test
	public void TC_17_priceFieldMinValueCheck() {
		// Test for priceField min value validation
		String price = excelData.get(13).get(3);
		WebElement priceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/input"));
		priceField.sendKeys(price);
		Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được nhỏ hơn 1.");
	}

	@Test
	public void TC_18_oldPriceFieldMinValueCheck() {
		// Test for oldPriceField min value validation
		String oldPrice = excelData.get(14).get(4);
		WebElement oldPriceField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/input"));
		oldPriceField.sendKeys(oldPrice);
		Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được nhỏ hơn 1.");
	}

	@Test
	public void TC_19_descriptionFieldMinValueCheck() {
		// Test for descriptionField min value validation
		String description = excelData.get(17).get(7);
		WebElement descriptionField = driver
				.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/textarea"));
		descriptionField.sendKeys(description);
		Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được nhỏ hơn 1.");

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
