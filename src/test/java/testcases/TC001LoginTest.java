package testcases;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import utilities.ERNXUtility;

import java.time.Duration;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC001LoginTest {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TC001LoginTest.class);
    @BeforeClass
    public void setup() throws InterruptedException {
        logger.info("Setting up WebDriver");
        driver = ERNXUtility.getWebDriver();
    }

    @AfterClass
    public void tearDown() {
        logger.info("Closing WebDriver");
        driver.quit();
    }

    @Test
    public void testLoginWithEmptyEmail() {
        logger.info("Starting test: testLoginWithEmptyEmail");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickNext();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement emptyEmailErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'text-red')]")));
            logger.info("Empty email error message: " + emptyEmailErrorElement.getText());
            Assert.assertEquals("Email is required", emptyEmailErrorElement.getText());
        } catch (Exception e) {
            logger.error("Error in testLoginWithEmptyEmail: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }

    @Test
    public void testLoginWithInvalidEmail() {
        logger.info("Starting test: testLoginWithInvalidEmail");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail("test");
            loginPage.clickNext();
            String expectedInvalidMsg = "Please include an '@' in the email address. 'test' is missing an '@'.";
            Assert.assertEquals(expectedInvalidMsg, loginPage.getValidationMsg());
        } catch (Exception e) {
            logger.error("Error in testLoginWithInvalidEmail: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }

    @Test
    public void testLoginWithValidEmail() {
        logger.info("Starting test: testLoginWithValidEmail");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail("test@gmail.com");
            loginPage.clickNext();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement otp1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otp-0")));
            Assert.assertEquals(driver.getCurrentUrl(), "https://ernx-consumer.vercel.app/login/otp");
            Assert.assertTrue(otp1Element.isDisplayed());
        } catch (Exception e) {
            logger.error("Error in testLoginWithValidEmail: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }

    @Test
    public void testLoginWithInValidOTP() {
        logger.info("Starting test: testLoginWithInValidOTP");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail("test@gmail.com");
            loginPage.clickNext();
            String[] otpArray = {"1", "1", "1", "1"};
            loginPage.setOtp(otpArray);
            loginPage.clickSignIn();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement otpInvalidElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='OTP is Invalid']")));
            Assert.assertTrue("OTP is invalid text should be displayed", otpInvalidElement.isDisplayed());
        } catch (Exception e) {
            logger.error("Error in testLoginWithInValidOTP: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }

    @Test
    public void testLoginWithValidOTP() {
        logger.info("Starting test: testLoginWithValidOTP");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail("test@gmail.com");
            loginPage.clickNext();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80)); // Adjust based on actual OTP wait time
            wait.until(ExpectedConditions.elementToBeClickable(loginPage.btnSignIn));
            loginPage.clickSignIn();
            logger.info("Successfully logged in with valid OTP");
        } catch (Exception e) {
            logger.error("Error in testLoginWithValidOTP: ", e);
            Assert.fail("Test failed due to an unexpected error.");
        }
    }
}