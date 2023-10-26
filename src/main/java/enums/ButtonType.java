package enums;

public enum ButtonType {
    REMEMBER ("Remember"),
    SHARE ("Share");

    private String buttonName;

    public String getButtonName()
    {
        return this.buttonName;
    }

    ButtonType(String buttonName)
    {
        this.buttonName = buttonName;
    }
}
