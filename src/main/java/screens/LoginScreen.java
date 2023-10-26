package screens;

import enums.OsType;
import framework.App;
import framework.AppControl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.ProjectData.os;

public class LoginScreen extends Screen {

    private AppControl txtEmailAndr = new AppControl(By.id("com.headway.books:id/et_email"));
    private AppControl txtPasswordAndr =  new AppControl(By.id("com.headway.books:id/et_password"));
    private AppControl btnLoginAndr =  new AppControl(By.id("com.headway.books:id/btn_login"));
    private AppControl lnkResetPasswordAndr = new AppControl(By.xpath("//android.widget.TextView[@text='Forgot Password?']"));
    private AppControl lstCompanyAndr = new AppControl(By.xpath("//android.view.View[@text='Select a Company']"));
    private AppControl lstCommunityAndr = new AppControl(By.xpath("//android.view.View[@text='Select a Community']"));
    private AppControl lblHelpAndSupportAndr = new AppControl(By.xpath("//android.widget.TextView[@text='Help and Support']"));
    private AppControl btnSignUpAndr =  new AppControl(By.xpath("//android.widget.Button[@text='SIGN UP']"));

    
    private AppControl txtEmailIos = new AppControl(By.xpath("//XCUIElementTypeTextField"));
    private AppControl txtPasswordIos =  new AppControl(By.xpath("//XCUIElementTypeSecureTextField"));  
    private AppControl btnLoginIos =  new AppControl(By.xpath("//XCUIElementTypeButton"));
    private AppControl lnkResetPassword = new AppControl(By.xpath("//android.widget.TextView[@text='Forgot Password?']"));
    private AppControl alertLoginHeader = new AppControl(By.xpath("//android.view.View[starts-with(@resource-id, 'alert') and ends-with(@resource-id, 'hdr')]"));
    private AppControl alertLoginMessage = new AppControl(By.xpath("//android.view.View[starts-with(@resource-id, 'alert') and ends-with(@resource-id, 'msg')]"));
    private AppControl alertLoginButton = new AppControl(By.xpath("//android.view.View[contains(@resource-id,'ion-overlay-')]/android.view.View/android.widget.Button"));

    
    public LoginScreen(){

    }

    public boolean isVisible(){
        System.out.println("Step: Verify visibility of Login page");
        boolean res = false;
        
        if (App.getCurrentOs().equalsIgnoreCase("ios")) {
        	res = (txtEmailIos.isDisplayed() && txtPasswordIos.isDisplayed());
    	} else {
    		res = (txtEmailAndr.isDisplayed() && txtPasswordAndr.isDisplayed());
    	}
		return res;
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

    public boolean waitUntilLoginPageDisapeared() {
        System.out.println("Step: Wait until Login Page disappeared");
        boolean res = false;
        try {
            WebDriverWait wait = new WebDriverWait(App.driver, 60);
            wait.until(ExpectedConditions.invisibilityOf(lnkResetPassword.returnElement()));
            res = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return res;
    }

    public LoginScreen loginUser(String email, String password){
        System.out.println("Step: Login into App");

        setEmail(email)
                .setPassword(password)
                .clickOnLoginButton();

        return this;
    }

    public LoginScreen setEmail(String email){
        System.out.println("Step: Set email");
        if (os == OsType.IOS) {
        	txtEmailIos.clear();
    		txtEmailIos.sendKeys(email);
    	} else {
    		txtEmailAndr.clear();
    		txtEmailAndr.sendKeys(email);
    	}
        return this;
    }

    public LoginScreen setPassword(String password){
        System.out.println("Step: Set password");
        if (os == OsType.IOS) {
        	txtPasswordIos.clear();
    		txtPasswordIos.sendKeys(password);
    	} else {
    		txtPasswordAndr.clear();
    		txtPasswordAndr.sendKeys(password);
    	}
        return this;
    }

    public LoginScreen clickOnLoginButton(){
        System.out.println("Step: Click on Login button");
        if (os == OsType.IOS) {
        	btnLoginIos.click();
    	} else {
    		btnLoginAndr.click();
    	}
        return this;
    }

}
