package interview.pagesPackage;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.tool.XSTCTester.TestCaseResult;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import interview.basePackage.Baseclass;
import interview.utilPackage.TestUtil;

public class Smartphones extends Baseclass {
    
    @FindBy(xpath = "//span[string()='- Shop by Brand']//ancestor::button")
    WebElement shopbybrand_button;
    
    @FindBy(xpath = "//span[string()='Screen Size']//ancestor::div[@role='tab']")
    WebElement screensize;

    @FindBy(xpath = "//span[string()='Price']//ancestor::div[@role='tab']")
    WebElement price;

    @FindBy(xpath = "//span[string()='Item Location']//ancestor::div[@role='tab']")
    WebElement itemlocation;

    @FindBy(xpath = "//button[string()='Apply']")
    WebElement  applyfilter;

    @FindBy(xpath = "//li[@class='brm__item brm__item--applied']//button")
    WebElement appliedfilterbutton;

    TestUtil util = new TestUtil();

    public Smartphones(){
        PageFactory.initElements(driver, this);
    }

    Actions action = new Actions(driver);


    public void Open_Applyfilter_popup(){
        try {
            shopbybrand_button.click();
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            driver.findElement(By.xpath("//button[string()='All Filters']")).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[string()='Brand']")));
       
    }

    public void Close_Applyfilter_popup(){
        applyfilter.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[string()='Brand']")));
    }

    public void Applyfilter_ToScreenSize(String size){
        Open_Applyfilter_popup();
        //Go to Screensize element
        action.moveToElement(screensize).click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel']")));
        //check the desired checkbox
        driver.findElement(By.xpath("//span[string()='"+size+"']/parent::label/preceding-sibling::span/input")).click();

        Close_Applyfilter_popup();
    }

    public void Applyfilter_Price(String min, String max){
        Open_Applyfilter_popup();
        //Go to Price element
        action.moveToElement(price).click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel']")));
        //enter min and max values
        driver.findElement(By.xpath("//input[@aria-label='Minimum Value, US Dollar']")).sendKeys(min);
        driver.findElement(By.xpath("//input[@aria-label='Maximum Value, US Dollar']")).sendKeys(max);

        Close_Applyfilter_popup();
    }

    public void Applyfilter_ItemLocation(String itemloc){
        Open_Applyfilter_popup();
        //Go to Price element
        action.moveToElement(itemlocation).click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='c3-subPanel']")));

        driver.findElement(By.xpath("//label[string()='"+itemloc+"']/parent::span/span/input")).click();

        Close_Applyfilter_popup();
    }

    public void Check_Filter_IsApplied(){

        appliedfilterbutton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class='brm__item brm__item--applied']//button//parent::div/div/ul/li/a/span[@class='brm__item-label']")));
        util.Takescreenshot("AppliedFilters");
    }

}
