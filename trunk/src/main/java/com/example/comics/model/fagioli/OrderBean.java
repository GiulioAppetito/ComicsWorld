package com.example.comics.model.fagioli;

import com.example.comics.model.Series;
import javafx.scene.image.Image;

import java.awt.*;
import java.time.LocalDate;

public interface OrderBean {
    Series getSeries();
    void setSeries(Series series);

    LocalDate getDate();
    void setDate(LocalDate date);

    Float getExpense();
    void setExpense(Float expense);

    javafx.scene.image.Image getImage();
    void setImage(Image image);
}
