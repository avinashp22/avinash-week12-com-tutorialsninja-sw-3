package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {
    String baseURL = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp(){openBrowser(baseURL);}

    public void selectMyAccountOptions(String option) {

        List<WebElement> list = driver.findElements(By.xpath("//body/nav/div/div/ul/li/ul/li"));
        for (WebElement options : list) {
            System.out.println(options.getText());
            if (options.getText().equalsIgnoreCase(option)) {
                options.click();
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        clickOnElement(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]"));
        selectMyAccountOptions("Register");
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        clickOnElement(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]"));
        selectMyAccountOptions("Login");
    }

    static String email;

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {

        //3.1 Click on My Account Link.
        clickOnElement(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]"));

        //3.2 Call the method “selectMyAccountOptions” method and pass the parameter
        //“Register”
        selectMyAccountOptions("Register");

        //3.3 Enter First Name
        sendTextToElement(By.id("input-firstname"), "Avinash");

        //3.4 Enter Last Name
        sendTextToElement(By.id("input-lastname"), "Patel");

        //3.5 Enter Email
        sendTextToElement(By.id("input-email"), "avvvinassshhhp2201@gmail.com");

        //3.6 Enter Telephone
        sendTextToElement(By.id("input-telephone"), "07759463259");

        //3.7 Enter Password
        sendTextToElement(By.id("input-password"), "Prime12345");

        //3.8 Enter Password Confirm
        sendTextToElement(By.id("input-confirm"), "Prime12345");

        //3.9 Select Subscribe Yes radio button
        clickOnElement(By.xpath("(//input[@type='radio'])[2]"));

        //3.10 Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@type='checkbox']"));
        //3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@type='submit']"));

        //3.12 Verify the message “Your Account Has Been Created!”
        String expectedText = "Your Account Has Been Created!";
        String actualText = getTextFromElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]"));
        Assert.assertEquals("NotMatching", expectedText, actualText);

        //3.13 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //3.14 Clickr on My Account Link.
        clickOnElement(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]"));

        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");

        //3.16 Verify the text “Account Logout”
        String expectedLogout = "Account Logout";
        String actualLogout = getTextFromElement(By.xpath("//h1[text()='Account Logout']"));
        Assert.assertEquals("NotMatching", expectedLogout, actualLogout);

        //3.17 Click on Continue button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //4.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]"));

        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");

        //4.3 Enter Email address
        sendTextToElement(By.id("input-email"), "avinashp22@gmail.com");
        //4.5 Enter Password
        sendTextToElement(By.id("input-password"), "Prime12345");

        //4.6 Click on Login button
        clickOnElement(By.xpath("//input[@type='submit']"));

        //4.7 Verify text “My Account”
        String expTextMyAccount = "My Account";
        String actTextMyAccount = getTextFromElement(By.xpath("//h2[text()='My Account']"));
        Assert.assertEquals("Not Logged in", expTextMyAccount, actTextMyAccount);


        //4.8 Clickr on My Account Link.
        clickOnElement(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]"));

        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");

        //4.10 Verify the text “Account Logout”
        String expTextLogout = "Account Logout";
        String actTextLogout = getTextFromElement(By.xpath("//h1[text()='Account Logout']"));
        Assert.assertEquals("Not Logout", expTextLogout, actTextLogout);

        //4.11 Click on Continue button
        clickOnElement(By.xpath("//a[text()='Continue']"));
    }

    @After
    public void tearDown(){closeBrowser();}

}
