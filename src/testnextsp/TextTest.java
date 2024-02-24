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

public class TextTest {
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
	public void TC_01_TextOnSideBar() throws InterruptedException {
		// Kiểm tra text của các mục trên sidebar
		WebElement sidebar = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div[4]/span[2]"));
		String sidebarText = sidebar.getText();
		Assert.assertEquals(sidebarText, "Product");
		Thread.sleep(2000);

	}

	@Test
	public void TC_02_CheckFieldNames() throws InterruptedException {
		// kiem tra h1 cua trang
		WebElement h1 = driver.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[2]/h1"));
		String h1Text = h1.getText();
		Assert.assertEquals(h1Text, "All Products");
		// Đợi element chứa danh sách sản phẩm hiển thị
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// truy cap vao xpath cua the div chua danh sach san pham
		WebElement productsContainer = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table")));
		// Lấy danh sách các element tiêu đề cột (header)
		List<WebElement> headers = productsContainer.findElements(By.tagName("th"));

		// Kiểm tra tên các trường
		Assert.assertEquals(headers.get(0).getText(), "No.");
		Assert.assertEquals(headers.get(1).getText(), "Product");
		Assert.assertEquals(headers.get(2).getText(), "Description");
		Assert.assertEquals(headers.get(3).getText(), "Images");
		Assert.assertEquals(headers.get(4).getText(), "Brand");
		Assert.assertEquals(headers.get(5).getText(), "Category");
		Assert.assertEquals(headers.get(6).getText(), "Price");
		Assert.assertEquals(headers.get(7).getText(), "Old Price");
		Assert.assertEquals(headers.get(8).getText(), "Status");
		Assert.assertEquals(headers.get(9).getText(), "Action");
	}

	@Test
	public void TC_03_TextOnButtons() throws InterruptedException {
		// Đợi trang sản phẩm tải xong
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Kiểm tra nút "Thêm sản phẩm"
		WebElement addProductButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[1]/span")));
		Assert.assertEquals(addProductButton.getText(), "ADD PRODUCT"); // Sửa text cho phù hợp

		// Lấy danh sách các dòng sản phẩm (tr)
		List<WebElement> productRows = driver
				.findElements(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr"));

		// Duyệt qua từng dòng sản phẩm
		for (WebElement productRow : productRows) {
			// Kiểm tra nút "Sửa"
			WebElement editButton = productRow.findElement(
					By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]"));
			Assert.assertEquals(editButton.getText(), "Edit");

			// Kiểm tra nút "Xóa"
			WebElement deleteButton = productRow.findElement(
					By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[2]"));
			Assert.assertEquals(deleteButton.getText(), "Delete");
		}
	}

	@Test
	public void TC_04_TextInForm() throws InterruptedException {
		// Click the "Add Product" button to open the form
		WebElement addProductButton = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[1]/span"));
		addProductButton.click();
		Thread.sleep(2000);

		// Verify the form title
		WebElement formTitle = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/div/span[1]"));
		Assert.assertEquals(formTitle.getText(), "Add Product");

		// Verify tile field label
		WebElement productNameLabel = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[1]/div/label"));
		Assert.assertEquals(productNameLabel.getText(), "Product Name *");
		// price
		WebElement priceLabel = driver.findElement(
				By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/label"));
		Assert.assertEquals(priceLabel.getText(), "Price *");
		// Old Price
		WebElement oldPriceLabel = driver.findElement(
				By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/label"));
		Assert.assertEquals(oldPriceLabel.getText(), "Old Price *");
		// Brand
		WebElement brandLabel = driver.findElement(
				By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[1]/label"));
		Assert.assertEquals(brandLabel.getText(), "Brand *");
		// Category
		WebElement categoryLabel = driver.findElement(
				By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[2]/label"));
		Assert.assertEquals(categoryLabel.getText(), "Category *");
		// Description
		WebElement descriptionLabel = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/label"));
		Assert.assertEquals(descriptionLabel.getText(), "Description *");
		// Images
		WebElement imagesLabel = driver.findElement(
				By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[1]/label"));
		Assert.assertEquals(imagesLabel.getText(), "Image *");
		// Status
		WebElement statusLabel = driver.findElement(
				By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[2]/label"));
		Assert.assertEquals(statusLabel.getText(), "Status *");

		// Verify button text
		WebElement addProductButtonInForm = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[6]/button"));
		Assert.assertEquals(addProductButtonInForm.getText(), "Add Product");

		// Close the form
		WebElement closeButton = driver
				.findElement(By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/div/span[2]"));
		closeButton.click();
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
