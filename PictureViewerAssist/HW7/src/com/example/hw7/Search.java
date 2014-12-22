package com.example.hw7;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
/**
 * HW7
 * Search.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class Search implements Serializable {
	
	@DatabaseField
	String query;
	
	@DatabaseField
	String PhotoURL;
	
	@DatabaseField
	String PhotoName;
	
	@DatabaseField
	String PhotoOwnerURL;
	
	@DatabaseField
	String PhotoOwner;
	
	@DatabaseField
	String Comments;
	
	@DatabaseField
	long date;
	
	public Search() {
		super();
	}


	public Search(String query, String photoURL, String photoName,
			String photoOwnerURL, String photoOwner, String comments) {
		super();
		this.query = query;
		this.PhotoURL = photoURL;
		this.PhotoName = photoName;
		this.PhotoOwnerURL = photoOwnerURL;
		this.PhotoOwner = photoOwner;
		this.Comments = comments;
		this.date=System.currentTimeMillis();
		
	}


	public long getDate() {
		return date;
	}


	public void setDate(long date) {
		this.date = date;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getPhotoURL() {
		return PhotoURL;
	}


	public void setPhotoURL(String photoURL) {
		PhotoURL = photoURL;
	}


	public String getPhotoName() {
		return PhotoName;
	}


	public void setPhotoName(String photoName) {
		PhotoName = photoName;
	}


	public String getPhotoOwnerURL() {
		return PhotoOwnerURL;
	}


	public void setPhotoOwnerURL(String photoOwnerURL) {
		PhotoOwnerURL = photoOwnerURL;
	}


	public String getPhotoOwner() {
		return PhotoOwner;
	}


	public void setPhotoOwner(String photoOwner) {
		PhotoOwner = photoOwner;
	}


	public String getComments() {
		return Comments;
	}


	public void setComments(String comments) {
		Comments = comments;
	}

	
}
