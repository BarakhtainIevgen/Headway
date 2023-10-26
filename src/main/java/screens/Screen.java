package screens;

import framework.App;
import framework.AppControl;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.FrameworkUtils.message;

public class Screen {

    public Screen returnBack(){
        System.out.println("Step: Click on Back button");
        App.driver.navigate().back();
        return this;
    }
    
    public Screen allowPopup() {
    	System.out.println("Step: Allow popup");
    	try {
    		App.driver.switchTo().alert().accept();
    	} catch (Exception ex) {
    		System.out.println("Step: Popup not found");
    	}
    	return this;
    }

/*
    public Page turnWiFiOff(){
        System.out.println("Step: WiFi - Turn Off");
        if (App.getNetworkConnection().isWiFiEnabled()) {
			App.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
		}
        return this;
    }

    public Page turnWiFiOn(){
        System.out.println("Step: WiFi - turn ON");
		if (!App.getNetworkConnection().isWiFiEnabled()) {
			App.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
		}
        return this;
    }

	public Page turn3gOff(){
		System.out.println("Step: 3G - Turn Off");
		if (App.getNetworkConnection().isDataEnabled()) {
			App.setConnection(new ConnectionStateBuilder().withDataDisabled().build());
		}
		return this;
	}

	public Page turn3gOn(){
		System.out.println("Step: 3G - turn ON");
		if (!App.getNetworkConnection().isDataEnabled()) {
			App.setConnection(new ConnectionStateBuilder().withDataEnabled().build());
		}
		return this;
	}


	public Page navigateToText (String text){

		System.out.println("Scroll to element with text '" + text + "' on page");

		try {
			App.driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
				".scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0))");
		} catch (Exception ex) {
			System.out.println("Warning: Cannot scroll to element with text '" + text + "' on page");
		}

		return this;
	}

	public WebElement findElementByText (String text){
		System.out.println("Find and scroll to element with text '" + text + "' on page");

		WebElement el = null;

		try {
			el = App.driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
				".scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0))");
		} catch (Exception ex) {
			System.out.println("Warning: Cannot scroll to element with text '" + text + "' on page");
		}

		return el;
	}
*/
	public boolean isTextPresentOnScreen (String text) {
		message("Verify visibility of element with text '" + text + "' on page");
		boolean res;

		try {
			res = App.driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
				".scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0))")).isDisplayed();
		} catch (Exception ex) {
			res = false;
		}

		return res;
	}

	public boolean isElementDisplayed (AppControl control) {
		message("Verify visibility of element");
		boolean res;

		try {
			res = control.isDisplayed();
		} catch (Exception ex) {
			res = false;
		}

		return res;
	}

	public void waiting(int seconds){
		System.out.println("Waiting '"+ seconds +"' seconds");
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception ex) {
			System.out.println("Something happened during waiting '"+ seconds +"' seconds");
		}
	}

	public void selectListItem(String itemName){
		System.out.println("Select list item: '" + itemName +"'");

		WebDriverWait wait = new WebDriverWait(App.driver, 60);

		if (App.getCurrentOs().equalsIgnoreCase("ios")) {

		} else {
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath(String.format("//android.widget.Button[@text='%s']", itemName))))
					.click();
		}
	}

	public void waitUnitElementDisplayed(String locator){
		WebDriverWait wait = new WebDriverWait(App.driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
	}
}


    