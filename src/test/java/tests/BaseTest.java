package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    WebDriver driver = null;

    //metoda za otvaranje chrome drivera
    public WebDriver openChromeDriver() {
        print("Opening Chrome Driver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"--ignore-certificate-errors"});
        options.addArguments(new String[]{"--disable-popup-blocking"});
//        options.addArguments(new String[]{"--incognito"});
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.dexy.co.rs/");
        return driver;
    }

    //print method
    public void print(String text) {
        System.out.println(text);
    }

    public boolean isCurrentURLEqualTo(String expectedUrl) {
        print("isCurrentURLEqualTo ( " + expectedUrl + " )");
        String currentUrl = driver.getCurrentUrl();
        print("User is on " + currentUrl);
        boolean b = currentUrl.equals(expectedUrl);
        return b;
    }


    public boolean verifySearchItemIsShown(String search, WebDriver driver) {
        List<WebElement> searchItems = driver.findElements(By.xpath("//div[@class = 'item sel-filter']"));
        ArrayList<String> searchItemText = new ArrayList<>();
        for(WebElement item :  searchItems) {
            String text = item.getText();
            searchItemText.add(text);
        }
        return searchItemText.contains(search);
    }
}