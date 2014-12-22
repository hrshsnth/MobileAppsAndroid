package com.example.hw8;

import com.j256.ormlite.field.DatabaseField;
/**
 * HW8
 * Places.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class Places {
	
	@DatabaseField
	String Name;
	@DatabaseField(id=true)
	Double latitude;
	@DatabaseField
	Double longitude;
	@DatabaseField
	boolean opennow;
	@DatabaseField
	String type;
	@DatabaseField
	String iconURL;
	
	
	
	public Places(String name, Double latitude, Double longitude,
			boolean opennow, String type, String iconURL) {
		super();
		Name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.opennow = opennow;
		this.type = type;
		this.iconURL = iconURL;
	}
	
	
	public Places() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isOpennow() {
		return opennow;
	}
	public void setOpennow(boolean opennow) {
		this.opennow = opennow;
	}
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	@Override
	public String toString() {
		return "Places [Name=" + Name + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", opennow=" + opennow
				+ ", iconURL=" + iconURL + "]";
	}
	
		
}
