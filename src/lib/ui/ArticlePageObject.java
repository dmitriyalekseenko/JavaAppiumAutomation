package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "id:org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "xpath://*[contains(@text, 'View page in browser')]",
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    MY_SAVED_LIST = "org.wikipedia:id/item_container";

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
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
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

    public void closeArticle()
    {
        this.waitForElementAndClick(
               CLOSE_ARTICLE_BUTTON,
                "Cannot find 'X' button",
                5
        );
    }

}
