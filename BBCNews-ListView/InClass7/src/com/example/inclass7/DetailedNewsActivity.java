package com.example.inclass7;

import java.util.concurrent.ExecutionException;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/** InClassAssign7
 * FileName : DetailedNewsActivity.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class DetailedNewsActivity extends Activity {

	String imageUrl,description,pubDate,title;
	TextView desc,titleval,dateval;
	Drawable drawable;
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_news);
		imageUrl=getIntent().getStringExtra("IMG");
		description=getIntent().getStringExtra("DESC");
		pubDate =getIntent().getStringExtra("PUB");
		title =getIntent().getStringExtra("TITLE");
		desc =(TextView) findViewById(R.id.textView4);
		titleval=(TextView)findViewById(R.id.imagetext);
		dateval=(TextView)findViewById(R.id.textView2);
		iv=(ImageView) findViewById(R.id.rowimage);
	
		Picasso.with(getApplicationContext()).load(imageUrl).into(iv);

		dateval.setText(pubDate);
		desc.setText(description);
		titleval.setText(title);

	}
}
