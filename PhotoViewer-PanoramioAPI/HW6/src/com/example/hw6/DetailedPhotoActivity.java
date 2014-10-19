package com.example.hw6;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * HW #6 FileName : DetailedPhotoActivity.java
 * 
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy
 *         Subramaniam(800833319)
 * 
 */

public class DetailedPhotoActivity extends Activity {

	String title, pubdate, imageurl, owner;
	TextView tvtitle, tvpubdate, tvowner;
	ImageView iv;
	int position;
	private float x1, x2;
	static final int MIN_DISTANCE = 150;
	ArrayList<Photo> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_photo);
		title = getIntent().getExtras().getString(MainActivity.TITLE_KEY);
		position = getIntent().getExtras().getInt(MainActivity.INDEX_KEY);
		imageurl = getIntent().getExtras().getString(MainActivity.IMGURL_KEY);
		pubdate = getIntent().getExtras().getString(MainActivity.PUBDATE_KEY);
		owner = getIntent().getExtras().getString(MainActivity.OWNER_KEY);
		list = getIntent().getExtras().getParcelableArrayList(
				MainActivity.LIST_KEY);
		tvtitle = (TextView) findViewById(R.id.textView1);
		tvpubdate = (TextView) findViewById(R.id.textView2);
		tvowner = (TextView) findViewById(R.id.textView3);
		iv = (ImageView) findViewById(R.id.imageView1);
		tvtitle.setText(title);
		tvpubdate.setText(pubdate);
		tvowner.setText("By:" + owner);
		Picasso.with(getApplicationContext()).load(imageurl).resize(300, 300)
				.into(iv);
		System.out.println("displayed item at position :" + position);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x1 = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			x2 = event.getX();
			float deltaX = x2 - x1;

			if (Math.abs(deltaX) > MIN_DISTANCE) {

				// Left to Right swipe action
				if (x2 > x1) {
					if (position == 0) {
						position = list.size() - 1;
					} else {
						position = position - 1;
					}

				}

				// Right to left swipe action
				else {
					if (position == list.size() - 1) {
						position = 0;
					} else {
						position = position + 1;
					}

				}
				System.out.println("displaying  item at position :" + position);
				tvtitle.setText(list.get(position).getTitle());
				tvpubdate.setText(list.get(position).getPubdate());
				tvowner.setText("By:" + list.get(position).getOwner_name());
				Picasso.with(getApplicationContext())
						.load(list.get(position).getImage_url()).resize(300, 300).into(iv);

			}
			break;
		}
		return super.onTouchEvent(event);
	}
}
