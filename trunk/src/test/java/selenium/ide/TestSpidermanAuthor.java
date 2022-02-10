package selenium.ide;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class TestSpidermanAuthor {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","trunk/src/test/java/selenium/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testSpidermanAuthor() {
        // Test name: testSpidermanAuthor
        // Step # | name | target | value | comment
        // 1 | open | /wiki/Pagina_principale |  |
        driver.get("https://it.wikipedia.org/wiki/Pagina_principale");
        // 2 | setWindowSize | 945x1012 |  |
        driver.manage().window().setSize(new Dimension(945, 1012));
        // 3 | click | id=searchInput |  |
        driver.findElement(By.id("searchInput")).click();
        // 4 | type | id=searchInput | uomo ragno |
        driver.findElement(By.id("searchInput")).sendKeys("uomo ragno");
        // 5 | sendKeys | id=searchInput | ${KEY_ENTER} |
        driver.findElement(By.id("searchInput")).sendKeys(Keys.ENTER);
        // 6 | runScript | window.scrollTo(0,495) |  |
        js.executeScript("window.scrollTo(0,495)");
        // 7 | assertText | linkText=Stan Lee | Stan Lee |
        assertThat(driver.findElement(By.linkText("Stan Lee")).getText(), is("Stan Lee"));
    }
}