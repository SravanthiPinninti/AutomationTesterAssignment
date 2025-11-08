package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.CompleteProfile;
import utilities.ERNXUtility;

import java.time.Duration;

/**
 * Test case class for CompleteProfile functionality.
 */
public class TC002CompleteProfile {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TC002CompleteProfile.class);

    @BeforeClass
    public void setup() throws InterruptedException {
        logger.info("Setting up WebDriver for CompleteProfile tests");
        driver = ERNXUtility.getWebDriver();
    }

    @AfterClass
    public void tearDown() {
        logger.info("Closing WebDriver");
        driver.quit();
    }

    @Test
    public void emptyFormSubmission() {
        logger.info("Starting test: emptyFormSubmission");
        try {
            ERNXUtility.loginWithValidOTP(driver);
            CompleteProfile completeProfile = new CompleteProfile(driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("complete-profile"));

            completeProfile.clickNext();

            WebElement emptyFormErrorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'text-red')]")));
            logger.info("Empty form error message: " + emptyFormErrorMsgElement.getText());
            Assert.assertEquals("Name is required", emptyFormErrorMsgElement.getText());
        } catch (Exception e) {
            logger.error("Error in emptyFormSubmission: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }

    @Test
    public void validFormSubmission() {
        logger.info("Starting test: validFormSubmission");
        try {
            ERNXUtility.loginWithValidOTP(driver);
            CompleteProfile completeProfile = new CompleteProfile(driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("complete-profile"));

            completeProfile.setFirstName("Hari");
            completeProfile.setLastName("P");
            completeProfile.clickNext();

            wait.until(ExpectedConditions.urlContains("addchild"));
            Assert.assertTrue("Add Yourself Or Your Child button should be displayed", completeProfile.addChildElement.isDisplayed());

            completeProfile.addChild();
            logger.info("Successfully submitted valid profile form");
        } catch (Exception e) {
            logger.error("Error in validFormSubmission: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }
}