package com.example.comics.model;

import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.dao.SeriesDAO;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Series extends SeriesSubject{

	private Author author;
	private String title;
	private Image cover;
	private String publishingHouse;
	private int averageRating;
	private List<Chapter> chapters;
	private List<Objective> objectives;
	private String description;

	private Genres genre1;
	private Genres genre2;
	private Genres genre3;

	public Series(String title, Author author){
		Thread chaptersThread,objectivesThread;
		this.title = title;
		this.author = author;
		chaptersThread = new Thread(() -> {
			ChapterDAO chapterDAO = new ChapterDAO();
			this.chapters = chapterDAO.retriveChapters(title);
		});
		chaptersThread.start();

		objectivesThread = new Thread(() -> {
			SeriesDAO seriesDAO = new SeriesDAO();
			this.objectives = seriesDAO.retrieveObjectives(title);
			if(objectives==null){
				objectives = new ArrayList<>();
			}
		});
		objectivesThread.start();

		try {
			chaptersThread.join();
			objectivesThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		calculateAverageRating();
	}

	public Series(){
		this.chapters = new ArrayList<>();
		this.objectives = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Chapter addChapter(String chapterTitle, Image chapterCover, String chapterDescription,Float chapterPrice){
		Chapter chapter = new Chapter(chapterTitle);
		chapter.setPrice(chapterPrice);
		chapter.setDescription(chapterDescription);
		chapter.setCover(chapterCover);
		this.chapters.add(chapter);

		return chapter;
	}

	public void addReview(String chapterTitle, String reviewComment, int rating) {
			for(int i=0; i< chapters.size(); i++){
				if(chapters.get(i).getTitle().equals(chapterTitle)){
					chapters.get(i).addReview(this, reviewComment, rating, UserLogin.getInstance().getReader());
				}
			}
			calculateAverageRating();
	}

	public void addReviewInSilence(String chapterTitle, String reviewComment, int rating) {
		for(int i=0; i< chapters.size(); i++){
			if(chapters.get(i).getTitle().equals(chapterTitle)){
				chapters.get(i).addReviewInSilence(reviewComment, rating, UserLogin.getInstance().getReader());
			}
		}
		calculateAverageRating();
	}

	public void calculateAverageRating(){
		int average = 0;
		int count = 0;
		for(Chapter chapter : this.chapters){
			if(chapter.getAverageRating() != 0){
				average = average + chapter.getAverageRating();
				count++;
			}
		}
		if(count==0){
			averageRating = 0;
		}
		else{
			averageRating = average/count;
		}

		notifyObservers();
	}

	public int getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void markChapter(String chapterTitle){
		for(Chapter chapter : this.chapters){
			if(chapter.getTitle().equals(chapterTitle)){
				chapter.setRead(true);
			}
		}
	}

	public void unmarkChapter(String chapterTitle) {

		boolean isToBeRemoved = true;
		Series seriesToRemove = null;

		for(Series readerSeries : UserLogin.getInstance().getReader().getReading()){
			if(readerSeries == null){
				return;
			}
			if(readerSeries.getTitle().equals(this.title)){
				for(Chapter chapter : readerSeries.getChapters()){
					if(chapter.getTitle().equals(chapterTitle)){
						chapter.setRead(false);
						seriesToRemove = readerSeries;
					}
				}

				for(Chapter chapter : readerSeries.getChapters()){
					if(chapter.getRead()){
						isToBeRemoved = false;
					}
				}

			}
		}
		if(isToBeRemoved){
			UserLogin.getInstance().getReader().removeFromReading(seriesToRemove);
		}

	}

}

