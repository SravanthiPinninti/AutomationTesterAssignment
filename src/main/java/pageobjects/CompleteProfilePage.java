package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompleteProfilePage extends BasePage {

    /**
     * Constructor for CompleteProfile.
     */
    public CompleteProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "first_name")
    private WebElement firstNameElement;

    @FindBy(name = "last_name")
    private WebElement lastNameElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnNext;

    @FindBy(xpath = "//button[text()='Add Yourself Or Your Child']")
    public WebElement addChildElement;


    // Set the first name in the profile form.
    public void setFirstName(String firstName) {
        firstNameElement.sendKeys(firstName);
    }

    // Set the last name in the profile form.
    public void setLastName(String lastName) {
        lastNameElement.sendKeys(lastName);
    }

    /**
     * Method to click the Next button in the profile form.
     */
    public void clickNext() {
        btnNext.click();
    }

    /**
     * Method to click the Add Child button.
     */
    public void addChild() {
        addChildElement.click();
    }
}