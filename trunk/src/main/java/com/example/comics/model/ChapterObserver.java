package com.example.comics.model;


import com.example.comics.model.fagioli.ReviewBean;

public interface ChapterObserver {
    void updateReviews(ReviewBean review);
}
