package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){

        super(driver);
    }
    // Element for entering email
    @FindBy(name = "email")
    WebElement txtEmail;

    // Button to proceed after entering email
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnNext;

    // Elements for OTP input fields
    @FindBy(id = "otp-0")
    WebElement txtOtp1;

    @FindBy(id = "otp-1")
    WebElement txtOtp2;

    @FindBy(id = "otp-2")
    WebElement txtOtp3;

    @FindBy(id = "otp-3")
    WebElement txtOtp4;

    // Button to submit login form
    @FindBy(xpath = "//button[text()='Sign In ']")
   public WebElement btnSignIn;

    // Method to set email address
    public void setEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    // Method to click the Next button
    public void clickNext() {
        btnNext.click();
    }

    // Method to input OTP
    public void setOtp(String[] otp) {
        for (int i = 0; i < otp.length; i++) {
            WebElement ele = driver.findElement(By.id("otp-" + i));
            ele.sendKeys(otp[i]);
        }
    }

    // Method to click the Sign In button
    public void clickSignIn() {
        btnSignIn.click();
    }

    // Method to retrieve validation message
    public String getValidationMsg() {
        return txtEmail.getAttribute("validationMessage");
    }

}