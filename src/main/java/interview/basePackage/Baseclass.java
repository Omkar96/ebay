package interview.basePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field.Xpath;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import interview.utilPackage.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
    public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static Wait<WebDriver> fwait;

    public Baseclass() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"src\\main\\java\\interview\\configPackage\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

    public static void initialization(){
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

        driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT);

        driver.get("https://www.ebay.com/");
        
        try {
            driver.findElement(By.xpath("//button[@id='gdpr-banner-accept']")).click();
        } catch (ElementClickInterceptedException e) {
            driver.findElement(By.xpath("//button[@id='gdpr-banner-accept']")).click();
        }
        
    }

}
