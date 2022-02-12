package selenium.ide;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class TestInuyashaAuthor {
    private WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","trunk/src/test/java/selenium/Driver/chromedriver.exe");

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testInuyashaAuthor() {
        // Test name: TestInuyashaAuthor
        // Step # | name | target | value | comment
        // 1 | open | https://www.starcomics.com/fumetto/inuyasha-wide-edition-3 |  |
        driver.get("https://www.starcomics.com/fumetto/inuyasha-wide-edition-3");
        // 2 | setWindowSize | 945x1012 |  |
        driver.manage().window().setSize(new Dimension(945, 1012));
        // 3 | assertText | linkText=Rumiko Takahashi | Rumiko Takahashi |
        assertThat(driver.findElement(By.linkText("Rumiko Takahashi")).getText(), is("Rumiko Takahashi"));
    }
}
