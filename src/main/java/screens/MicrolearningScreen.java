package screens;

import enums.OsType;
import framework.App;
import framework.AppControl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static framework.ProjectData.os;

public class MicrolearningScreen extends Screen {
    private AppControl btnShare =  new AppControl(By.xpath("//android.widget.Button[@resource-id='com.headway.books:id/btn_share']"));
    private AppControl btnRemember =  new AppControl(By.xpath("//android.widget.Button[@resource-id='com.headway.books:id/btn_repetition_add']"));
    private AppControl btnClose =  new AppControl(By.xpath("//android.widget.ImageView[@resource-id='com.headway.books:id/btn_back']"));
    private AppControl pnlCardView = new AppControl(By.xpath("//androidx.cardview.widget.CardView[@resource-id='com.headway.books:id/ctnr_insight_book']"));
    private AppControl pnlContent = new AppControl(By.xpath("//android.widget.TextView[@resource-id='com.headway.books:id/tv_content']"));
    public MicrolearningScreen(){

    }

    public boolean waitUntilLoginPageApeared() {
        System.out.println("Step: Wait until Login Page appeared");
        boolean res = false;
        try {
            WebDriverWait wait = new WebDriverWait(App.driver, 60);
            wait.until(ExpectedConditions.visibilityOf(btnClose.returnElement()));
            res = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public MicrolearningScreen clickOnCloseButton(){
        btnClose.click();
        return this;
    }

    public boolean isVisiblePnlContent(){
        return isElementDisplayed(pnlContent);
    }

    public boolean isVisibleCardView(){
        return isElementDisplayed(pnlCardView);
    }

    public boolean isVisibleBtnShare(){
        return isElementDisplayed(btnShare);
    }

    public boolean isVisibleBtnRemember(){
        return isElementDisplayed(btnRemember);
    }
}
