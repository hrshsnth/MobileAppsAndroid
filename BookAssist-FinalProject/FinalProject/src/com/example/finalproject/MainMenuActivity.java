package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class MainMenuActivity extends FragmentActivity implements View.OnClickListener{

	public final static int PAGES = 10;
	// You can choose a bigger number for LOOPS, but you know, nobody will fling
	// more than 1000 times just in order to test your "infinite" ViewPager :D 
	public final static int LOOPS = 10; 
	public final static int FIRST_PAGE = 9;
	public final static float BIG_SCALE = 1.0f;
	public final static float SMALL_SCALE = 0.8f;
	public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
	
	public MyPagerAdapter adapter;
	public ViewPager pager;
	public  ArrayList<Book> bookList;
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ParseUser.logOut();
	}
	Button search,myFavourites,wishlist;
	Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		System.out.println(modifiedDate); 
		new BestSellersAsync(this).execute("http://api.nytimes.com/svc/books/v3/lists/"+modifiedDate+"/hardcover-fiction.json?&api-key=901978f1eb2af7a91db25bd380c738ce:9:70249740");	
		search=(Button)findViewById(R.id.button1);
		myFavourites=(Button)findViewById(R.id.button2);	
		wishlist=(Button)findViewById(R.id.button3);
		search.setOnClickListener(this);
		myFavourites.setOnClickListener(this);
		wishlist.setOnClickListener(this);
		
	}
	
	
	public void setUpData(){
		pager = (ViewPager) findViewById(R.id.myviewpager);
		adapter = new MyPagerAdapter(this, this.getSupportFragmentManager(),bookList);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(adapter);
		// Set current item to the middle page so we can fling to both
		// directions left and right
		pager.setCurrentItem(FIRST_PAGE);
	
		// Necessary or the pager will only have one extra page to show
		// make this at least however many pages you can see
		pager.setOffscreenPageLimit(3);
		
		// Set margin for pages as a negative number, so a part of next and 
		// previous pages will be showed
		pager.setPageMargin(Integer.parseInt(getString(R.string.pagermargin)));
		
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1)
		{
			i=new Intent(MainMenuActivity.this,SearchActivity.class);
			startActivity(i);
		}
		else if(v.getId()==R.id.button2)
		{
			i=new Intent(MainMenuActivity.this,CatalogueActivity.class);
			startActivity(i);
		}
		else if(v.getId()==R.id.myviewpager){
			Log.d("Hi", "this");
		}
		else if(v.getId()==R.id.button3)
		{
			i=new Intent(MainMenuActivity.this,WishListActivity.class);
			startActivity(i);
		}
		
	}

}
