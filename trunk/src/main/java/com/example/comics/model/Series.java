package com.example.comics.model;

import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.dao.SeriesDAO;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Series {
	
	private String title;
	private ImageView cover;
	private String author;
	private String publishingHouse;
	private ArrayList<Chapter> chapters;
	private ArrayList<Character> characters;
	private ArrayList<Objective> objectives = new ArrayList<Objective>();

	private Series(){
		//costruttore di default
	}

	public Series(String title){
		this.title = title;
		SeriesDAO seriesDAO = new SeriesDAO();
		this.chapters = seriesDAO.retriveChapters(title);

		//inizializzazione obiettivi dal db
		this.objectives = seriesDAO.retrieveObjectives(title);
		if(objectives==null){
			objectives = new ArrayList<>();
		}
		System.out.println("[SERIES] Nel costruttore, taglia di objectives : "+ this.objectives.size());

	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}
	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public ArrayList<Chapter> getChapters(){
		return chapters;
	}

	public ArrayList<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(ArrayList<Objective> objectives) {
		this.objectives = objectives;
	}
}

