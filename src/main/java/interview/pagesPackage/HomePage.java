package interview.pagesPackage;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import interview.basePackage.Baseclass;
import interview.utilPackage.TestUtil;

public class HomePage extends Baseclass {

    @FindBy(xpath = "//button[@id='gh-shop-a']")
    WebElement shopbycategory_button;

    @FindBy(xpath = "//input[@id='gh-ac']")
    WebElement searchbar;

    @FindBy(xpath = "//input[@id='gh-btn']")
    WebElement searchbutton;

    TestUtil util = new TestUtil();

    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    Actions action = new Actions(driver);

    public void GoTo_SubCategory(String subcategory){
        action.click(shopbycategory_button).moveToElement(driver.findElement(By.xpath("//*[string()='"+subcategory+"']/a"))).click().perform();;
    }

    public void Searchtext(String text){
        searchbar.clear();
        searchbar.sendKeys(text + "\n");
    }

    public void Search_In_Different_category(String text, String category){
        Searchtext(text);
        
        Select newcategory = new Select(driver.findElement(By.xpath("//div[@id='gh-cat-box']/select")));
        newcategory.selectByVisibleText(category);

        searchbutton.click();
    }

    public void Verify_Searched_Item(){
        String expected = searchbar.getAttribute("value");

        String actual = driver.findElement(By.xpath("//li[@class='s-item s-item__pl-on-bottom s-item--watch-at-corner']//h3[@class='s-item__title']")).getText();
        
        if(StringUtils.containsIgnoreCase(actual, expected)){
            System.out.println("Searching successful");
            util.Takescreenshot("SearchedItem");
        }else{
            System.out.println("Searching is not successful");
        }

    }

}
