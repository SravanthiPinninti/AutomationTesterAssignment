package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.CompleteProfile;
import pageobjects.LoginPage;

import java.time.Duration;

public class ERNXUtility {

    private static final Logger logger = LogManager.getLogger(ERNXUtility.class);

    /**
     * Sets up and returns a WebDriver instance.
     */
    public static WebDriver getWebDriver() throws InterruptedException {
        logger.info("Initializing WebDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://ernx-consumer.vercel.app/login");

        logger.info("WebDriver initialized and navigated to login page");
        return driver;
    }

    /**
     * Logs in with a valid OTP.
     */
    public static void loginWithValidOTP(WebDriver driver) throws InterruptedException {
        logger.info("Performing login with valid OTP");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail("alliswell551@gmail.com");
            loginPage.clickNext();

            // Wait for OTP input elements to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOf(loginPage.btnSignIn));

            // Assume OTP is manually entered or handled externally
            loginPage.clickSignIn();
            logger.info("Successfully logged in with valid OTP");
        } catch (Exception e) {
            logger.error("Error during login with valid OTP: ", e);
            throw new InterruptedException("Login failed due to an unexpected error.");
        }
    }

    /**
     * Submits a valid complete profile.
     */
    public static void validCompleteProfileSubmission(WebDriver driver) throws InterruptedException {
        logger.info("Submitting valid complete profile");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("complete-profile"));

            CompleteProfile completeProfile = new CompleteProfile(driver);
            completeProfile.setFirstName("Hari");
            completeProfile.setLastName("P");
            completeProfile.clickNext();

            wait.until(ExpectedConditions.urlContains("addchild"));
            logger.info("Successfully submitted complete profile and navigated to add child page");
        } catch (Exception e) {
            logger.error("Error during complete profile submission: ", e);
            throw new InterruptedException("Profile submission failed due to an unexpected error.");
        }
    }
}