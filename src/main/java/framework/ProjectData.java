package framework;

import enums.OsType;

import static framework.FrameworkUtils.getFrameworkProperty;

public class ProjectData {
	public static String env = System.getProperty("env");
	public static boolean runFromCi = false;
    public static OsType os = OsType.valueOf(System.getProperty("os").toUpperCase());
    public static String app_path;
    public static String app_file_name;
    public static boolean showMessage = Boolean.parseBoolean(getFrameworkProperty("logger", "displayMessages"));
}
