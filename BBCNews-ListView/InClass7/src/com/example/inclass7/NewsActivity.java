package com.example.inclass7;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;


import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
/** InClassAssign7
 * FileName : NewsActivity.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class NewsActivity extends Activity {
	
	Drawable drawable;
	LinkedList<News> list;
	List<Object> test;
	ProgressDialog mProgressDialog;
	LinearLayout root;
	Bitmap bitmap;
	ScrollView sview;
	static Context context;	
    String url,category;
    ListView output;
    NewsAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		root = (LinearLayout) findViewById(R.id.LinearLayout1);
		url=getIntent().getStringExtra(MainActivity.URL);
		category=getIntent().getStringExtra(MainActivity.TYPE);

		Log.d("dayaurl", url);
	   		new LoadTask().execute(url);
	   	   		
	}
	
	public class LoadTask extends AsyncTask<String, Void, LinkedList<News>>{

		 NewsActivity activity;
		
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
			mProgressDialog=new ProgressDialog(NewsActivity.this);
			mProgressDialog.setMessage("Loading News...");
			mProgressDialog.show();
			
		}

		@Override
		protected void onPostExecute(LinkedList<News> result) {
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			output=(ListView)findViewById(R.id.listnews);
	    	adapter = new NewsAdapter(NewsActivity.this, R.layout.imagetext, result);
	    	output.setAdapter(adapter);
		
	    	list=new LinkedList<News>();
    		list=result;
	    	output.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    		
	  			@Override
	  			public void onItemClick(AdapterView<?> parent, View view,
	  					int position, long id) {
	  				Intent i =new Intent(getApplicationContext(), DetailedNewsActivity.class);
					i.putExtra("IMG", list.get(position).getThumbnailLarge());
					i.putExtra("DESC", list.get(position).getDescription());
					i.putExtra("PUB", list.get(position).getPubDate());
					i.putExtra("TITLE", list.get(position).getTitle());
					startActivity(i);
	  			}
	  		});
	          
	          output.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					//((ViewGroup) view.getParent()).removeView(view);
					adapter.remove(list.get(position));
				
					return true;
				}
			});
	    	adapter.setNotifyOnChange(true);
	    	
		}
	
	}
}
