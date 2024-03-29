package TH8;

import java.io.FileReader;
import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BT1 {

    WebDriver driver;

    @Test(dataProvider = "dp")
    public void Login(String data) throws Exception {

        String users[] = data.split(",");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://admin-demo.nopcommerce.com/");

        // Login with first element of the data array
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys(users[0]);
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(users[1]);
        driver.findElement(By.xpath("//button[@normalize-space()='Login']")).click();

        // Assertion to verify login successful
        String act_title = driver.getTitle();
        String exp_title = "Your store. Login";
        AssertJUnit.assertEquals(act_title, exp_title);

        driver.close();
    }

    @DataProvider(name = "dp")
    public String[] readJson() throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONParser jsonparser = new JSONParser();

        FileReader reader = new FileReader("D:\\testdata.json");
        Object obj = jsonparser.parse(reader);

        JSONObject userloginsJsonobj = (JSONObject) obj;
        JSONArray userloginsArray = (JSONArray) userloginsJsonobj.get("userlogins");

        String arr[] = new String[userloginsArray.size()];
        for (int i = 0; i < userloginsArray.size(); i++) {

            JSONObject users = (JSONObject) userloginsArray.get(i);
            String user = (String) users.get("username");

            arr[i] = user + "," + (String) users.get("password");

        }
        return arr;

    }

    @BeforeClass
    public void setup() {
    	String projectPath = System.getProperty("user.dir");
        String osName = System.getProperty("os.name");

        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }
}
