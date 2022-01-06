package com.example.comics.model;

import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.dao.ReviewsNotFoundException;
import com.example.comics.model.dao.SeriesDAO;
import javafx.scene.image.Image;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Series {
	
	private String title;
	private Image cover;
	private String author;
	private String publishingHouse;
	private List<Chapter> chapters;
	private List<Character> characters;
	private List<Objective> objectives;
	private Genres genre1;
	private Genres genre2;
	private Genres genre3;

	public Series(){
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

	public Genres getGenre1() {
		return genre1;
	}

	public void setGenre1(Genres genre1) {
		this.genre1 = genre1;
	}

	public Genres getGenre2() {
		return genre2;
	}

	public void setGenre2(Genres genre2) {
		this.genre2 = genre2;
	}

	public Genres getGenre3() {
		return genre3;
	}

	public void setGenre3(Genres genre3) {
		this.genre3 = genre3;
	}

	public void addChapter(String chapterTitle, Image chapterCover, String chapterDescription) throws SQLException {
		Chapter chapter = new Chapter(chapterTitle);
		chapter.setDescription(chapterDescription);
		this.chapters.add(chapter);

		ChapterDAO chapterDAO = new ChapterDAO();
		chapterDAO.saveChapter(chapter,this.title);
	}

	public int getNumberOfReviews(Reader reader) {
		int numOfReviews = 0;

		ChapterDAO chapterDAO = new ChapterDAO();

		List<Review> reviews = null;
		try {
			reviews = chapterDAO.retrieveReviewsByReader(this, reader);
		} catch (ReviewsNotFoundException e) {
			e.printStackTrace();
		}


		for(Review review: reviews){
			if(review.getUsername().equals(reader.getUsername())){
				numOfReviews++;
			}
		}
		return numOfReviews;
	}
}

