package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

public class JSONAsyncTask extends AsyncTask<String, Void, String> {

	
	ProgressDialog pb;
	Context context;
	WebView web;

	public JSONAsyncTask(Context con,WebView w) {
		super();
		this.context=con;
		this.web=w;
		// TODO Auto-generated constructor stub
	}



	@Override
	protected String doInBackground(String... params) {
		URL url = null;
		try {
			Log.d("URL Good Reads", params[0]);
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
			
				return AppUtilJSON.AppJSONParser.parsedetail(builder
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
		
		pb=new ProgressDialog(context);
	
		pb.setCancelable(false);
		pb.show();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.d("URL for Loading", result+ "");
		web.loadUrl(result);
		web.setClickable(false);
		pb.dismiss();
	}
}