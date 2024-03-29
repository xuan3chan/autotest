package page;

import org.openqa.selenium.By;

public class ViewListProductPage {
    // Sidebar
    public static final By SIDEBAR_PRODUCT_TEXT = By.xpath("//*[@id=\"sidebar\"]/div[4]/span[2]");

    // Header
    public static final By HEADER_H1 = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[2]/h1");
    public static final By HEADER_PRODUCT_TABLE = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[3]/table");
    public static final By TABLE_HEADERS = By.tagName("th");

    // Buttons
    public static final By BUTTON_ADD_PRODUCT = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[1]/span");
    public static final By BUTTON_EDIT_PRODUCT = By.xpath("//span[text()='Edit']");
    public static final By BUTTON_DELETE_PRODUCT = By.xpath("//span[text()='Delete']");

    // Form
    public static final By FORM_TITLE = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/div/span[1]");
    public static final By FORM_PRODUCT_NAME_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[1]/div/label");
    public static final By FORM_PRICE_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[1]/label");
    public static final By FORM_OLD_PRICE_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[2]/div[2]/label");
    public static final By FORM_BRAND_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[1]/label");
    public static final By FORM_CATEGORY_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[3]/div[2]/label");
    public static final By FORM_DESCRIPTION_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[4]/div/label");
    public static final By FORM_IMAGES_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[1]/label");
    public static final By FORM_STATUS_LABEL = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[5]/div[2]/label");
    public static final By FORM_ADD_PRODUCT_BUTTON = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/form/div[6]/button");
    public static final By FORM_CLOSE_BUTTON = By.xpath("//*[@id=\"root\"]/section/div[2]/div/div[1]/div[3]/div/div/span[2]");

    // Login
    public static final By USERNAME_FIELD = By.id("accountName");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BUTTON = By.xpath("//*[@id=\"root\"]/div/div/div/button");
    //List
    public static final By PRODUCT_NAME = By.xpath("/html/body/div/section/div[2]/div/div[3]/table/tbody/tr[1]/td[2]");
    public static final By TABLE_ROWS = By.xpath("/html/body/div[1]/section/div[2]/div/div[3]/table/tbody/tr");
    public static final By TABLE_DATA = By.xpath("/html/body/div[1]/section/div[2]/div/div[3]/table/tbody/tr/td");
}
