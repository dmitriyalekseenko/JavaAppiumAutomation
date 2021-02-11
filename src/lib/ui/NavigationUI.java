package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
    MY_LIST_LINK,
    POPUP_CLOSE_BUTTON;

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

    public void closeSyncPopupButton()
    {
        this.waitForElementAndClick(POPUP_CLOSE_BUTTON, "Cannot find close Sync pop-up button", 10);
    }
}
