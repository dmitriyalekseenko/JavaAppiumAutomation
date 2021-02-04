package tests.IOS;

import lib.IOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends IOSTestCase {

    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWays();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddAndEditLanguage();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForLearnMoreAbout();
        WelcomePageObject.clickGetStartedButton();
    }

}
