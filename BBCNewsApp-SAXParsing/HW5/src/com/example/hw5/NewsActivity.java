package com.example.hw5;


import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
/** HW #5
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
	private SharedPreferences.Editor newsPrefsEditor;
	private SharedPreferences newsPrefs;
    public static final String PREFS_NAME = "CategoriesPref";
    String url,category;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		root = (LinearLayout) findViewById(R.id.LinearLayout1);
		sview =(ScrollView) findViewById(R.id.ScrollView1);
		mProgressDialog= new ProgressDialog(this);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		url=getIntent().getStringExtra(MainActivity.URL);
		category=getIntent().getStringExtra(MainActivity.TYPE);

		Log.d("dayaurl", url);
		long two_mins = 1000 *120;

		/* Restore preferences*/
	       newsPrefs =getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
	       
	       long oldTime = newsPrefs.getLong(category, 0);
	      
	       if(System.currentTimeMillis() - oldTime > two_mins){
	    	   Log.d("category",category);
	    	   Log.d("current time", "time is:"+System.currentTimeMillis());
	    	   Log.d("old time", " old time is:"+oldTime);
	    	   newsPrefsEditor = newsPrefs.edit();
	 	       newsPrefsEditor.putLong(category, System.currentTimeMillis());
	 	       newsPrefsEditor.commit();
	 	 
	   		new LoadRssTask(NewsActivity.this,mProgressDialog).execute(url);
	    	}else{
	    		Log.d("debug", "In Else");    	
	    		list = new LinkedList<News>();
		        for ( int i=0;i< 25;i++){
			       String s= newsPrefs.getString("keys"+i,null);
				    	Log.d("debug","set retrieve:"+s);
			        try {
	    	        	String thumbnailSmall=null;
	    	        	String thumbnailLarge=null;
	    	            JSONObject jsonObject = new JSONObject(s);
	    	            String title = jsonObject.getString("title");
	    	            String description = jsonObject.getString("description");
	    	            String pubDate = jsonObject.getString("pubDate");
	    	            	thumbnailSmall=jsonObject.getString("thumbnailSmall");
	    	            	thumbnailLarge=jsonObject.getString("thumbnailLarge");
	    	            News news = new News(title, description, pubDate, thumbnailSmall, thumbnailLarge);
	    	            list.add(news);
	    	        } catch (JSONException e) {
	    	            e.printStackTrace();
	    	        }
		        }
	    
	    		setupData(list);
	    	}

	}
	

	 public void setupData(LinkedList<News> newsList){
		 Log.d("daya", "calling setup data");
	    	list = new LinkedList<News>();
	    	list = newsList;
	    	newsPrefsEditor = newsPrefs.edit();
	 	    newsPrefsEditor.putLong(category, System.currentTimeMillis());
	 	    //newsPrefsEditor.commit();
	    	int newsitem=list.size();
	    	Log.d("debug","size of orig list:"+newsitem);
	    	
	        
	        for ( int i=0;i< newsitem;i++){
		        newsPrefsEditor.putString("keys"+i,newsList.get(i).getJSONObject().toString());
	        }
	        newsPrefsEditor.commit();
	    	Log.d("daya", "Items:"+newsitem);
	    	int i=0;
	    	for (final News news : newsList) {
	    		//System.out.println(news.toString());
	    		LinearLayout linearLayout = new LinearLayout(this);
	    		linearLayout.setId(i);
	    		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
	    		linearLayout.setGravity(Gravity.CENTER_VERTICAL);
	    		int ht_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 49, getResources().getDisplayMetrics());
	    		int wt_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 66, getResources().getDisplayMetrics());
	    		int lht_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
	    		int lwt_px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
	    		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,lht_px);
	    		linearLayout.setLayoutParams(params);
	    		ImageView iv = new ImageView(this);
	    		LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(wt_px, ht_px);
		    	
	    		/* Implementation for Hw#5 with Async Task*/
	    		iv.setLayoutParams(layoutParams);
	    		int pad = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
	    		
	    		try {
						drawable = new PhotoAsyncTask().execute(news.getThumbnailSmall()).get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		
	    		//Implementation for Bonus with Picasso
	    		//	Picasso.with(getApplicationContext()).setDebugging(true);
	    		//	Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
	    			//Picasso.with(getApplicationContext()).setLoggingEnabled(true);
	    		Picasso.with(getApplicationContext()).load(news.getThumbnailSmall()).into(iv);

	    		//iv.setImageDrawable(drawable);	
		    	//iv.setPadding(pad, pad, pad, pad);
		    	TextView tv = new TextView(this);
		    	tv.setText(news.getTitle());
		    	tv.setPadding(pad, 0, 0, 0);
		    	tv.setTextAppearance(this, android.R.style.TextAppearance_Medium);
		    	tv.setGravity(Gravity.CENTER|Gravity.LEFT);
		    	linearLayout.addView(iv);
		    	linearLayout.addView(tv);
	    		root.addView(linearLayout);
	    		i++;
	    		linearLayout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent i =new Intent(getApplicationContext(), DetailedNewsActivity.class);
						i.putExtra("IMG", news.getThumbnailLarge());
						i.putExtra("DESC", news.getDescription());
						i.putExtra("PUB", news.getPubDate());
						i.putExtra("TITLE", news.getTitle());
						startActivity(i);
						
					}
				});
	    	
	    		linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						
						((ViewGroup) v.getParent()).removeView(v);
						return true;
					}
				});
			}
	    	
	    }
}
