package pageObjects;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.GlobalVars;

import java.time.Duration;

import static driver.DriverFactory.getDriver;

public class Base_PO {
    public Base_PO() {
        //  CONSTRUCTOR Logic here
        PageFactory.initElements(getDriver(), this);
    }
    protected By inventory_title =new  By.ByClassName("inventory_item_name");

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    protected void navigateTo_URL(String url) {
        getDriver().get(url);
    }

    protected void sendKeys(By by, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }
    protected void sendKeys(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }

    protected void waitForWebElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    protected void waitForWebElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void waitForElement_And_ValidateText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        String actualText = wait.until(ExpectedConditions.visibilityOf(element)).getText();

        Assert.assertEquals(actualText, text,text+" is not visible");
    }

    protected  void waitForElement_And_ValidateText (By selector, String text){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        String actualText = wait.until(ExpectedConditions.elementToBeClickable(selector)).getText();

        Assert.assertEquals(actualText, text,text+" is not visible");
    }

    protected void waitForSelectElement_And_SelectByName(WebElement element, String selectName){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));

        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByVisibleText(selectName);
    }

}
