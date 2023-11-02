import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


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
    }

    @Test
    public void testCSC5991CourseListing() {

        WebElement compSciLink = driver.findElement(By.linkText("CSC - Computer Science"));
        compSciLink.click();

        Assert.assertEquals("https://bulletins.wayne.edu/courses/csc/", driver.getCurrentUrl());

        String courseBody = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(courseBody.contains("CSC 5991"));

        try {
            Thread.sleep(8000);
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
