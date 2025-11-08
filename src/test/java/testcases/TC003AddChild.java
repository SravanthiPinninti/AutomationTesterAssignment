package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.AddChildPage;
import utilities.ERNXUtility;

import java.time.Duration;

/**
 * Test case class for AddChild functionality.
 */
public class TC003AddChild {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TC003AddChild.class);

    @BeforeTest
    public void setup() throws InterruptedException {
        logger.info("Setting up WebDriver for AddChild tests");
        driver = ERNXUtility.getWebDriver();
    }
    @AfterClass
    public void tearDown() {
        logger.info("Closing WebDriver");
        driver.quit();
    }
    @Test
    public void formSubmission() {
        logger.info("Starting test: formSubmission");
        try {
            ERNXUtility.loginWithValidOTP(driver);
            Thread.sleep(3000);
            ERNXUtility.validCompleteProfileSubmission(driver);

            AddChildPage addChild = new AddChildPage(driver);
            addChild.addYourSelfOrChild();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlContains("addchild/basics"));

            // Verify male gender is not selected
            boolean genderMaleIsSelected = addChild.maleGenderElement.isSelected();
            Assert.assertFalse("Male gender should not be selected by default", genderMaleIsSelected);

            // Verify Next button is not enabled
            boolean nextButtonIsEnabled = addChild.btnNext.isEnabled();
            Assert.assertFalse("Next button should not be enabled when form is incomplete", nextButtonIsEnabled);

            // Fill form and navigate
            addChild.setNickName("Hari");
            addChild.clickNext();
            addChild.clickNext();

            addChild.selectReward();
            addChild.clickNext();

            addChild.clickFinish();

            // Verify bottom navigation is displayed
            Assert.assertTrue("Bottom navigation should be displayed after finishing", addChild.bottomNavigationElement.isDisplayed());
            logger.info("Bottom navigation is displayed successfully");
        } catch (Exception e) {
            logger.error("Error in emptyFormSubmission: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }
}