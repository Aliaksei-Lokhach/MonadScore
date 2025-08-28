package pages;

import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;

import java.awt.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage{

    public LoginPage() throws AWTException {
    }

    public LoginPage openPage() {
        log.info("Opening SwapPage");
        open("https://dashboard.monadscore.xyz");
        return this;
    }

    public LoginPage isOpened() {
        try {
            $(byText("Connect Wallet")).shouldBe(visible);
            log.info("LoginPage is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("LoginPage isn't opened");
        }
        return this;
    }

    public LoginPage connectWallet() throws InterruptedException {
        log.info("Connecting wallet...");
        $(byText("Connect Wallet")).click();
        $("[aria-label='Close']").shouldBe(visible);
        $(byText("HaHa Wallet")).shouldBe(visible).click();
        Thread.sleep(2000);
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get("chrome-extension://dnmpelhpleegioagnoeflghldepcpbbd/popup.html");
        $(byText("Accept")).shouldBe(visible).click();
        $(byText("Accept")).shouldNotBe(visible);
        newTab.close();
        var tabs = driver.getWindowHandles().stream().toList();
        driver.switchTo().window(tabs.get(0));
        $(byText("Sign Wallet & Continue")).shouldBe(visible);
        log.info("Wallet connected");
        return this;
    }

    public LoginPage signWallet() {
        log.info("Sign Wallet...");
        $(byText("Sign Wallet & Continue")).click();
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get("chrome-extension://dnmpelhpleegioagnoeflghldepcpbbd/popup.html");
        $(byText("Sign")).shouldBe(visible).click();
        $(byText("Sign")).shouldNotBe(visible);
        newTab.close();
        var tabs = driver.getWindowHandles().stream().toList();
        driver.switchTo().window(tabs.get(0));
        $(byText("Tasks")).shouldBe(visible);
        log.info("Sign wallet complete");
        return this;
    }

    public LoginPage runNode() {
        log.info("Start running node...");
        $(byText("Run Node")).click();
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get("chrome-extension://dnmpelhpleegioagnoeflghldepcpbbd/popup.html");
        $(byText("Sign")).shouldBe(visible).click();
        $(byText("Sign")).shouldNotBe(visible);
        newTab.close();
        var tabs = driver.getWindowHandles().stream().toList();
        driver.switchTo().window(tabs.get(0));
        $(byText("Tasks")).shouldBe(visible);
        log.info("Run node complete");
        return this;
    }

    public LoginPage dailyCheck() {
        log.info("Start daily check-in...");
        $(byText("Tasks")).shouldBe(visible).click();
        $(byText("Check In")).shouldBe(visible).click();
        $(byText("Done")).shouldBe(visible);
        log.info("Check-in done");
        return this;
    }
}
