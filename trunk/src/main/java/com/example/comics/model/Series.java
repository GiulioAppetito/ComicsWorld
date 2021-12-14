package com.example.comics.model;

import com.example.comics.model.dao.SeriesDAO;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Series {
	
	private String title;
	private ImageView cover;
	private String author;
	private String publishingHouse;
	private List<Chapter> chapters;

	
	
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



	public static ArrayList<Series> getSeries(){
		SeriesDAO seriesdao = new SeriesDAO();
		ArrayList<Series> latestSeries = seriesdao.retriveLatestSeries();
		return latestSeries;
	}

	//effettivamente non so se qua sarebbe solo una get, e sti chapters
	//li devo settare quando creo una nuova serie
	//e poi qua faccio solo return di quell'array
	//però vabbe per ora lascio cosi, che non sono settati e quindi
	//devo per forza tornare sul db a prenderli

	//per ora dummy
	public static ArrayList<Chapter> getChapters(String seriesTitle){
		SeriesDAO seriesdao = new SeriesDAO();
		ArrayList<Chapter> seriesChapters = seriesdao.retriveChapters(seriesTitle);
		return seriesChapters;
	}

}

