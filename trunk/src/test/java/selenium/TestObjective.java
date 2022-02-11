package selenium;

import com.example.comics.model.Objective;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.SeriesDAO;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestObjective {
    @Test
    public void testObjectiveAchieved(){

        //test if, given a certain new discount code, its expiring date is set correctly
        String seriesToTest = "Spiderman";

        UserLogin.createAccount("giulio","giulio","reader");
        Series series = SeriesDAO.retrieveSeries(seriesToTest);
        assert series != null;
        Objective objective = series.getObjectives().get(0);

        Float achievement = objective.getRequirement();

        System.setProperty("webdriver.chrome.driver","trunk/src/test/java/selenium/Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.rapidtables.com/calc/math/subtraction-calculator.html");
        driver.findElement(By.xpath("//*[@id=\"doc\"]/form/table/tbody/tr[1]/td[2]/input")).sendKeys(achievement.toString());
        driver.findElement(By.xpath("//*[@id=\"doc\"]/form/table/tbody/tr[3]/td[2]/input")).sendKeys(objective.getRequirement().toString());
        driver.findElement(By.xpath("//*[@id=\"doc\"]/form/table/tbody/tr[4]/td[2]/input[1]")).click();
        WebElement result = driver.findElement(By.xpath("//*[@id=\"doc\"]/form/table/tbody/tr[5]/td[2]/input"));

        boolean expected;
        expected = Integer.parseInt(result.getAttribute("value")) >= 0;

        boolean actual = objective.isObjectiveAchieved(achievement);

        assertEquals(expected,actual);

    }
}
