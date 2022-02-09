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
        driver.get("https://calcolatricegratis.it/risolutore-disequazioni-online-risolvi-disequazioni/#soluciones");
        driver.findElement(By.xpath("//*[@id=\"source\"]")).sendKeys(achievement.toString());
        driver.findElement(By.xpath("//*[@id=\"mayorigual\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"source\"]")).sendKeys(objective.getRequirement().toString());
        WebElement result = driver.findElement(By.xpath("//*[@id=\"outintro\"]/div/fmath/mrow/mrow/mrow[2]/mn"));

        boolean expected;
        expected = "1".equals(result.getText());

        boolean actual = objective.isObjectiveAchieved(achievement);

        assertEquals(expected,actual);

    }
}
