package com.example.inclassassign5;
/**
 * InClassAssign5
 * Weather.java
 * @author HARISHSAINATH GANAPATHY (800833319) DAYABARAN GANGATHARAN(800823490)
 *
 */
public class Weather {
 @Override
	public String toString() {
		return "Weather [temperature=" + temperature + ", Humidity=" + Humidity
				+ ", pressure=" + pressure + ", windspeed=" + windspeed
				+ ", winddirection=" + winddirection + ", clouds=" + clouds
				+ ", precipitation=" + precipitation + "]";
	}

String temperature,Humidity,pressure,windspeed,winddirection,clouds,precipitation;

public String getTemperature() {
	return temperature;
}

public void setTemperature(String temperature) {
	this.temperature = temperature;
}

public String getHumidity() {
	return Humidity;
}

public void setHumidity(String humidity) {
	Humidity = humidity;
}


public String getPressure() {
	return pressure;
}

public void setPressure(String pressure) {
	this.pressure = pressure;
}

public String getClouds() {
	return clouds;
}

public String getWindspeed() {
	return windspeed;
}

public void setWindspeed(String windspeed) {
	this.windspeed = windspeed;
}

public String getWinddirection() {
	return winddirection;
}

public void setWinddirection(String winddirection) {
	this.winddirection = winddirection;
}

public void setClouds(String clouds) {
	this.clouds = clouds;
}

public String getPrecipitation() {
	return precipitation;
}

public void setPrecipitation(String precipitation) {
	this.precipitation = precipitation;
}
}
