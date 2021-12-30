package com.example.comics.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Badge {
	
	private String name;
	private Image icon;
	private int id;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
