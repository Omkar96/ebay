package interview.utilPackage;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import interview.basePackage.Baseclass;

public class TestUtil extends Baseclass{
    public static int PAGE_LOAD_TIMEOUT = 10;
    public static int IMPLICIT_WAIT = 10;
    public static int EXPLICIT_WAIT = 10;

    public void Takescreenshot(String filename){
        File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(Screenshot, new File("src\\main\\java\\interview\\Screenshots\\"+filename+"_"+Calendar.getInstance().get(Calendar.HOUR)+"_"+Calendar.getInstance().get(Calendar.MINUTE)+".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
