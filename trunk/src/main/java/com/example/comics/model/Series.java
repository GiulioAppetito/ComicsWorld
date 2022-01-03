package com.example.comics.model;

import com.example.comics.model.dao.SeriesDAO;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Series {
	
	private String title;
	private Image cover;
	private String author;
	private String publishingHouse;
	private List<Chapter> chapters;
	private List<Character> characters;
	private List<Objective> objectives = new ArrayList<>();

	private Series(){
		//costruttore di default
	}

	public Series(String title){
		this.title = title;
		SeriesDAO seriesDAO = new SeriesDAO();
		this.chapters = seriesDAO.retriveChapters(title);
		//inizializzazione obiettivi dal db
		this.objectives = seriesDAO.retrieveObjectives(this);
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

	public List<Chapter> getChapters(){
		return chapters;
	}

	public List<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(List<Objective> objectives) {
		this.objectives = objectives;
	}

	public Image getCover() {
		return cover;
	}

	public void setCover(Image cover) {
		this.cover = (cover);
	}
}

