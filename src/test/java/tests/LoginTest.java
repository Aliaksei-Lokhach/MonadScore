package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(
            testName = "Run Node #0",
            groups = {"Second"},
            priority = 0
    )
    public void checkRunNodeZero() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailZero, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWalletShort(seedZero)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #1",
            groups = {"First"},
            priority = 1
    )
    public void checkRunNodeOne() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailOne, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedOne)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #2",
            groups = {"First"},
            priority = 2
    )
    public void checkRunNodeTwo() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailTwo, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedTwo)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #3",
            groups = {"First"},
            priority = 3
    )
    public void checkRunNodeThree() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailThree, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedThree)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #4",
            groups = {"First"},
            priority = 4
    )
    public void checkRunNodeFour() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailFour, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedFour)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #5",
            groups = {"First"},
            priority = 5
    )
    public void checkRunNodeFive() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailFive, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedFive)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #6",
            groups = {"First"},
            priority = 6
    )
    public void checkRunNodeSix() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailSix, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedSix)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #7",
            groups = {"First"},
            priority = 7
    )
    public void checkRunNodeSeven() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailSeven, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedSeven)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #8",
            groups = {"First"},
            priority = 8
    )
    public void checkRunNode() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailEight, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedEight)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #9",
            groups = {"First"},
            priority = 9
    )
    public void checkRunNodeNine() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailNine, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedNine)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }

    @Test(
            testName = "Run Node #10",
            groups = {"First"},
            priority = 10
    )
    public void checkRunNodeTen() throws InterruptedException {
        walletPage.openLoginPage()
                .isLoginPageOpened()
                .closeAnotherTabs()
                .login(emailTen, password)
                .isCreatePinCodePageOpened()
                .createPinCode(newPassword)
                .isImportPageOpened()
                .importWallet(seedTen)
                .isImportWalletSuccessful();
        loginPage.openPage()
                .isOpened()
                .connectWallet()
                .signWallet()
                .runNode()
                .dailyCheck();
    }
}
