package enums;

public enum OsType {
    ANDROID ("android"),
    IOS ("ios");

    private String osName;

    public String getOsName()
    {
        return this.osName;
    }

    OsType(String osName)
    {
        this.osName = osName;
    }
}
