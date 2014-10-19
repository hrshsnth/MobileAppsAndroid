package com.example.hw5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
/** HW #5
 * FileName : LoadRssTask.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class LoadRssTask extends AsyncTask<String, Void, LinkedList<News>>{

	 NewsActivity activity;
	 ProgressDialog mProgressDialog;
		/*public LoadRssTask(NewsActivity newsActivity) {
			super();
			this.activity = newsActivity;
		}*/
		
	public LoadRssTask(NewsActivity newsActivity,ProgressDialog mProgressDialog) {
		super();
		this.activity = newsActivity;
		this.mProgressDialog =mProgressDialog;
		}

	@Override
	protected LinkedList<News> doInBackground(String... params) {
		URL url =null;
		LinkedList<News> newsList = new LinkedList<News>();

		try {
			Log.d("dayaTask", params[0]);
			url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				InputStream in = con.getInputStream();
			    
				return NewsUtils.NewsXMLPullParser.parseData(in);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog.setMessage("Loading News...");
		mProgressDialog.show();
		
	}

	@Override
	protected void onPostExecute(LinkedList<News> result) {
		super.onPostExecute(result);
		mProgressDialog.dismiss();
		
		activity.setupData(result);
		result.clear();
	
	}

}
