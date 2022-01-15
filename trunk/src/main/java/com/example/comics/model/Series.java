package com.example.comics.model;

import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.dao.SeriesDAO;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Series {

	private Author author;
	private String title;
	private Image cover;
	private String publishingHouse;
	private int averageRating;
	private final List<Chapter> chapters;
	private List<Character> characters;
	private List<Objective> objectives;

	private Genres genre1;
	private Genres genre2;
	private Genres genre3;

	public Series(String title, Author author){
		this.title = title;
		this.author = author;
		ChapterDAO chapterDAO = new ChapterDAO();
		this.chapters = chapterDAO.retriveChapters(title);
		//inizializzazione obiettivi dal db
		SeriesDAO seriesDAO = new SeriesDAO();
		this.objectives = seriesDAO.retrieveObjectives(title);
		if(objectives==null){
			objectives = new ArrayList<>();
		}

		this.averageRating = calculateAverageRating();
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

	public void addChapter(String chapterTitle, Image chapterCover, String chapterDescription, InputStream inputStream) throws SQLException {
		Chapter chapter = new Chapter(chapterTitle);
		chapter.setDescription(chapterDescription);
		chapter.setCover(chapterCover);
		this.chapters.add(chapter);

		ChapterDAO chapterDAO = new ChapterDAO();
		chapterDAO.saveChapter(chapter,this.title,inputStream);
	}

	public int getNumberOfReviews(Reader reader) {
		int numOfReviews = 0;

		ChapterDAO chapterDAO = new ChapterDAO();

		List<Review> reviews = null;

		reviews = chapterDAO.retrieveReviewsByReader(this, reader);

		for(Review review: reviews){
			if(review.getUsername().equals(reader.getUsername())){
				numOfReviews++;
			}
		}
		return numOfReviews;
	}

	public void addReview(String chapterTitle, String reviewComment, int rating) {
			for(int i=0; i< chapters.size(); i++){
				if(chapters.get(i).getTitle().equals(chapterTitle)){
					chapters.get(i).addReview(this, reviewComment, rating, UserLogin.getInstance().getReader().getUsername());
				}
			}
	}

	public int calculateAverageRating(){
		int average = 0;
		int count = 0;
		for(Chapter chapter : this.chapters){
			if(!(chapter.getAverageRating() == 0)){
				average = average + chapter.getAverageRating();
				count++;
			}
		}
		if(count==0){
			return count;
		}
		return average/count;
	}

	public int getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}
}

