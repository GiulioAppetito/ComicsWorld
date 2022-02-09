package selenium;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.DatesConverter;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiscountCode {
    @Test
    public void testDiscountCodeIsExpired(){

        //test if discount code is expired by checking on RapidTables today's date

        String codeToTest = "MFR458";
        String finalFormat = "yyyy-MM-dd";
        String oldFormatWeb = "MM/dd/yyyy";

        UserLogin.createAccount("giulio","giulio","reader");
        DiscountCode discountCode = UserLogin.getInstance().getReader().getDiscountCodeByCode(codeToTest);

        System.setProperty("webdriver.chrome.driver","trunk/src/test/java/selenium/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.rapidtables.com/tools/calendar.html");
        WebElement result = driver.findElement(By.xpath("//*[@id=\"date\"]/div[1]/input"));

        String todaysDate = result.getAttribute("value");
        String expiringDateString = discountCode.getExpiringDate().toString();

        SimpleDateFormat sdf2 = new SimpleDateFormat(oldFormatWeb);
        Date d2 = null;
        try {
            d2 = sdf2.parse(todaysDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf2.applyPattern(finalFormat);
        String todaysDateWebString = sdf2.format(d2);

        boolean expected = DatesConverter.toLocalDate(expiringDateString).isBefore(DatesConverter.toLocalDate(todaysDateWebString));
        boolean actual = discountCode.isExpired();

        assertEquals(expected,actual);
        driver.close();
    }

}
