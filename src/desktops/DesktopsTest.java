package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DesktopsTest extends Utility {

    String baseURL = " http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp(){openBrowser(baseURL);}

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {

        //1.1 Mouse hover on Desktops Tab.and click
        mouseHoverAndClickOnElement(By.xpath("//a[@class = 'dropdown-toggle' and contains(text(), 'Desktops')]"));
        //1.2 Click on “Show All Desktops”
        mouseHoverAndClickOnElement(By.xpath(" //a[contains(text(), 'Show AllDesktops')]"));

        //1.3 Select Sort By position "Name: Z to A"
        selectByValueFromDropDown(By.xpath("//select[@id='input-sort']"),"https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC");

        //1.4 Verify the Product will arrange in Descending order.
        String actualTextOnDropDown = driver.findElement(By.xpath("//select/option[contains(text(), 'Name (Z - A)')]")).getText();
        String expectedTextOnDropDown = "Name (Z - A)";
        Assert.assertEquals(expectedTextOnDropDown,actualTextOnDropDown);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        Thread.sleep(2000);
//        2.1 Mouse hover on Desktops Tab. and click
        mouseHoverAndClickOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']/ul/li[1]"));
//        2.2 Click on “Show All Desktops”
        clickOnElement(By.linkText("Show AllDesktops"));
        //Select Sort By position "Name: A to Z"
        selectFromVisibleTextFromDropdown(By.xpath("//select[@id='input-sort']"),"Name (A - Z)");
        //Select product “HP LP3065”
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));
        //Verify the Text "HP LP3065"
        verifyMessage("HP LP3065",By.xpath("//h1[normalize-space()='HP LP3065']"));
        //Select Delivery Date "2022-11-30"
        String year = "2023";
        String month = "November";
        String date = "30";
        clickOnElement(By.xpath("//body/div[@id='product-product']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/span[1]/button[1]/i[1]"));

        while (true) {
            String monthYear = driver.findElement(By.xpath("//div[@class='datepicker']/div[1]/table/thead/tr[1]/th[2]")).getText();

            //Splitting year and month Nov 2022
            String arr[] = monthYear.split(" ");
            //Actual dates
            String mon = arr[0];
            String yer = arr[1];

            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker']/div[1]/table/thead/tr[1]/th[3]"));
            }

        }
        //Select Date
        List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='datepicker']//div//table//td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }
        //Enter Qty "1” using Select class.
        driver.findElement(By.xpath("//input[@id='input-quantity']")).sendKeys(Keys.BACK_SPACE);
        sendTextToElement(By.xpath("//input[@id='input-quantity']"),"1");
        // Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        //Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Thread.sleep(2000);
        verifyMessage("Success: You have added HP LP3065 to your shopping cart!\n" +
                "×",By.xpath("//body/div[@id='product-product']/div[1]"));
        //Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        //Verify the text "Shopping Cart"
        String actualText1 = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart         (1.00kg) ')]"));
        Assert.assertEquals("Invalid Text", "Shopping Cart  (1.00kg)", actualText1);
        //Verify the Product name "HP LP3065"
        verifyMessage("HP LP3065",By.linkText("HP LP3065"));
//      Verify the Delivery Date "2022-11-30"
        verifyMessage("Delivery Date:2023-11-30",By.xpath("//small[normalize-space()='Delivery Date:2022-11-30']"));
//      Verify the Model "Product21"
        verifyMessage("Product 21",By.xpath("//td[normalize-space()='Product 21']"));
//      Verify the Total "£74.73" convert to pounds from dollars
        clickOnElement(By.xpath("//span[normalize-space()='Currency']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("(//button[@class='currency-select btn btn-link btn-block'])[2]"));
        //verify total £74.73
        verifyMessage("£74.73",By.xpath("//tbody//tr//td[6]"));
    }

    @After
    public void tearDown(){closeBrowser();}

}
