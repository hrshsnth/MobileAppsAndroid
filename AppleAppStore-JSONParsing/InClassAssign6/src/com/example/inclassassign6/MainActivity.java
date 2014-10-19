package com.example.inclassassign6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * InClassAssign6
 * MainActivity.java
 * @author HARISHSAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN(800823490)
 *
 */

public class MainActivity extends Activity {

	TextView title,price,category,summary;
	ImageView imageurl;
	ArrayList<App> temp=new ArrayList<App>();
	CountDownTimer cn ;
	  int i=0;
	  ProgressDialog progressDialog;
 Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new JSONAsyncTask().execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=200/json");
      
     /*   title=(TextView)findViewById(R.id.textView1);
        price=(TextView)findViewById(R.id.textView2);
        category=(TextView)findViewById(R.id.textView3);
        summary=(TextView)findViewById(R.id.textView4);
        imageurl=(ImageView)findViewById(R.id.imageView1);*/
        
        
        progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Loading Apps");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setCancelable(false);
		progressDialog.show();
        
    }
    
    public class JSONAsyncTask extends AsyncTask<String, String, ArrayList<App>>{

    	int responsecode;
    	@Override
    	protected ArrayList<App> doInBackground(String... params) {
    	try {
    		URL url=new URL(params[0]);
    		HttpURLConnection con=(HttpURLConnection) url.openConnection();
    		con.setRequestMethod("GET");
    		con.connect();
    		responsecode=con.getResponseCode();
    		if(responsecode==HttpURLConnection.HTTP_OK)
    		{
    			BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
    			StringBuilder sb=new StringBuilder();
    		
    			while(br.ready())
    			{
    				sb.append(br.readLine());
    		
    				
    			}
    			System.out.println(sb.toString());
    			temp=AppUtilJSON.AppJSONParser.parsedetail(sb.toString());
    			return AppUtilJSON.AppJSONParser.parsedetail(sb.toString());
    		}
    		
    	} catch (MalformedURLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}  catch (JSONException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    		return null;
    	}
    	@Override
    	protected void onPostExecute(final ArrayList<App> result) {
    		// TODO Auto-generated method stub
    		super.onPostExecute(result);
    		progressDialog.dismiss();
    		Collections.sort(temp,new UserComparator());
    		Log.d("list size", temp.size()+"");
    		
    		
    		  title=(TextView)findViewById(R.id.textView1);
    	        price=(TextView)findViewById(R.id.textView2);
    	        category=(TextView)findViewById(R.id.textView3);
    	        summary=(TextView)findViewById(R.id.textView4);
    	        imageurl=(ImageView)findViewById(R.id.imageView1);
    	      
    	        title.setText(temp.get(0).getTitle());
				price.setText("$"+temp.get(0).getPrice());
				category.setText(temp.get(0).getCategory());
				summary.setText(temp.get(0).getSummary());
				Picasso.with(getApplicationContext()).load(temp.get(0).getImageURL()).into(imageurl);
    	        
    	        
    	        cn = new CountDownTimer(3000, 1000) {
    				public void onTick(long millisUntilFinished) {

    				}

    				@Override
    				public void onFinish() {
    					if(i>199)
    					{
    						cn.cancel();
    					}
    					else
    					{
    						title.setText(temp.get(i).getTitle());
    						if(temp.get(i).getPrice().equalsIgnoreCase("0.0"))
    						{
    							price.setText("Free");
    						}
    						else
    						{
    							price.setText("$"+temp.get(i).getPrice());
    						}    						
    						category.setText(temp.get(i).getCategory());
    						summary.setText(temp.get(i).getSummary());
    						Picasso.with(getApplicationContext()).load(temp.get(i).getImageURL()).into(imageurl);
    						i++;
    						cn.start();
    					}

    						
    					}

    				}.start();
    	        
    	        
    	        
    	       /* for(App a:result)
    	        {
    	        	if(result.indexOf(a)==0)
    	        	{
    	        		title.setText(a.getTitle());
    	        		price.setText(a.getPrice());
    	        		category.setText(a.getCategory());
    	        		summary.setText(a.getSummary());
    	        		Picasso.with(MainActivity.this).load(a.getImageURL()).into(imageurl);
    	        	}
    	        	Log.d("price",a.getPrice() );
    	        	
    	        	
    	        }*/
    	    
    	}

    }
    public void setData(ArrayList<App> result, int i)
    {
    	for(App a:result)
        {
        	if(result.indexOf(a)==i)
        	{
        		title.setText(a.getTitle());
        		price.setText(a.getPrice());
        		category.setText(a.getCategory());
        		summary.setText(a.getSummary());
        		Picasso.with(MainActivity.this).load(a.getImageURL()).into(imageurl);
        		cn.start();
        	}
        	
        }
    }
    
    public class UserComparator implements Comparator<App> {
	    @Override
	    public int compare(App u1, App u2) {
	        return u2.getPrice().compareTo(u1.getPrice());
	    }
	}
}
