package com.example.comics.model;

public abstract class Objective {
    private Levels level;

    public Levels getLevel() {
        return level;
    }
    public void setLevel(Levels level) {
        this.level = level;
    }

    public abstract boolean achieveObjective();
}
