package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {
    @Test
    public void testCase(){
        System.setProperty("webdriver.chrome.driver","trunk/src/test/java/selenium/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://it.wikipedia.org/wiki/Uomo_Ragno");
        WebElement txtBox = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[8]/td/ul/li[1]/a"));
    }

}
