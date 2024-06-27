package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GlobalVars;

public class Saucedemo_Login_PO extends Base_PO{
    public Saucedemo_Login_PO(){
        super();
    }

    private @FindBy(id="user-name") WebElement username_TextField;
    private @FindBy(id="password") WebElement password_TextField;
    private @FindBy(id="login-button") WebElement login_SubmitField;

    private @FindBy(className = "login_logo") WebElement login_Text;

    private  @FindBy(css = "h3[data-test='error']") WebElement wrong_Credentials;

    public void navigateTo_SauceDemo_Website(){
        navigateTo_URL(GlobalVars.BASE_URL);
    }

    public void setUserName(String username){
        sendKeys(username_TextField,username);
    }

    public void setPassword(String password){
        sendKeys(password_TextField,password);
    }

    public void clickOn_LoginButton(){
        waitForWebElementAndClick(login_SubmitField);
    }

    public void verifyHeaderText(String headText){
        waitForElement_And_ValidateText(login_Text,headText);
    }

    public void verifyWrongCredentialsMessage(String message){
        waitForElement_And_ValidateText(wrong_Credentials,message);
    }
}
