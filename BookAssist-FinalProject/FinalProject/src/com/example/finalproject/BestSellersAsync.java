package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class BestSellersAsync extends AsyncTask<String,Void,ArrayList<Book>>{

	MainMenuActivity activity;
	ProgressDialog pb;
	public BestSellersAsync(MainMenuActivity mainMenuActivity) {
		this.activity=mainMenuActivity;
	}

	@Override
	protected ArrayList<Book> doInBackground(String... params) {
		URL url = null;
		try {
			Log.d("URL BestSeller", params[0]);
			url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String line = reader.readLine();

				while (line != null) {
					builder.append(line);
					line = reader.readLine();
				}
				
				return UtilJSONBestSeller.AppJSONParser.parsedetail(builder
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
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		pb=new ProgressDialog(activity);
	
		pb.setCancelable(false);
		pb.show();
	}
	
	@Override
	protected void onPostExecute(ArrayList<Book> result) {
		super.onPostExecute(result);
		Log.d("BestSeller Book List", result+ "");
		activity.bookList = result;
		activity.setUpData();
		pb.dismiss();
	}
}
