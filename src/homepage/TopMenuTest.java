package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseURL = " http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp(){openBrowser(baseURL);}

    public void selectMenu(String menu) { // This method will click on the menu
        List<WebElement> elementList = driver.findElements(By.id("menu"));
        for (WebElement element : elementList) {
            if (element.getText().equalsIgnoreCase(menu)) {
                element.click();
                break;
            }
        }

    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //Mouse hover on “Desktops” Tab and click
        mouseHoverAndClickOnElement(By.xpath("//a[normalize-space()='Desktops']"));
        //call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");
        clickOnElement(By.xpath("//a[contains(text(),'Show AllDesktops')]"));
        //Verify the text ‘Desktops’
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Desktops']"));
        Assert.assertEquals("Incorrect text", "Desktops", actualText);

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        //Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverAndClickOnElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        //call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        clickOnElement(By.xpath("//a[contains(text(),'Show AllLaptops & Notebooks')]"));
        //Verify the text ‘Laptops & Notebooks’
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Laptops & Notebooks']"));
        Assert.assertEquals("Incorrect text", "Laptops & Notebooks", actualText);

    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        //Mouse hover on “Components” Tab and click
        mouseHoverAndClickOnElement(By.xpath("//a[normalize-space()='Components']"));
        //call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show AllComponents");
        clickOnElement(By.xpath("//a[contains(text(),'Show AllComponents')]"));
        //Verify the text ‘Components’
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Components']"));
        Assert.assertEquals("Incorrect text", "Components", actualText);
    }

    @After
    public void tearDown(){closeBrowser();}

}
