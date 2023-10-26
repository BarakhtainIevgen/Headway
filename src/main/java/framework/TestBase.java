package framework;

import enums.OsType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static framework.FrameworkUtils.getFrameworkProperty;
import static framework.ProjectData.*;

public class TestBase {

    public static void setUpAppium() throws IOException {
		String config_section = "install_files";

		try {
			runFromCi = Boolean.parseBoolean(System.getProperty("runFromCi"));
		} catch (Exception ex) {
			runFromCi = false;
		}

        System.out.println("Setup application - start - " + FrameworkUtils.getCurrentTime());		

        // Define the URL for the Appium server
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);

		if (runFromCi){
			app_path = getFrameworkProperty(config_section, "path_ci");
			FrameworkUtils.skipColoringLogText();
		} else {
			app_path = getFrameworkProperty(config_section,"path_local");
		}

		if (os == OsType.IOS) {
			app_file_name = getFrameworkProperty(config_section,"app_name");
		} else {
			app_file_name = getFrameworkProperty(config_section,"apk_name");
		}


        DesiredCapabilities capabilities = new DesiredCapabilities();

		if (os == OsType.IOS) {
        	capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        	capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.1");
        	capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");	
        	capabilities.setCapability(MobileCapabilityType.APP, app_path + app_file_name);
        	capabilities.setCapability("bundleId", "com.headway.app");
        	capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        	capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        	capabilities.setCapability("autoAcceptAlerts", true);
        	capabilities.setCapability("connectHardwareKeyboard", false);
        	App.driver = new IOSDriver<MobileElement>(url, capabilities );
        } else {
            File app = new File(app_path, app_file_name);
            System.out.println("app.getAbsolutePath() = '" + app.getAbsolutePath() + "'");
            
	        // Create an empty DesiredCapabilities object (leave blank for Device Farm)	
	        capabilities.setCapability("automationName", "Appium");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("deviceName", "Simulator");
	        capabilities.setCapability("app", app.getAbsolutePath());
	        capabilities.setCapability("unicodeKeyboard", true);
	        capabilities.setCapability("skipUnlock", true);
			capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);
	        capabilities.setCapability("clearSystemFiles", true);
			//capabilities.setCapability("fullReset", true);
			capabilities.setCapability("newCommandTimeout", 180);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			App.driver = new AndroidDriver<MobileElement>(url, capabilities);;
        }

        // Use a higher value if your mobile elements take time to show up
        App.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        if(App.isLocked()){
            App.unlockDevice();
        }

        System.out.println("Setup application - finish - " + FrameworkUtils.getCurrentTime());
    }

    // After the Test Suite runs, quit the App
    public static void tearDownAppium() throws InterruptedException {
        System.out.println("Tear down APP - start - "  + FrameworkUtils.getCurrentTime());
        App.driver.quit();
        System.out.println("Tear down APP - finish - " + FrameworkUtils.getCurrentTime());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            try {
				System.out.println("Creating screenshot - start - " + FrameworkUtils.getCurrentTime());

				String folder_name = System.getProperty("user.dir") + "/target/surefire-reports";
				File f = ((TakesScreenshot) App.driver).getScreenshotAs(OutputType.FILE);
				//Date format fot screenshot file name
				DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
				//create dir with given folder name
				new File(folder_name).mkdir();
				//Setting file name
				String file_name = df.format(new Date()) + ".png";
				//coppy screenshot file into screenshot folder.
				FileUtils.copyFile(f, new File(folder_name + "/" + file_name));

				System.out.println("Screenshot: " + folder_name + "/" + file_name);
				System.out.println("Creating screenshot - finish - " + FrameworkUtils.getCurrentTime());

				restartApp();
			} catch (Exception ex) {
            	System.out.println("Something went wrong during creation screenshot. Screenshot not created.\n" + ex.getMessage());
				restartApp();
            }
        }
    }

    public void restartApp(){
		System.out.println("Restart APP - start - " + FrameworkUtils.getCurrentTime());
		App.driver.resetApp();
		System.out.println("Restart APP - finish - " + FrameworkUtils.getCurrentTime());
	}
}
