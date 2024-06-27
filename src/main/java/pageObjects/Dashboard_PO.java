package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GlobalVars;

import java.time.Duration;
import java.util.List;

public class Dashboard_PO extends Base_PO{
    public Dashboard_PO(){
        super();
    }


    private @FindBy(className = "app_logo") WebElement dashboard_PageTitle;

    private @FindBy(css = "div[id='inventory_container'][class='inventory_container']") WebElement inventory_container;

    private @FindBy(className = "product_sort_container") WebElement product_sorting_combo;

    private By inventory_item = new By.ByClassName("inventory_item");


    private  By add_to_cart_button = new By.ByCssSelector("button[id^='add-to-cart']");
    public void verifyDashboardPageTitle(String headText){
        waitForElement_And_ValidateText(dashboard_PageTitle,headText);
    }

    public boolean isInventoryItemsVisible()
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        boolean inventoryListShowing = wait.until(ExpectedConditions.visibilityOf(inventory_container)).isDisplayed();

        return inventoryListShowing;
    }

    public void sortProduct(String sortingToApply){
        waitForSelectElement_And_SelectByName(product_sorting_combo,sortingToApply);
    }

    public String verifySortingIsAppliedSuccessfully(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(inventory_container)).findElements(inventory_item).get(0);

        String productText = element.findElement(inventory_title).getText();

        System.out.println(productText);
        return productText;
    }

    public void selectProductFromInventory(String productName){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVars.WEBDRIVER_DEFAULT_EXPLICIT_TIMEOUT));
        List<WebElement> element = wait.until(ExpectedConditions.visibilityOf(inventory_container)).findElements(inventory_item);

        for (WebElement elem:
             element) {
            WebElement productTitle = elem.findElement(inventory_title);
            if(productTitle.getText().equals(productName)){
                productTitle.click();
                break;
            }
        }
    }


}
