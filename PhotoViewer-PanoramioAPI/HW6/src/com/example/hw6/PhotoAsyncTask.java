package com.example.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
/**
 * HW #6 FileName : PhotoAsyncTask.java
 * 
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy
 *         Subramaniam(800833319)
 * 
 */

public class PhotoAsyncTask extends AsyncTask<String, Void, ArrayList<Photo>>{

	 MainActivity activity;
	 ProgressDialog mProgressDialog;
		
		
	public PhotoAsyncTask(MainActivity mactivity,ProgressDialog mProgressDialog) {
		super();
		this.activity = mactivity;
		this.mProgressDialog =mProgressDialog;
		}

	@Override
	protected ArrayList<Photo> doInBackground(String... params) {
		URL url =null;

		try {
			Log.d("dayaTask", params[0]);
			url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String line = reader.readLine();

				while (line != null) {
					builder.append(line);
					line = reader.readLine();
				}
			//	Log.d("Apps", builder.toString());
				return PhotoUtils.PhotoJSONParser.parseApp(builder
						.toString());
			
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog.setMessage("Loading Data...");
		mProgressDialog.show();
		
	}

	@Override
	protected void onPostExecute(ArrayList<Photo> result) {
		super.onPostExecute(result);
		mProgressDialog.dismiss();
		
		activity.setupData(result);
		result.clear();
	
	}

}

