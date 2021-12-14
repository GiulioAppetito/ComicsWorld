package com.example.comics;

import com.example.comics.model.Chapter;
import com.example.comics.model.Series;
import java.util.ArrayList;

public class ResearchController {

    public ArrayList<Series> getLatestSeries(){
        return Series.getSeries();
    }

    //qui io chiedo sempre alla serie perchè
    //Series è composta di chapters, che seguono il suo corso di vita,
    //quindi non posso comunicare direttamente con istanze di chapter per conto mio
    //ma attraverso il composto
    public ArrayList<Chapter> getChapters(String title){
        return Series.getChapters(title);
    }

}
