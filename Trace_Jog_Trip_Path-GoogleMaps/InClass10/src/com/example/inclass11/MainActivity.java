package com.example.inclass11;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView triptitle;
	
		ArrayList<String> titles;
		ParseQueryAdapter adapter;
		ArrayAdapter<String> test;
		List<ParseObject> temp;
		ArrayList<String> gettitles;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("JogTrip");
        triptitle = (ListView) findViewById(R.id.listViewInbox);
        temp=new ArrayList<ParseObject>();
       gettitles=new ArrayList<String>();
        try {
			temp=query.find();
			for(ParseObject obj: temp)
			{
				gettitles.add(obj.getString("title"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        test = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gettitles);
        triptitle.setAdapter(test);
    
        triptitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
		
				Intent i =new Intent(MainActivity.this,JogTripActivity.class);
				i.putExtra("title", gettitles.get(position));
				startActivity(i);
				
			}
		});  
        
        
    		/*adapter = new ParseQueryAdapter(this, "JogTrip");
    				 adapter.setTextKey("title");
    				 
    				 triptitle = (ListView) findViewById(R.id.listViewInbox);
    				 triptitle.setAdapter(adapter);
    		
            triptitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
				ParseObject obj=adapter.getItem(position);
					//Log.d("value", obj.getString("title"));
					Intent i =new Intent(MainActivity.this,JogTripActivity.class);
					i.putExtra("title", obj.getString("title"));
					startActivity(i);
					
				}
			});*/
    }
}
