package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject
{
    private static final String
    MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";


    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find 'My list'",
                15
        );
    }
}
