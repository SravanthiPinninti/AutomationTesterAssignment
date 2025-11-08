package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddChildPage extends BasePage {

    /**
     * Constructor for AddChild.
     */
    public AddChildPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='Add Yourself Or Your Child']")
    private WebElement addChildElement;

    @FindBy(xpath = "//input[@placeholder='Nickname']")
    private WebElement nickNameElement;

    @FindBy(xpath = "//div/span[text()='female']")
    private WebElement femaleGenderElement;

    @FindBy(xpath = "//div/span[text()='male']")
    public WebElement maleGenderElement;

    @FindBy(xpath = "//button[text()='Next']")
    public WebElement btnNext;

    @FindBy(xpath = "//div[contains(@class,'rounded-lg border')]")
    private WebElement rewardElement;

    @FindBy(xpath = "//button[text()='Finish']")
    public WebElement btnFinish;

    @FindBy(xpath = "//div[@class='MuiBottomNavigation-root css-18ibs1n']")
    public WebElement bottomNavigationElement;

    /**
     * Method to click the Add Yourself or Your Child button.
     */
    public void addYourSelfOrChild() {
        addChildElement.click();
    }

    /**
     * Method to set the nickname in the form.
     *
     * @param nickName Nickname to be entered.
     */
    public void setNickName(String nickName) {
        nickNameElement.sendKeys(nickName);
    }

    /**
     * Method to select female gender.
     */
    public void selectGender() {
        femaleGenderElement.click();
    }

    /**
     * Method to click the Next button.
     */
    public void clickNext() {
        btnNext.click();
    }

    /**
     * Method to select a reward.
     */
    public void selectReward() {
        rewardElement.click();
    }

    /**
     * Method to click the Finish button.
     */
    public void clickFinish() {
        btnFinish.click();
    }
}