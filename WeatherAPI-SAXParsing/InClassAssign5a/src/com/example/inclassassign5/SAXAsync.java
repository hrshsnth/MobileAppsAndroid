package com.example.inclassassign5;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.SAXException;


import android.os.AsyncTask;
import android.util.Log;
/**
 * InClassAssign5
 * SAXAsync.java
 * @author HARISHSAINATH GANAPATHY (800833319) DAYABARAN GANGATHARAN(800823490)
 *
 */
public class SAXAsync extends AsyncTask<String, String, Weather>{

	int responsecode;
	getWeatherData activity;
	public SAXAsync(getWeatherData activity)
	{
		this.activity=activity;
	}
	
	@Override
	protected Weather doInBackground(String... params) {
	try {
		URL url=new URL(params[0]);
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		responsecode=con.getResponseCode();
		if(responsecode==HttpURLConnection.HTTP_OK)
		{
			InputStream in=con.getInputStream();
			return WeatherUtilSAX.WeatherSXParser.parsedetail(in);
		}
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return null;
	}

	@Override
	protected void onPostExecute(Weather result) {
	
		super.onPostExecute(result);
		if(result!=null)
		{
			
		Log.d("Result Value", result.getTemperature() +" "+result.getHumidity()+" "+result.getPressure());
		activity.setupData(result);
		}
		else
		{
			Log.d("Result Value", "No Result Received");
		}
		
	}
	
	public static interface getWeatherData{
		public void setupData(Weather result);
	
	}

}
