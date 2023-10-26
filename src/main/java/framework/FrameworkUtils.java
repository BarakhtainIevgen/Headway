package framework;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static framework.ProjectData.showMessage;

public class FrameworkUtils {
	public static String ANSI_RESET = "\u001B[0m";
	public static String ANSI_BLACK = "\u001B[30m";
	public static String ANSI_RED = "\u001B[31m";
	public static String ANSI_GREEN = "\u001B[32m";
	public static String ANSI_YELLOW = "\u001B[33m";
	public static String ANSI_BLUE = "\u001B[34m";
	public static String ANSI_PURPLE = "\u001B[35m";
	public static String ANSI_CYAN = "\u001B[36m";
	public static String ANSI_WHITE = "\u001B[37m";

	public static void skipColoringLogText(){
		ANSI_RESET = "";
		ANSI_BLACK = "";
		ANSI_RED = "";
		ANSI_GREEN = "";
		ANSI_YELLOW = "";
		ANSI_BLUE = "";
		ANSI_PURPLE = "";
		ANSI_CYAN = "";
		ANSI_WHITE = "";
	}

	public static String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static String getFrameworkProperty(String section, String property) {

		String propertyValue = "";

		message(ANSI_GREEN + String.format("Read property '%s' from section '%s' in configuration", property, section) + ANSI_RESET);

		try {
			FileReader json = new FileReader(System.getProperty("user.dir") + "/src/main/java/framework/config.json");

			JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
			propertyValue = jsonObject.getAsJsonObject(section).get(property).getAsString();

		} catch (FileNotFoundException e) {
			message(ANSI_RED + "Configuration file (config.json) not found" + ANSI_RESET);
		} catch (Exception e) {
			message(ANSI_RED + "Property not found!" + ANSI_RESET);
		}

		return propertyValue;
	}

	public static String findTextUseRegExp(String str, String regexp) {
		String f = "";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(str);

		if (matcher.find()) {
			f = matcher.group(1);
		}

		return f;
	}

	public static void message(String message){
		if (showMessage){
			System.out.println(message);
		}
	}
}