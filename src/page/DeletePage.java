package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeletePage {
	private WebDriver driver;
	private WebDriverWait wait;

	public DeletePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
	}

	public DeletePage() {
		
	}
	// Locators
	public static final String DELETE_PRODUCT_BUTTON = "/html/body/div/section/div[2]/div/div[3]/table/tbody/tr[1]/td[10]/span[2]";

}
