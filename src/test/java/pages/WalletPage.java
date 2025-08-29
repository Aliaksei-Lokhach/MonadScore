package pages;

import com.codeborne.selenide.ElementsCollection;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class WalletPage extends BasePage{

    public WalletPage() throws AWTException {
    }

    public WalletPage openLoginPage() {
        log.info("Opening HaHa Wallet page...");
        open("chrome-extension://dnmpelhpleegioagnoeflghldepcpbbd/home.html#onboarding/login");
        return this;
    }

    public WalletPage isLoginPageOpened() {
        try {
            $("[type='email']").shouldBe(visible);
            log.info("WalletPage is opened");
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("WalletPage isn't opened");
        }
        return this;
    }

    public WalletPage closeAnotherTabs() throws InterruptedException {
        Thread.sleep(5000);
        log.info("Close another tabs");
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_W);
        robot.delay(50);
        robot.keyRelease(KeyEvent.VK_W);
        robot.delay(50);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.waitForIdle();

        return this;
    }

    public WalletPage login(String email, String password) {
        log.info("Login wallet...");
        $("[type='email']").setValue(email);
        $("[type='password']").setValue(password);
        $(byText("CONTINUE")).click();
        log.info("Login successful");
        return this;
    }

    public WalletPage isCreatePinCodePageOpened() {
        $(byText("Create Pin Code")).shouldBe(visible);
        return this;
    }

    public WalletPage createPinCode(String pin) {
        log.info("Creating pin...");
        $("[placeholder='Your Pin Code']").setValue(pin);
        $("[placeholder='Confirm Pin Code']").setValue(pin);
        $(byText("Continue")).click();
        log.info("Pin created");
        return this;
    }

    public WalletPage isImportPageOpened() {
        $("[type='checkbox']").shouldBe(visible);
        return this;
    }

    public WalletPage importWallet(String seedPhrase) {
        log.info("Import wallet...");
        String[] seedWords = seedPhrase.split(" ");
        $("[type='checkbox']").click();
        $(byText("Import Existing Wallet")).click();
        $(byText("Seed Phrase")).shouldBe(visible);
        $(byText("Seed Phrase")).click();
        $(byText("I have a 24-word recovery phrase")).shouldBe(visible);
        $(byText("I have a 24-word recovery phrase")).click();
        $(byText("I have a 24-word recovery phrase")).shouldNotBe(visible);
        ElementsCollection inputs = $$x("//div[(starts-with(text(), 'Import an existing'))]" +
                "/following::div//div//input");

        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setValue(seedWords[i]);
        }
        $(byText("Continue")).click();
        log.info("Wallet imported");
        return this;
    }

    public WalletPage importWalletShort(String seedPhrase) {
        log.info("Import wallet...");
        String[] seedWords = seedPhrase.split(" ");
        $("[type='checkbox']").click();
        $(byText("Import Existing Wallet")).click();
        $(byText("Seed Phrase")).shouldBe(visible);
        $(byText("Seed Phrase")).click();
        $(byText("I have a 24-word recovery phrase")).shouldBe(visible);
        ElementsCollection inputs = $$x("//div[(starts-with(text(), 'Import an existing'))]" +
                "/following::div//div//input");

        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setValue(seedWords[i]);
        }
        $(byText("Continue")).click();
        log.info("Wallet imported");
        return this;
    }

    public WalletPage isImportWalletSuccessful() {
        $(byText("Start Using Wallet")).shouldBe(visible).click();
        return this;
    }
}
