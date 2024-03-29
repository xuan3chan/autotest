package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    public static final String EDIT_PRODUCT_BUTTON = "/html/body/div/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[1]";
    public static final String PRODUCT_NAME_FIELD = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[1]/div/input";
    public static final String PRICE_FIELD = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[2]/div[1]/input";
    public static final String OLD_PRICE_FIELD = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[2]/div[2]/input";
    public static final String BRAND_DROPDOWN = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[2]/div[1]/select";
    public static final String CATEGORY_DROPDOWN = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[2]/div[2]/select";
    public static final String DESCRIPTION_FIELD = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[3]/textarea";
    public static final String STATUS_DROPDOWN = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[1]/div[2]/select";
    public static final String IMAGE_UPLOAD_BUTTON = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[3]/div[1]/div[1]/input";
    public static final String UPDATE_PRODUCT_BUTTON_FORM = "/html/body/div[1]/section/div[2]/div/div[1]/div[5]/div/form/div[4]/button";

    
}
