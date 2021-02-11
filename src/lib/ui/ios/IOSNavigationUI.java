package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI
{
    static {
        MY_LIST_LINK = "id:Saved";
        POPUP_CLOSE_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
    }

    public IOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

}
