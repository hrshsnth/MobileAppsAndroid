package com.example.hw6;



import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * HW #6 FileName : MainActivity.java
 * 
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy
 *         Subramaniam(800833319)
 * 
 */
public class MainActivity extends Activity {

	ListView lv;
	String url="http://www.panoramio.com/map/get_panoramas.php?set=public&from=0&to=50&size=medium";
	public static String INDEX_KEY="com.example.hw6.INDEX";
	public static String LIST_KEY="com.example.hw6.LIST";
	public static String TITLE_KEY="com.example.hw6.TITLE";
	public static String IMGURL_KEY="com.example.hw6.IMGURL";
	public static String PUBDATE_KEY="com.example.hw6.PUBDATE";
	public static String OWNER_KEY="com.example.hw6.OWNER";
	ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog= new ProgressDialog(this);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        lv =(ListView) findViewById(R.id.listView1);
  
   		new PhotoAsyncTask(this,mProgressDialog).execute(url);

    }
	public void setupData(ArrayList<Photo> result) {
		final ArrayList<Photo> titles= new ArrayList<Photo>();
		for (Photo photo : result) {
			
			titles.add(photo);
		}
		
	    ArrayAdapter<Photo> adapter = new ArrayAdapter<Photo>(this, android.R.layout.simple_list_item_1,titles);
		System.out.println(result.size());
	        lv.setAdapter(adapter);
	        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					System.out.println(titles.size());
					Intent i = new Intent(getApplicationContext(), DetailedPhotoActivity.class);
					i.putExtra(INDEX_KEY, position);
					i.putParcelableArrayListExtra(LIST_KEY, titles);
					i.putExtra(TITLE_KEY,titles.get(position).getTitle() );
					i.putExtra(IMGURL_KEY,titles.get(position).getImage_url() );
					i.putExtra(PUBDATE_KEY,titles.get(position).getPubdate() );
					i.putExtra(OWNER_KEY,titles.get(position).getOwner_name() );
					startActivity(i);
				}
			});
	}
}
