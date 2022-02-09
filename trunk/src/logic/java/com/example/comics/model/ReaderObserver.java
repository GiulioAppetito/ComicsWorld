package com.example.comics.model;

import com.example.comics.model.fagioli.BadgeBean;

public interface ReaderObserver {
    void update(BadgeBean badgeBean);
    void update(Boolean payment);
}
