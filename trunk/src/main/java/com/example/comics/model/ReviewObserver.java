package com.example.comics.model;

import com.example.comics.model.fagioli.ObjectiveBean;
import com.example.comics.model.fagioli.ReviewBean;

public interface ReviewObserver {
    void update(ReviewBean reviewBean);

    void achievedObjective(ObjectiveBean objectiveBean);


}
