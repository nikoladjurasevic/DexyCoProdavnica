package tests;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import javax.swing.*;

public class LoginTest extends BaseTest{

    @Test
    public void testLoginWithValidCreds() {
        WebDriver driver = openChromeDriver();
        try {
            BasePage basePage = new BasePage(driver);
            basePage.clickPrijaviSeButton();
            basePage.enterEmailInPrijavaModal("tralalal");

        } finally {
//            driver.quit();
        }
    }


    /**
     * Search for item and clicking on it
     *
     * Test steps:
     * 1. Navigate to dexy.co.rs
     * 2. Enter an item name in search field
     * 3. From search results find that item and click on it
     *
     * Expected result:
     * 3. Verify that the same item you searched in step 2 is shown on item page
     */
    @Test
    public void testSearch() {
        driver = openChromeDriver();
        try {
            print("1.Navigate to dexy.co.rs");
            BasePage basePage = new BasePage(driver);

            print("2.Enter an item name in search field");
            basePage.clickSearchButton();
            basePage.enterTextInSearchField("lego duplo");

            print("3.From search results find that item and click on it");
            basePage.clickOnItem("LEGO DUPLO BAKERY ");

            print("Verify that the same item you searched in step 2 is shown on item page");
            String actualTitle = driver.findElement(By.xpath("//div[@class='block product-details-info']//div[@class='title']")).getText();
            assert "LEGO DUPLO BAKERY ".contains(actualTitle) : "Error. Expected title to be: " + "LEGO DUPLO BAKERY " + ".Actual: " + actualTitle;

        }finally {
            driver.quit();
        }
    }
    /**
     * Search for item, filter the results and click on first item
     *
     * Test steps:
     * 1. Navigate to dexy.co.rs
     * 2. Enter an item name in search field
     * 3. Filter the results from the menu on the left hand side.
     * 4. Choose gender to be unisex, choose age to be 4-6 years and choose price 5001-15000
     * 5. From search results find that item and click on it
     *
     * Expected result:
     * 4. Verify that item filter elements are shown
     * 5. Verify that  item withing specified criterias is found
     **/
    @Test
    public void testSearchWithFilter() {

        driver = openChromeDriver();
        try {
            print("1.Navigate to dexy.co.rs");
            BasePage basePage = new BasePage(driver);

            print("2.Enter an item name in search field");
            basePage.clickSearchButton();
            basePage.enterTextInSearchField("lego duplo");

            print("Filter the results from the menu on the left hand side.");
            basePage.checkUnisexAge4to6PriceUpTo15000();
//            basePage.clickSearchFilterButton();

            print("Verify that item filter elements are shown");
            assert verifySearchItemIsShown("univerzalno", driver) : "Error. Search item is not shown: " + "univerzalno ";

            assert verifySearchItemIsShown("uzrast-4-6-godina", driver) : "Error. Search item is not shown: " + "uzrast-4-6-godina  ";


        }finally {
//            driver.quit();
        }

    }

}
