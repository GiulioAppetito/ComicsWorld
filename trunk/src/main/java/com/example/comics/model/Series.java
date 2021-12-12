package com.example.comics.model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Series {
	
	private String name;
	private ImageView cover;
	private String author;
	private ArrayList<Genres> genres = new ArrayList<Genres>();
	private String publishingHouse;
	private List<Chapter> chapters;
	private List<Character> characters;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public ImageView getCover() {
		return cover;
	}
	public void setCover(ImageView cover) {
		this.cover = cover;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}
	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public List<Character> getCharacters() {
		return characters;
	}
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}



	public ArrayList<Genres> getGenres() {
		return genres;
	}
	public void setGenres(ArrayList<Genres> genres) {
		this.genres = genres;
	}
}

