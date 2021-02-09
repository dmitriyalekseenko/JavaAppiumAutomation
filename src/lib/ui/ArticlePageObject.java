package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;
abstract public class ArticlePageObject extends MainPageObject
{
   protected static String
    TITLE,
    FOOTER_ELEMENT,
    OPTIONS_BUTTON,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    CLOSE_ARTICLE_BUTTON,
    MY_SAVED_LIST;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }

    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More options'",
                15
        );

        this.waitForElementAndClick(
               OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' in 'More options'",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button 'GOT IT'",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find input field",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button",
                5
        );
    }

    public void addSecondArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More options'",
                15
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' in 'More options'",
                15
        );

        this.waitForElementAndClick(
                MY_SAVED_LIST,
                "Cannot find list" + name_of_folder,
                15
        );

    }

    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
               CLOSE_ARTICLE_BUTTON,
                "Cannot find 'X' button",
                5
        );
    }

}
