package com.example.inclass7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/** InclassAssign 7
 * FileName : MainActivity.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class MainActivity extends Activity {
	public static String URL ="com.example.hw5.url";
	public static String TYPE = "com.example.hw5.type";
	public String[] categories = {"Top Stories", "World", "UK","Business","Politics","Health","Education & Family","Science & Environment","Technology","Entertainment & Arts"};
	ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      list= (ListView) findViewById(R.id.newslist);
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
      list.setAdapter(adapter);

      
      adapter.notifyDataSetChanged();
      
    
   
      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			  Intent i =new Intent(MainActivity.this, NewsActivity.class);
			switch (position) {
			case 0:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/rss.xml");
				i.putExtra(TYPE, "TopStories");
				break;
			case 1:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/world/rss.xml");
				i.putExtra(TYPE, "World");
				break;
			case 2:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/uk/rss.xml");
				i.putExtra(TYPE, "UK");
				break;
			case 3:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/business/rss.xml");
				i.putExtra(TYPE, "Business");
				break;
			case 4:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/politics/rss.xml");
				i.putExtra(TYPE, "Politics");
				break;
			case 5:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/health/rss.xml");
				i.putExtra(TYPE, "Health");
				break;
			case 6:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/education/rss.xml");
				i.putExtra(TYPE, "EducationFamily");
				break;
			case 7:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/science_and_environment/rss.xml");
				i.putExtra(TYPE, "ScienceEnvironment");
				break;
			case 8:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/technology/rss.xml");
				i.putExtra(TYPE, "Technology");
				break;
			case 9:
				i.putExtra(URL, "http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml");
				i.putExtra(TYPE, "EntertainmentArts");
				break;
			default:
				break;
			}
			startActivity(i);
			
		}
	});
      
    }
	
}
