package com.example.comics.model;

import com.example.comics.fagioli.ObjectiveBean;
import com.example.comics.fagioli.ReviewBean;

public interface ReviewObserver {
    void update(ReviewBean reviewBean);

    void achievedObjective(ObjectiveBean objectiveBean);
}
