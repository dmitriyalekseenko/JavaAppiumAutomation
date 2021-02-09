package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    private static final  String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");


        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            SearchPageObject.waitForCancelButtonToAppear();
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.clickMyLists();

        if (Platform.getInstance().isIOS()) {
            NavigationUI.closeSyncPopupButton();
        }

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }

        MyListPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testSaveTwoArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");


        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Saved pages";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java");
        ArticlePageObject.waitForTitleElement();
        String article_title_2 = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addSecondArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
        MyListPageObject.waitForArticleToAppearByTitle(article_title_2);

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );

    }
}
