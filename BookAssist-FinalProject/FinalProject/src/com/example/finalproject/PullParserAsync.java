package com.example.finalproject;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.xmlpull.v1.XmlPullParserException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PullParserAsync extends AsyncTask<String, String, BookPrice>{

	int responsecode;
	Context context;
	TextView AZTitle,AZPrice;
	ImageView azimg;
	String URL="";
	public PullParserAsync(Context con,TextView aztitle,TextView azprice,ImageView aimg)
	{
		this.AZTitle=aztitle;
		this.AZPrice=azprice;
		this.context=con;
		this.azimg=aimg;
	}
	
	@Override
	protected BookPrice doInBackground(String... params) {
	try {
		URL url=new URL(params[0]);
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		responsecode=con.getResponseCode();
		if(responsecode==HttpURLConnection.HTTP_OK)
		{
			InputStream in=con.getInputStream();
			
			return BookUtilParser.BookXMLPullParser.parseData(in);
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
	protected void onPostExecute(BookPrice result) {
	
		super.onPostExecute(result);
		if(result!=null)
		{		
			Log.d("Result Value", result.getTitle() +" "+result.getPrice());
		//activity.setupData(result);
			AZTitle.setText(result.getTitle());
			AZPrice.setText("Total Cost: "+result.getPrice());	
			URL=result.getURL();
			azimg.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
					context.startActivity(browserIntent);					
				}
			});
		}
		else{
			Log.d("Result Value", "No Result Received");
		}		
	}
}
