package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.MainCalls;
import org.testng.Assert;

public class SauceDemo_Login_Steps{

    @Given("Sam is on Sauce demo application")
    public void NavigateToSauceDemoWebsite() {
        MainCalls.getSd_LoginPO().navigateTo_SauceDemo_Website();
    }
    @Given("Title page {string} of the application is visible")
    public void VerifyNavigationIsSuccessful(String title) {
        MainCalls.getSd_LoginPO().verifyHeaderText(title);
    }
    @When("Sam enters username {string}")
    public void EnterUserName(String username) {
        MainCalls.getSd_LoginPO().setUserName(username);
    }
    @When("Sam enters password {string}")
    public void EnterPassword(String password) {
        MainCalls.getSd_LoginPO().setPassword(password);
    }
    @When("Click on Submit button")
    public void ClickLoginButton() {
        MainCalls.getSd_LoginPO().clickOn_LoginButton();
    }
    @Then("Login should be successful")
    public void VerifyLoginIsSuccessful() {
        MainCalls.getSd_DashboardPO().verifyDashboardPageTitle("Swag Labs");
    }
    @Then("Product listing should be visible")
    public void VerifyProductListingIsVisible() {
        Assert.assertEquals(MainCalls.getSd_DashboardPO().isInventoryItemsVisible(),true,"Inventory Listing Is not Available.");
    }
    @Then("Error Message {string} should be visible")
    public void WrongCredentialsMessageShouldBeVisible(String message) {
        MainCalls.getSd_LoginPO().verifyWrongCredentialsMessage(message);
    }
}

