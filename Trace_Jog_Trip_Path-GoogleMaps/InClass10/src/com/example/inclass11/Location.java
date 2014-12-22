package com.example.inclass11;

public class Location {


	Double Latitude;
	Double Longitude;
	
	public Double getLatitude() {
		return Latitude;
	}
	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}
	public Double getLongitude() {
		return Longitude;
	}
	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}
	@Override
	public String toString() {
		return "Location [ Latitude=" + Latitude
				+ ", Longitude=" + Longitude + "]";
	}
	
	
}
