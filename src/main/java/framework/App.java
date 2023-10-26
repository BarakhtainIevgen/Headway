package framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.LocksDevice;
import org.openqa.selenium.chrome.ChromeDriver;
import screens.*;

public class App {
    public static ChromeDriver chromeDriver;
    public static AppiumDriver<?> driver;
    private static String currentOS;

    public static String getCurrentOs(){
        return currentOS;
    }

    public static void setCurrentOs(String osName){
        currentOS = osName;
    }

    /**
     * Check if the device is locked.
     *
     * @return true if device is locked. False otherwise
     */
    public static boolean isLocked() {
        return ((LocksDevice) driver).isDeviceLocked();
    }

	/**
	 * Unlock device.
	 *
	 * @return void
	 */
	public static void unlockDevice() {
		((LocksDevice) driver).unlockDevice();
	}

    //endregion

    //region App POMs

    // Pages objects

    public static StartScreen StartScreen() {
        return new StartScreen();
    }
    public static LoginScreen LoginScreen() {
        return new LoginScreen();
    }
    public static HomeScreen HomeScreen() {
        return new HomeScreen();
    }
    public static MicrolearningScreen MicrolearningScreen() {
        return new MicrolearningScreen();
    }

}

