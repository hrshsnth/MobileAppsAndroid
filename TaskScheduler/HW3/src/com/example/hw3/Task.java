package com.example.hw3;

import java.io.Serializable;
/**
 * Homework 3
 * Task.java
 * @author HARISH SAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN (800823490)
 *
 */
public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String title,desc,date,time,location,priority;

	/*public Task(String title, String desc, String date, String time,
			String location) {
		super();
		this.title = title;
		this.desc = desc;
		this.date = date;
		this.time = time;
		this.location = location;
	}*/

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	

}