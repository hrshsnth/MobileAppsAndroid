package com.example.hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
/**
 * HW7
 * GalleryActivity.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class GalleryActivity extends Activity {

	 ProgressDialog progressDialog;
	  PhotoAdapter adapter;
	  GridView output;
	  ArrayList<Search> list;
	  String query;
	  DatabaseHelper dbHelp;
	  RuntimeExceptionDao<Search, Integer> runtimedao;
	  boolean find;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		
		output=(GridView)findViewById(R.id.gridView1);
		query= getIntent().getExtras().getString("Search");
		
		dbHelp=OpenHelperManager.getHelper(GalleryActivity.this, DatabaseHelper.class);
		
		try {
			runtimedao=dbHelp.getRuntimeDao();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		List<Search> check=runtimedao.queryForAll();
	
		if(check!=null)
		{
			find=checkexists(check, query);	
			if(find)
			{
				Log.d("Already in DB", "Already in DB");
				List<Search> result=runtimedao.queryForEq("query",query );
				setupData((ArrayList<Search>)result);
						
			}
			else
			{
				Log.d("Not in DB", "Not in DB");
				new photoresult().execute("https://api.500px.com/v1/photos/search?consumer_key=Ws5wumN4Kfs3BBpLg5seclH0yw3C0oJe8Ejd6EUa&term="+query+"&image_size=4&rpp=50");
				
				 progressDialog = new ProgressDialog(this);
					progressDialog.setMessage("Loading Result");
					progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					progressDialog.setCancelable(false);
					progressDialog.show();
				
			}			
			
			
		}
		
	}
	
	public class photoresult extends AsyncTask<String, Void, ArrayList<Search>>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(ArrayList<Search> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			for(Search p:result)
			{
				runtimedao.create(new Search(query,p.getPhotoURL(),p.getPhotoName(),p.getPhotoOwnerURL(),p.getPhotoOwner(),""));				
			}
			setupData(result);
			
		}

		int responsecode;

		@Override
		protected ArrayList<Search> doInBackground(String... params) {
			
			URL url;
			try {
				url = new URL(params[0]);
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
	    		con.setRequestMethod("GET");
	    		con.connect();
	    		responsecode=con.getResponseCode();
	    		if(responsecode==HttpURLConnection.HTTP_OK)
	    		{
	    			BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
	    			StringBuilder sb=new StringBuilder();
	    			String line=br.readLine();
	    			while(line!=null)
	    			{
	    				sb.append(line);
	    				line=br.readLine();
	    			}
	    			return AppUtilJSON.AppJSONParser.parsedetail(sb.toString());
	    		}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
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
		
	}
	
	public void setupData(ArrayList<Search> result)
	{
		
		adapter = new PhotoAdapter(GalleryActivity.this, R.layout.templistimg, result);
		output.setAdapter(adapter);
		
		
		list=new ArrayList<Search>();
		
		list=result;	
		
		adapter.setNotifyOnChange(true);
    	output.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent i =new Intent(getApplicationContext(), DetailsActivity.class);
				i.putExtra("photoIMG", list.get(position).getPhotoURL());
				i.putExtra("photoOWN", list.get(position).getPhotoOwnerURL());
				i.putExtra("photoname", list.get(position).getPhotoName());
				i.putExtra("ownname", list.get(position).getPhotoOwner());
				i.putExtra("comment",list.get(position).getComments());
				startActivity(i);
				
			}
		});
		
		
	}
	
	public boolean checkexists(List<Search> check,String n)
	{
		boolean validate = false;
		for(Search search:check)
		{
			if(n.equalsIgnoreCase(search.getQuery()))
			{
				validate=true;
			}
			
		}
		return validate;
	}

}
