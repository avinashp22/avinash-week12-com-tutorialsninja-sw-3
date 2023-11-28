package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    //This method will find the element  and click on that element
    public void clickOnElement(By by) {driver.findElement(by).click();}

    //This method will send text to the element
    public void sendTextToElement(By by, String text) {driver.findElement(by).sendKeys(text);}

    //This method will get the text from an element
    public String getTextFromElement(By by) {return driver.findElement(by).getText();}

    //This method will clear the text
    public void clearText(By by) {driver.findElement(by).clear();}



    //************************* Alert Methods *****************************************************

    //This method will switch to alert
    public void switchToAlert(){driver.switchTo().alert();}

    //This method will accept to alert
    public void acceptAlert(){driver.switchTo().alert().accept();}

    //This method will dismiss alert
    public void dismissAlert(){driver.switchTo().alert().dismiss();}

    //This Method will send text to alert
    public void sendTextToAlert(By by, String text) {driver.switchTo().alert().sendKeys(text);}

    //this method will get text from alert
    public String getTextFromAlert(){return driver.switchTo().alert().getText();}

    //*************************** Select Class Methods ***************************************//

    //This method will select the option from the dropdown by visible Text
    public void selectFromVisibleTextFromDropdown(By by,String text){
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    //This method will select the option from the dropdown by visible Index
    public void selectByIndexFromDropDown(By by, String index) {
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByValue(index);

    }

    //This method select the option from the dropdown by visible Value
    public void selectByValueFromDropDown(By by, String value){
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    //*************************** Action Methods ***************************************//

    //This method will use to hover mouse on element
    public void mouseHoverToElement(By by){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    //This method will mouse hover and click on element
    public void mouseHoverAndClickOnElement(By by){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();
    }



    //This method will verify text
    public void verifyText(String displayMessage, String expectedMessage, By by) {
        String Message = getTextFromElement(by);
        Assert.assertEquals(displayMessage, expectedMessage, Message);
    }

    //This method will verify text also
    public void verifyMessage(String expectedResult, By by) {
        String actualResult = driver.findElement(by).getText();
        Assert.assertEquals("is not displayed correctly", expectedResult, actualResult);
    }

}
