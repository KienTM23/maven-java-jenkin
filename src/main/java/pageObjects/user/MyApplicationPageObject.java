package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageObjects.navigation.SideBarMyAccountPageObject;

public class MyApplicationPageObject extends SideBarMyAccountPageObject {
    private WebDriver driver;
    public MyApplicationPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


}
