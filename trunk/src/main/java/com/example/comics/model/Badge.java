package com.example.comics.model;

import javafx.scene.image.ImageView;

public class Badge {
	
	private String name;
	private ImageView icon;
	private int id;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public ImageView getIcon() {
		return icon;
	}

	public void setIcon(ImageView icon) {
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
