package interview.pagesPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import interview.basePackage.Baseclass;

public class Electronics extends Baseclass {
    
    @FindBy(xpath = "//div[string()='Cell Phones & Smartphones']")
    WebElement CellPhones_and_Smartphones;

    public Electronics(){
        PageFactory.initElements(driver, this);
    }

    public void GoTo_Electronics_SubCategory(String Electronics_Subcategory){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='b-visualnav__grid']/a")));
        driver.findElement(By.xpath("//div[string()='"+Electronics_Subcategory+"']/parent::a")).click();

    }
}
