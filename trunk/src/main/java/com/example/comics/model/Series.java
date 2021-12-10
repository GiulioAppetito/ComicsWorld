package com.example.comics.model;

import javafx.scene.image.ImageView;

import java.util.List;

public class Series {
	
	private String name;
	private ImageView cover;
	private String author;
	private String publishingHouse;
	private List<Chapter> chapters;

	
	
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
}

