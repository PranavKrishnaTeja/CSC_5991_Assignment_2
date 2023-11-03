import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WayneUniTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Before
    public void setUp() {
        driver.get("https://bulletins.wayne.edu/courses/");

        
        WebElement compSciLink = driver.findElement(By.linkText("CSC - Computer Science"));

        
        highlightElement(compSciLink, "yellow", 5000);
    }

    @Test
    public void testCSC5991CourseListing() {
        
        WebElement compSciLink = driver.findElement(By.linkText("CSC - Computer Science"));
        compSciLink.click();        
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://bulletins.wayne.edu/courses/csc/"));
        boolean isCourseFound = driver.getPageSource().contains("CSC 5991");

        if (isCourseFound) {
            WebElement courseElement = driver.findElement(By.xpath("//*[contains(text(), 'CSC 5991')]"));
            highlightElement(courseElement, "yellow", 10000);
            System.out.println("CSC 5991 found.");
        } else {
            System.out.println("CSC 5991 not found.");
        }

        Assert.assertTrue("CSC 5991 should be present on the page", isCourseFound);
    }

    private void highlightElement(WebElement element, String color, int waitTime) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);

        
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.backgroundColor = '" + color + "';", element);

        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

    public static void main(String[] args) {
        setUpClass();
        WayneUniTest test = new WayneUniTest();
        test.setUp();
        test.testCSC5991CourseListing();
        tearDownClass();
    }
}
