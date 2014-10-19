package com.example.hw5;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/** HW #5
 * FileName : MainActivity.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class MainActivity extends Activity implements OnClickListener {
	public static String URL ="com.example.hw5.url";
	public static String TYPE = "com.example.hw5.type";
	TextView TopStories, World, UK, Business, Politics, Health, EducationFamily, ScienceEnvironment, Technology, EntertainmentArts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TopStories =(TextView) findViewById(R.id.TextView00);
        World =(TextView) findViewById(R.id.TextView01);
        UK =(TextView) findViewById(R.id.TextView02);
        Business =(TextView) findViewById(R.id.TextView03);
        Politics =(TextView) findViewById(R.id.TextView04);
        Health =(TextView) findViewById(R.id.TextView05);
        EducationFamily =(TextView) findViewById(R.id.TextView06);
        ScienceEnvironment =(TextView) findViewById(R.id.TextView07);
        Technology=(TextView)findViewById(R.id.TextView08);
        EntertainmentArts=(TextView)findViewById(R.id.TextView09);
        TopStories.setOnClickListener(this);
        World.setOnClickListener(this);
        UK.setOnClickListener(this);
        Business.setOnClickListener(this);
        Politics.setOnClickListener(this);
        Health.setOnClickListener(this);
        EducationFamily.setOnClickListener(this);
        ScienceEnvironment.setOnClickListener(this);
        Technology.setOnClickListener(this);
        EntertainmentArts.setOnClickListener(this);
        
    }
	@Override
	public void onClick(View v) {
		Intent i =new Intent(this, NewsActivity.class);
		switch (v.getId()) {
		case R.id.TextView00:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/rss.xml");
			i.putExtra(TYPE, "TopStories");
			break;
		case R.id.TextView01:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/world/rss.xml");
			i.putExtra(TYPE, "World");
			break;
		case R.id.TextView02:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/uk/rss.xml");
			i.putExtra(TYPE, "UK");
			break;
		case R.id.TextView03:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/business/rss.xml");
			i.putExtra(TYPE, "Business");
			break;
		case R.id.TextView04:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/politics/rss.xml");
			i.putExtra(TYPE, "Politics");
			break;
		case R.id.TextView05:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/health/rss.xml");
			i.putExtra(TYPE, "Health");
			break;
		case R.id.TextView06:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/education/rss.xml");
			i.putExtra(TYPE, "EducationFamily");
			break;
		case R.id.TextView07:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/science_and_environment/rss.xml");
			i.putExtra(TYPE, "ScienceEnvironment");
			break;
		case R.id.TextView08:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/technology/rss.xml");
			i.putExtra(TYPE, "Technology");
			break;
		case R.id.TextView09:
			i.putExtra(URL, "http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml");
			i.putExtra(TYPE, "EntertainmentArts");
			break;
		default:
			break;
		}
		startActivity(i);
	}

	
}
