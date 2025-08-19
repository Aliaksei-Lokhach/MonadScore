package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.WalletPage;
import utils.TestListener;

import java.awt.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.PropertyReader.getProperty;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    LoginPage loginPage;
    WalletPage walletPage;

    String seedTen = System.getProperty("SEED_PHRASE_10", getProperty("SEED_PHRASE_10"));
    String seedNine = System.getProperty("SEED_PHRASE_9", getProperty("SEED_PHRASE_9"));
    String seedEight = System.getProperty("SEED_PHRASE_8", getProperty("SEED_PHRASE_8"));
    String seedSeven = System.getProperty("SEED_PHRASE_7", getProperty("SEED_PHRASE_7"));
    String seedSix = System.getProperty("SEED_PHRASE_6", getProperty("SEED_PHRASE_6"));
    String seedFive = System.getProperty("SEED_PHRASE_5", getProperty("SEED_PHRASE_5"));
    String seedFour = System.getProperty("SEED_PHRASE_4", getProperty("SEED_PHRASE_4"));
    String seedThree = System.getProperty("SEED_PHRASE_3", getProperty("SEED_PHRASE_3"));
    String seedTwo = System.getProperty("SEED_PHRASE_2", getProperty("SEED_PHRASE_2"));
    String seedOne = System.getProperty("SEED_PHRASE_1", getProperty("SEED_PHRASE_1"));
    String seedZero = System.getProperty("SEED_PHRASE_0", getProperty("SEED_PHRASE_0"));

    String emailTen = System.getProperty("EMAIL_10", getProperty("EMAIL_10"));
    String emailNine = System.getProperty("EMAIL_9", getProperty("EMAIL_9"));
    String emailEight = System.getProperty("EMAIL_8", getProperty("EMAIL_8"));
    String emailSeven = System.getProperty("EMAIL_7", getProperty("EMAIL_7"));
    String emailSix = System.getProperty("EMAIL_6", getProperty("EMAIL_6"));
    String emailFive = System.getProperty("EMAIL_5", getProperty("EMAIL_5"));
    String emailFour = System.getProperty("EMAIL_4", getProperty("EMAIL_4"));
    String emailThree = System.getProperty("EMAIL_3", getProperty("EMAIL_3"));
    String emailTwo = System.getProperty("EMAIL_2", getProperty("EMAIL_2"));
    String emailOne = System.getProperty("EMAIL_1", getProperty("EMAIL_1"));
    String emailZero = System.getProperty("EMAIL_0", getProperty("EMAIL_0"));

    String password = System.getProperty("PASSWORD", getProperty("PASSWORD"));
    String newPassword = System.getProperty("PIN", getProperty("PIN"));

    private static final String
            EXTENSION_PATH = Paths.get("src/test/resources/extensions/HaHaWallet").toAbsolutePath().toString();

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) throws AWTException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));

        if (browser.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";
            Configuration.baseUrl = "https://dashboard.monadscore.xyz/";
            Configuration.timeout = 15000;
            Configuration.browserSize = null;
            Configuration.headless = false;
            Configuration.browserCapabilities = getChromeOptions();
        }

        loginPage = new LoginPage();
        walletPage = new WalletPage();

    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions-except=" + EXTENSION_PATH);
        options.addArguments("--load-extension=" + EXTENSION_PATH);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--lang=en");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches",
                Collections.singletonList("enable-automation"));
        options.addArguments("--start-maximized");
        return options;
    }

    @AfterMethod
    public void TearDawn() {
        getWebDriver().quit();
    }
}
