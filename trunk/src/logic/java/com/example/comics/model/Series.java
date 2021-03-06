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
	private int averageRating;
	private List<Chapter> chapters;
	private List<Objective> objectives;
	private String description;

	private Genres genre1;
	private Genres genre2;
	private Genres genre3;

	public Series(String title, Author author){
		Thread chaptersThread;
		this.title = title;
		this.author = author;
		chaptersThread = new Thread(() -> {
			ChapterDAO chapterDAO = new ChapterDAO();
			this.chapters = chapterDAO.retriveChapters(title);
		});
		chaptersThread.start();

		SeriesDAO seriesDAO = new SeriesDAO();
		this.objectives = seriesDAO.retrieveObjectives(title);

		try {
			chaptersThread.join();
		} catch (InterruptedException e) {
			chaptersThread.interrupt();
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

	public List<Chapter> getChapters(){
		return chapters;
	}

	public List<Objective> getObjectives() {
		return this.objectives;
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

	public Genres getGenre3() {
		return genre3;
	}

	public void setGenre3(Genres genre3) {
		this.genre3 = genre3;
	}

	public Genres getGenre2() {
		return genre2;
	}

	public void setGenre2(Genres genre2) {
		this.genre2 = genre2;
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

	public void addReview(String chapterTitle, String comment, int rating, Account account) {
		for (Chapter chapter : chapters) {
			if (chapter.getTitle().equals(chapterTitle)) {
				chapter.addReview(this, comment, rating, account);
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

		for(Chapter chapter : this.getChapters()){
			if(chapter.getTitle().equals(chapterTitle)){
				chapter.setRead(false);
			}
		}

		for(Chapter chapter : this.getChapters()){
			if(Boolean.TRUE.equals(chapter.getRead())){
				isToBeRemoved = false;
			}
		}

		if(isToBeRemoved){
			UserLogin.getInstance().getReader().removeFromReading(seriesToRemove);
		}

	}

    public float countReviewsByReader(String readerUsername) {
		float numOfReviews = 0;
		for(Chapter chapter : this.chapters){
			for(Review review : chapter.getReviews()){
				if(review.getAccount().getUsername().equals(readerUsername)){
					numOfReviews+=1;
				}
			}
		}
		return numOfReviews;
    }

	public float countReadersReadChapters() {
		float readChapters = 0;
		for(Chapter chapter : this.chapters){
			if(chapter.getRead()){
				readChapters++;
			}
		}
		return readChapters;
	}
}

