package screens;

import enums.OsType;
import framework.App;
import framework.AppControl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static framework.ProjectData.os;

public class HomeScreen extends Screen {
    private AppControl lblDiscover =  new AppControl(By.xpath("//android.widget.TextView[@text='Discover']"));
    private AppControl btnMicrolearningItem =  new AppControl(By.xpath("//android.widget.ImageView[@resource-id='com.headway.books:id/iv_book']"));
    private List<?> btnMicrolearningItems = App.driver.findElements(By.xpath("//android.widget.ImageView[@resource-id='com.headway.books:id/iv_book']"));
    public HomeScreen(){

    }

    public boolean waitUntilLoginPageApeared() {
        System.out.println("Step: Wait until Login Page appeared");
        boolean res = false;
        try {
            WebDriverWait wait = new WebDriverWait(App.driver, 60);
            wait.until(ExpectedConditions.visibilityOf(lblDiscover.returnElement()));
            res = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public int getMicrolearningItemsCount(){
        return btnMicrolearningItems.size();
    }

    public HomeScreen clickOnMicrolearningItem(){
        btnMicrolearningItem.click();
        return this;
    }

}
