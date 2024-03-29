package testnextsp;

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

import helper.JsonHelper;
import page.ViewListProductPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewListProduct {
    WebDriver driver;
    JsonHelper jsonHelper = new JsonHelper();
    private static final String API_URL = "https://nextsp-server.id.vn/api/products/getall";
    

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        String projectPath = System.getProperty("user.dir");
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chrome");
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://admin.nextsp-server.id.vn/login");

        // Login
        driver.findElement(ViewListProductPage.USERNAME_FIELD).sendKeys("admin2");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(ViewListProductPage.PASSWORD_FIELD));
        passwordField.sendKeys("admin");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(ViewListProductPage.LOGIN_BUTTON));
        submitButton.click();
        Thread.sleep(6000);

        // Navigate to the products page
        driver.get("https://admin.nextsp-server.id.vn/products");
        Thread.sleep(3000);
    }

    @Test
    public void TC_01_TextOnSideBar() throws InterruptedException {
        WebElement sidebar = driver.findElement(ViewListProductPage.SIDEBAR_PRODUCT_TEXT);
        String sidebarText = sidebar.getText();
        Assert.assertEquals(sidebarText, "Product");
        Thread.sleep(2000);
    }

    @Test
    public void TC_02_CheckFieldNames() throws InterruptedException {
        WebElement h1 = driver.findElement(ViewListProductPage.HEADER_H1);
        String h1Text = h1.getText();
        Assert.assertEquals(h1Text, "All Products");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement productsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(ViewListProductPage.HEADER_PRODUCT_TABLE));
        List<WebElement> headers = productsContainer.findElements(ViewListProductPage.TABLE_HEADERS);

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
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement addProductButton = wait.until(ExpectedConditions.elementToBeClickable(ViewListProductPage.BUTTON_ADD_PRODUCT));
        Assert.assertEquals(addProductButton.getText(), "ADD PRODUCT");

        List<WebElement> productRows = driver.findElements(ViewListProductPage.BUTTON_EDIT_PRODUCT);

        for (WebElement productRow : productRows) {
            WebElement editButton = productRow;
            Assert.assertEquals(editButton.getText(), "Edit");

            WebElement deleteButton = productRow.findElement(ViewListProductPage.BUTTON_DELETE_PRODUCT);
            Assert.assertEquals(deleteButton.getText(), "Delete");
        }
    }

    @Test
    public void TC_04_TextInForm() throws InterruptedException {
        WebElement addProductButton = driver.findElement(ViewListProductPage.BUTTON_ADD_PRODUCT);
        addProductButton.click();
        Thread.sleep(2000);

        WebElement formTitle = driver.findElement(ViewListProductPage.FORM_TITLE);
        Assert.assertEquals(formTitle.getText(), "Add Product");

        WebElement productNameLabel = driver.findElement(ViewListProductPage.FORM_PRODUCT_NAME_LABEL);
        Assert.assertEquals(productNameLabel.getText(), "Product Name *");

        WebElement priceLabel = driver.findElement(ViewListProductPage.FORM_PRICE_LABEL);
        Assert.assertEquals(priceLabel.getText(), "Price *");

        WebElement oldPriceLabel = driver.findElement(ViewListProductPage.FORM_OLD_PRICE_LABEL);
        Assert.assertEquals(oldPriceLabel.getText(), "Old Price *");

        WebElement brandLabel = driver.findElement(ViewListProductPage.FORM_BRAND_LABEL);
        Assert.assertEquals(brandLabel.getText(), "Brand *");

        WebElement categoryLabel = driver.findElement(ViewListProductPage.FORM_CATEGORY_LABEL);
        Assert.assertEquals(categoryLabel.getText(), "Category *");

        WebElement descriptionLabel = driver.findElement(ViewListProductPage.FORM_DESCRIPTION_LABEL);
        Assert.assertEquals(descriptionLabel.getText(), "Description *");

        WebElement imagesLabel = driver.findElement(ViewListProductPage.FORM_IMAGES_LABEL);
        Assert.assertEquals(imagesLabel.getText(), "Image *");

        WebElement statusLabel = driver.findElement(ViewListProductPage.FORM_STATUS_LABEL);
        Assert.assertEquals(statusLabel.getText(), "Status *");

        WebElement addProductButtonInForm = driver.findElement(ViewListProductPage.FORM_ADD_PRODUCT_BUTTON);
        Assert.assertEquals(addProductButtonInForm.getText(), "Add Product");

        WebElement closeButton = driver.findElement(ViewListProductPage.FORM_CLOSE_BUTTON);
        closeButton.click();
        Thread.sleep(3000);
    }
    @Test
    public void TC_05_CompareData() throws InterruptedException {
        List<String> keysToExtract = new ArrayList<>();
        keysToExtract.add("nameProduct");
        keysToExtract.add("description");
        keysToExtract.add("price");
        keysToExtract.add("oldprice");
        keysToExtract.add("brand");
        keysToExtract.add("category");
        keysToExtract.add("status");
        
        List<Map<String, Object>> dataList = jsonHelper.fetchDataFromAPI(API_URL, keysToExtract);
        
        // Extract data from the first item in the dataList (assuming there's at least one item)
        Map<String, Object> firstItem = dataList.get(0);
        String nameProductAPI = (String) firstItem.get("nameProduct");
        String descriptionAPI = (String) firstItem.get("description");
        String priceAPI = String.valueOf(firstItem.get("price"));
        String oldpriceAPI = String.valueOf(firstItem.get("oldprice"));
        
        // Extract the "brand" object
        Map<String, Object> brandObject = (Map<String, Object>) firstItem.get("brand");
        String brandName = (String) brandObject.get("name");
        
        // Extract the "category" object
        Map<String, Object> categoryObject = (Map<String, Object>) firstItem.get("category");
        String categoryName = (String) categoryObject.get("name");
        
        String statusAPI = (String) firstItem.get("status");
        //add array [][]
        String[][] data = new String[dataList.size()][7];
		for (int i = 0; i < dataList.size(); i++) {
			Map<String, Object> item = dataList.get(i);
			data[i][0] = (String) item.get("nameProduct");
			data[i][1] = (String) item.get("description");
			data[i][2] =  (String) ((Map<String, Object>) item.get("brand")).get("name");
			data[i][3] =(String) ((Map<String, Object>) item.get("category")).get("name");
			data[i][4] = String.valueOf(item.get("price")); 
			data[i][5] = String.valueOf(item.get("oldprice"));
			data[i][6] = (String) item.get("status");
		}
		// Check data
		int numberDataWrong = 0;
		int j[] = {2, 3, 5, 6, 7, 8,9};
		for (int i = 1; i <= data.length; i++) {
			String locator = "/html/body/div[1]/section/div[2]/div/div[3]/table/tbody/tr[" + i + "]";
			for (int J : j) {
				WebElement product = driver.findElement(By.xpath(locator + "/td[" + J + "]"));
				String productText = product.getText();
				if (J == 2) {
					//nameProduct
					if (!productText.equals(data[i - 1][0])) {
						System.out.println(productText);
						numberDataWrong++;
					}
					//description
				} else if (J == 3) {
					if (!productText.equals(data[i - 1][1])) {
						System.out.println(productText);
						numberDataWrong++;
					}
					//brand
				} else if (J == 5) {
					if (!productText.equals(data[i - 1][2])) {
						System.out.println(productText);
						numberDataWrong++;
					}
					//category
				} else if (J == 6) {
					if (!productText.equals(data[i - 1][3])) {
						System.out.println(productText);
						numberDataWrong++;
					}
					//price
				} else if (J == 7) {
					productText = productText.replace(".", "").replace("đ", "");
					if (!productText.equals(data[i - 1][4])) {
					
						System.out.println(productText);
						numberDataWrong++;
					}
					//oldprice
				} else if (J == 8) {
					productText = productText.replace(".", "").replace("đ", "");
					if (!productText.equals(data[i - 1][5])) {
						
						System.out.println(productText);
						numberDataWrong++;
					}
					//status
				} else if (J == 9) {
					WebElement status = driver.findElement(By.xpath(locator + "/td[" + J + "]/span"));
					productText = status.getText();
					// format . and đ
					
					System.out.println(productText);
					if (!productText.equals(data[i - 1][6])) {
						numberDataWrong++;
					}
				}
			}
		
		} Assert.assertEquals(numberDataWrong, 0);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
