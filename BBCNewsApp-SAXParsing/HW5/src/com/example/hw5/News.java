package com.example.hw5;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/** HW #5
 * FileName : News.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class News implements Serializable{
	
	String  title, description, pubDate, thumbnailSmall,thumbnailLarge;
	
	public News() {
		super();
	}


	public News(String title, String description, String pubDate,
			String thumbnailSmall, String thumbnailLarge) {
		super();
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
		this.thumbnailSmall = thumbnailSmall;
		this.thumbnailLarge = thumbnailLarge;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

		public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getThumbnailSmall() {
		return thumbnailSmall;
	}

	public void setThumbnailSmall(String thumbnailSmall) {
		this.thumbnailSmall = thumbnailSmall;
	}

	public String getThumbnailLarge() {
		return thumbnailLarge;
	}

	public void setThumbnailLarge(String thumbnailLarge) {
		this.thumbnailLarge = thumbnailLarge;
	}

	public JSONObject getJSONObject() {
	    JSONObject obj = new JSONObject();
	    try {
	        obj.put("title", this.title);
	        obj.put("description", this.description);
	        obj.put("pubDate", this.pubDate);
	        obj.put("thumbnailSmall", this.thumbnailSmall);
	        obj.put("thumbnailLarge", this.thumbnailLarge);
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    return obj;
	}
	@Override
	public String toString() {
		return title + ";" + description+ ";" + pubDate + ";" + thumbnailSmall+ ";" + thumbnailLarge;
	}

}
