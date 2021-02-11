package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    DELETE_ARTICLE_FROM_LISTS;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name" + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        if (Platform.getInstance().isAndroid()) {
            String article_xpath = getFolderXpathByName(article_title);
            this.waitForElementPresent(article_xpath,"Cannot find saved article by title" + article_title, 15);
        } else {
            String article_xpath = getSavedArticleXpathByTitle(article_title);
            this.waitForElementPresent(article_xpath,"Cannot find saved article by title" + article_title, 15);
        }


    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        if (Platform.getInstance().isAndroid()) {
            String article_xpath = getFolderXpathByName(article_title);
            this.waitForElementNotPresent(article_xpath,"Saved article still presence with title" + article_title, 15);
        } else {
            String article_xpath = getSavedArticleXpathByTitle(article_title);
            this.waitForElementNotPresent(article_xpath,"Saved article still presence with title" + article_title, 15);
        }


    }

    public void swipeByArticleToDelete(String article_title)
    {
        if (Platform.getInstance().isAndroid()) {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getFolderXpathByName(article_title);
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find save article"
            );
            this.waitForArticleToDisappearByTitle(article_title);
        } else {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getSavedArticleXpathByTitle(article_title);
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find save article"
            );
            this.waitForElementAndClick(DELETE_ARTICLE_FROM_LISTS, "Cannot find 'Delete' button article from lists", 10);
            this.waitForArticleToDisappearByTitle(article_title);
        }

    }
}
