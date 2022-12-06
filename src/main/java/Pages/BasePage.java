package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    WebDriver driver = null;

    //web elementi zajednicki za sve strane

    @FindBy(xpath = "//button[@class='cookie-agree 1']")
    WebElement cookieSlazemSeButton;

    @FindBy(className = "login-btn")
    WebElement prijaviteSeButton;

    @FindBy(className = "modal-content")
    WebElement prijavaModal;

    @FindBy(id = "login_email")
    WebElement loginEmailTextField;

    @FindBy(xpath = "//input[@id='cookie_info738']/parent::div")
    WebElement trajniCookieCheckbox;

    @FindBy(xpath = "//div[@title='Pretraži sajt']")
    WebElement pretraziSajtButton;

    @FindBy(id = "search-text")
    WebElement searchTextField;

    @FindBy(xpath = "//div[@class='block search-sugest']")
    WebElement searchResultsTable;

    @FindBy(xpath = "//label[@for='5_univerzalno']")
    WebElement filterByGenderUnisex;

    @FindBy(xpath = "//label[@for='3_uzrast-4-6-godina']")
    WebElement filterByAge4to6;

    @FindBy(xpath = "//label[@for='f_pricelist_15000']")
    WebElement filterByPriceUpTo15000;

    @FindBy(xpath = "//input[@id='5_univerzalno']")
    WebElement getFilterByGenderUnisexCheckbox;

    @FindBy(xpath = "//form[@id='filter_form']//button[text()='Pretraži']")
    WebElement searchFilterButton;


    @FindBy(className = "block products-listing product product-listing-items")
    WebElement resultsTable;


    //konstrukor mora da se napravi
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        clickSlazemSe();
    }

    public void clickSlazemSe() {
        waitForElement(cookieSlazemSeButton);
        trajniCookieCheckbox.click();
        cookieSlazemSeButton.click();

    }

    //metode nad web elementima
    public void clickPrijaviSeButton() {
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOf(prijaviteSeButton));
        waitForElement(prijaviteSeButton);
        prijaviteSeButton.click();
    }

    public void enterEmailInPrijavaModal(String email) {
        waitForElement(loginEmailTextField);
        assert isElementPresent(prijavaModal) : "Error. Modal is not open";
        loginEmailTextField.click();
        loginEmailTextField.sendKeys(email);
    }


    public void clickSearchButton() {
        pretraziSajtButton.click();
    }

    public void enterTextInSearchField(String text) {
        searchTextField.sendKeys(text);
        searchTextField.sendKeys(Keys.ENTER);
    }

    /**
     * This method clicks on item with specified name
     * @param itemName
     */
    public void clickOnItem(String itemName) {
        waitForElement(searchResultsTable);
        String customXpath = "//a[@title='" + itemName + "']";
        print(customXpath);
        List<WebElement> elements = driver.findElements(By.xpath(customXpath));
        assert elements.size()!=0 : "Array is empty";
        elements.get(0).click();
        String actualTitle = driver.findElement(By.xpath("//div[@class='block product-details-info']//div[@class='title']")).getText();
        assert itemName.contains(actualTitle) : "Error. Expected title to be: " + itemName + ".Actual: " + actualTitle;
    }

    public void clickUnisexGenderFilter() {
        waitForElement(filterByGenderUnisex);
        filterByGenderUnisex.click();
        sleep();
    }

    public void clickAge4to6Filter() {
        waitForElement(filterByGenderUnisex);
        filterByAge4to6.click();
        sleep();
    }

    public void clickPriceUpTo15000rFilter() {
        waitForElement(filterByGenderUnisex);
        filterByPriceUpTo15000.click();
        sleep();
    }

    public void clickSearchFilterButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", searchFilterButton);
        searchFilterButton.click();
        sleep();
    }
    public void checkUnisexAge4to6PriceUpTo15000() {
        this.clickUnisexGenderFilter();
        this.clickAge4to6Filter();
        this.clickPriceUpTo15000rFilter();
    }



    //neke helper metode

    //print method
    public void print(String text) {
        System.out.println(text);
    }

    //da li je neki web element prisutan nas stranici
    public boolean isElementPresent(WebElement element) {
        print("isCartNumberBadgePresent");
        try {
            boolean isPresent = element.isDisplayed();
            return true;
        } catch (Exception e) {
            print(e.getMessage());
            print("Element is not present on page");
            return  false;
        }
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sleep() {

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
