package screens;

import enums.OsType;
import framework.App;
import framework.AppControl;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.ProjectData.os;

public class StartScreen extends Screen {
    private AppControl btnLoginAndr =  new AppControl(By.xpath("//android.widget.TextView[@text='Log in']"));
    public StartScreen(){

    }

    public boolean waitUntilLoginPageApeared() {
        System.out.println("Step: Wait until Login Page appeared");
        boolean res = false;
        try {
            WebDriverWait wait = new WebDriverWait(App.driver, 60);
            wait.until(ExpectedConditions.visibilityOf(btnLoginAndr.returnElement()));
            res = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public StartScreen clickOnLoginButton(){
        System.out.println("Step: Click on Login button");
        if (os == OsType.IOS) {
        	//btnLoginIos.click();
    	} else {
    		btnLoginAndr.click();
    	}
        return this;
    }

}
