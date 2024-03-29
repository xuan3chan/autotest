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
import page.LoginPage;
import page.UpdateProductPage;

public class UpdateProductTest {
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
        driver.manage().window().maximize();
        driver.navigate().to("https://admin.nextsp-server.id.vn/login");
        LoginPage lg = new LoginPage(driver);
        lg.login("admin2", "admin");
        Thread.sleep(2000);
        driver.navigate().to("https://admin.nextsp-server.id.vn/products");
        Assert.assertEquals(driver.getCurrentUrl(), "https://admin.nextsp-server.id.vn/products");
    }

    @Test(priority = 1)
    public void TC_01_openform() {
    	//wait for page to load
    	        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement addProductButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UpdateProductPage.EDIT_PRODUCT_BUTTON)));
        addProductButton.click();
    }
    @Test(priority = 4)
    public void TC_02_EmptyField() throws InterruptedException {
        WebElement addProductButton = driver.findElement(By.xpath(UpdateProductPage.EDIT_PRODUCT_BUTTON));
        addProductButton.click();
        //clear all fields
        WebElement productNameField = driver.findElement(By.xpath(UpdateProductPage.PRODUCT_NAME_FIELD));
        productNameField.clear();
        WebElement priceField = driver.findElement(By.xpath(UpdateProductPage.PRICE_FIELD));
        priceField.clear();
        WebElement oldPriceField = driver.findElement(By.xpath(UpdateProductPage.OLD_PRICE_FIELD));
        oldPriceField.clear();
        WebElement descriptionField = driver.findElement(By.xpath(UpdateProductPage.DESCRIPTION_FIELD));
        descriptionField.clear();
        Thread.sleep(2000);
        WebElement addProductButton1 = driver.findElement(By.xpath(UpdateProductPage.UPDATE_PRODUCT_BUTTON_FORM));
        addProductButton1.click();
        Thread.sleep(2000);
        WebElement productNameField1 = driver.findElement(By.xpath(UpdateProductPage.PRODUCT_NAME_FIELD));
        Assert.assertEquals(productNameField1.getAttribute("validationMessage"), "Vui lòng điền vào trường này.");
        WebElement priceField1 = driver.findElement(By.xpath(UpdateProductPage.PRICE_FIELD));
        Assert.assertEquals(priceField1.getAttribute("validationMessage"), "Vui lòng điền vào trường này.");
        WebElement descriptionField1 = driver.findElement(By.xpath(UpdateProductPage.DESCRIPTION_FIELD));
        Assert.assertEquals(descriptionField1.getAttribute("validationMessage"), "Vui lòng điền vào trường này.");
        WebElement imageUploadButton = driver.findElement(By.xpath(UpdateProductPage.IMAGE_UPLOAD_BUTTON));
        Assert.assertEquals(imageUploadButton.getAttribute("validationMessage"), "Vui lòng chọn một hoặc nhiều tệp.");
    }

    @Test(priority = 5)
    public void TC_06_CheckDropdown() throws InterruptedException {
        Select brandDropdown = new Select(driver.findElement(By.xpath(UpdateProductPage.BRAND_DROPDOWN)));
        Assert.assertEquals(brandDropdown.getFirstSelectedOption().getText(), "Select a Brand");
        Select categoryDropdown = new Select(driver.findElement(By.xpath(UpdateProductPage.CATEGORY_DROPDOWN)));
        Assert.assertEquals(categoryDropdown.getFirstSelectedOption().getText(), "Select a Category");
        Select statusDropdown = new Select(driver.findElement(By.xpath(UpdateProductPage.STATUS_DROPDOWN)));
        Assert.assertEquals(statusDropdown.getFirstSelectedOption().getText(), "Select a Status");
    }

    @Test(priority = 6)
    public void TC_07_productNameFieldSymbolCheck() {
        String productName = excelData.get(2).get(1);
        WebElement productNameField = driver.findElement(By.xpath(UpdateProductPage.PRODUCT_NAME_FIELD));
        productNameField.clear();
        productNameField.sendKeys(productName);
        Assert.assertEquals(productNameField.getAttribute("validationMessage"), "Tên sản phẩm không được chứa ký tự đặc biệt.");
    }

    @Test(priority = 7)
    public void TC_08_priceFieldSymbolCheck() {
        String price = excelData.get(3).get(2);
        WebElement priceField = driver.findElement(By.xpath(UpdateProductPage.PRICE_FIELD));
        priceField.clear();
        priceField.sendKeys(price);
        Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được chứa ký tự đặc biệt.");
    }

    @Test(priority = 8)
    public void TC_09_oldPriceFieldSymbolCheck() {
        String oldPrice = excelData.get(4).get(3);
        WebElement oldPriceField = driver.findElement(By.xpath(UpdateProductPage.OLD_PRICE_FIELD));
        oldPriceField.clear();
        oldPriceField.sendKeys(oldPrice);
        Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được chứa ký tự đặc biệt.");
    }

    @Test(priority = 9)
    public void TC_10_descriptionFieldSymbolCheck() {
        String description = excelData.get(5).get(6);
        WebElement descriptionField = driver.findElement(By.xpath(UpdateProductPage.DESCRIPTION_FIELD));
        descriptionField.clear();
        descriptionField.sendKeys(description);
        Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được chứa ký tự đặc biệt.");
    }

    @Test(priority = 10)
    public void TC_11_productNameFieldMaxLengthCheck() {
    	String productName = excelData.get(6).get(1);
        WebElement productNameField = driver.findElement(By.xpath(UpdateProductPage.PRODUCT_NAME_FIELD));
        productNameField.clear();
        productNameField.sendKeys(productName);
        Assert.assertEquals(productNameField.getAttribute("validationMessage"), "Tên sản phẩm không được quá 100 ký tự.");
    }

    @Test(priority = 11)
    public void TC_12_priceFieldMaxLengthCheck() {
        WebElement priceField = driver.findElement(By.xpath(UpdateProductPage.PRICE_FIELD));
        priceField.clear();
        priceField.sendKeys(excelData.get(7).get(2));
        Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được quá 100 ký tự.");
    }

    @Test(priority = 12)
    public void TC_13_oldPriceFieldMaxLengthCheck() {
        WebElement oldPriceField = driver.findElement(By.xpath(UpdateProductPage.OLD_PRICE_FIELD));
        oldPriceField.clear();
        oldPriceField.sendKeys(excelData.get(8).get(3));
        Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được quá 100 ký tự.");
    }

    @Test(priority = 13)
    public void TC_14_descriptionFieldMaxLengthCheck() {
        WebElement descriptionField = driver.findElement(By.xpath(UpdateProductPage.DESCRIPTION_FIELD));
        descriptionField.clear();
        descriptionField.sendKeys(excelData.get(9).get(6));
        Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được quá 500 ký tự.");
    }

    @Test(priority = 14)
    public void TC_15_checkMinLength() {
        WebElement priceField = driver.findElement(By.xpath(UpdateProductPage.PRICE_FIELD));
        priceField.clear();
        priceField.sendKeys("1");
        Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được ít hơn 2 ký tự.");

    }

    @Test(priority = 15)
    public void TC_16_oldPriceFieldMinLengthCheck() {
        WebElement oldPriceField = driver.findElement(By.xpath(UpdateProductPage.OLD_PRICE_FIELD));
        oldPriceField.clear();
        oldPriceField.sendKeys(excelData.get(11).get(3));
        Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được ít hơn 2 ký tự.");
    }

    @Test(priority = 16)
    public void TC_17_descriptionFieldMinLengthCheck() {
        WebElement descriptionField = driver.findElement(By.xpath(UpdateProductPage.DESCRIPTION_FIELD));
        descriptionField.clear();
        descriptionField.sendKeys(excelData.get(12).get(6));
        Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được ít hơn 2 ký tự.");
    }

    @Test(priority = 17)
    public void TC_18_priceFieldMinValueCheck() {
        WebElement priceField = driver.findElement(By.xpath(UpdateProductPage.PRICE_FIELD));
        priceField.clear();
        priceField.sendKeys(excelData.get(13).get(3));
        Assert.assertEquals(priceField.getAttribute("validationMessage"), "Giá không được nhỏ hơn 1.");
    }

    @Test(priority = 18)
    public void TC_19_oldPriceFieldMinValueCheck() {
        WebElement oldPriceField = driver.findElement(By.xpath(UpdateProductPage.OLD_PRICE_FIELD));
        oldPriceField.clear();
        oldPriceField.sendKeys(excelData.get(14).get(3));
        Assert.assertEquals(oldPriceField.getAttribute("validationMessage"), "Giá cũ không được nhỏ hơn 1.");
    }

    @Test(priority = 19)
    public void TC_20_descriptionFieldMinValueCheck() {
        WebElement descriptionField = driver.findElement(By.xpath(UpdateProductPage.DESCRIPTION_FIELD));
        descriptionField.clear();
        descriptionField.sendKeys(excelData.get(16).get(6));
        Assert.assertEquals(descriptionField.getAttribute("validationMessage"), "Mô tả không được nhỏ hơn 1.");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}