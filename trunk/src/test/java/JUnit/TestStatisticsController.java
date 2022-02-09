package JUnit;

import com.example.comics.controller.ResearchController;
import com.example.comics.controller.StatisticsController;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.SeriesBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestStatisticsController {

    @Test
    void testSeriesAverageRating(){

        String seriesToTestTitle = "Scarlet Witch";

        Float result;
        SeriesBean seriesToTestBean;

        UserLogin.createAccount("stanlee","stanlee","author");

        Series series = SeriesDAO.retrieveSeries(seriesToTestTitle);
        List<Series> seriesList = new ArrayList<>();
        seriesList.add(series);

        ResearchController researchController = new ResearchController();
        seriesToTestBean = researchController.getSeriesBeans(seriesList).get(0);

        StatisticsController statisticsController = new StatisticsController();

        result = statisticsController.seriesAverageRating(seriesToTestBean);
        Float expected = 3.6f;

        assertEquals(expected,result);

    }
}
