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
	private static final Objective[] objectives = new Objective[Levels.values().length];

	private Series(){
		//costruttore di default
	}

	public Series(String title){

		/*
		//PROBLEMA:

		Salve professore, mi trovo nella seguente situazione:
		una classe A svolge il ruolo di 'whole' in una relazione di composizione con una
		classe B, le cui istanze sono appunto le 'parts'.

		Nel momento in cui una classe con ruolo di
		creator per la classe A, come ad esempio un DAO, si trova ad istanziare un oggetto di tipo A, ne
		invocherà il costruttore, il quale si dovrà occupare della creazione delle istanze di B(parts).
		Tuttavia, momento in cui, le parts devono essere ad esempio recuperate dallo strato di persistenza,
		è possibile che la loro creazione venga delegata ad un'ulteriore classe C, che prenderà dunque la
		responsabilità di creator?

		Sostengo che potrebbe essere una soluzione se la classe C fosse ad uso esclusivo della classe A(whole),
		in modo trasparente al resto del sistema, in quanto
		l'invocazione della creazione delle parti(B) resterebbe sempre vincolata e implicita
		nella creazione dell'whole.

		*/

		this.title = title;
		SeriesDAO seriesDAO = new SeriesDAO();
		this.chapters = seriesDAO.retriveChapters(title);

		//inizializzazione obiettivi

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

}

