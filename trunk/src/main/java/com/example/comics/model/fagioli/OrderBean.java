package com.example.comics.model.fagioli;

import java.time.LocalDate;

public interface OrderBean {

    SeriesBean getSeries();
    void setSeries(SeriesBean series);

    LocalDate getDate();
    void setDate(LocalDate date);

    Float getExpense();
    void setExpense(Float expense);

    String getChapterTitle();
    void setChapterTitle(String chapterTitle);

}
