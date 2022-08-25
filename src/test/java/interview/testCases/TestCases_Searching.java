package interview.testCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import interview.basePackage.Baseclass;
import interview.pagesPackage.Electronics;
import interview.pagesPackage.HomePage;
import interview.pagesPackage.Smartphones;

public class TestCases_Searching extends Baseclass {
    
    Electronics ele;
    HomePage home;
    Smartphones phone;

    public TestCases_Searching(){
        super();
    }

    @BeforeSuite
    public void setup(){
        initialization();
        ele = new Electronics();
        home = new HomePage();
        phone = new Smartphones();
    }

    @Test(priority = 1)
    public void Access_Product_using_Filter(){
        home.GoTo_SubCategory("Cell phones & accessories");
        ele.GoTo_Electronics_SubCategory("Cell Phones & Smartphones");
        phone.Applyfilter_ToScreenSize("6 in or More");
        phone.Applyfilter_Price("0", "10000");
        phone.Applyfilter_ItemLocation("Asia");
        phone.Check_Filter_IsApplied();
    }

    @Test
    public void Access_Product_Via_Search(){
        home.Search_In_Different_category("Macbook", "Computers/Tablets & Networking");
        home.Verify_Searched_Item();
    }

    @AfterSuite
    public void teardown(){
        driver.quit();
    }

}
