package com.example.finalproject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailsActivity extends FragmentActivity implements ActionBar.TabListener {

	TextView title,author,isbn,desc;
	ImageView bookimage;

	private ViewPager viewPager;
    private SwipeAdapter adapter;

    ActionBar.Tab bookspecs, bookreviews, bookprice;

	ActionBar actionBar;
	String value;
	boolean TitleOrISBN=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_details);
		
		if(getIntent().getExtras().getString("ISBN").equals(""))
		{
			value=getIntent().getExtras().getString("Title");	
			TitleOrISBN=false;
		}
		else if(getIntent().getExtras().getString("Title").equals(""))
		{
			value=getIntent().getExtras().getString("ISBN");
			TitleOrISBN=true;
		}
		else
		{
			value=getIntent().getExtras().getString("ISBN");
			TitleOrISBN=true;
		}
				
		viewPager = (ViewPager) findViewById(R.id.activity_bookDetails);
	    adapter = new SwipeAdapter(getSupportFragmentManager());
	    
	     
	    viewPager.setAdapter(adapter);
		actionBar = getActionBar();
		
		actionBar.setDisplayShowHomeEnabled(false);

		actionBar.setDisplayShowTitleEnabled(false);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		bookspecs=actionBar.newTab().setText("Book Detail");
		bookreviews=actionBar.newTab().setText("Book Reviews");
		bookprice=actionBar.newTab().setText("Book Price");
		
		bookspecs.setTabListener(this);
		bookreviews.setTabListener(this);
		bookprice.setTabListener(this);
		
		actionBar.addTab(bookspecs);
		actionBar.addTab(bookreviews);
		actionBar.addTab(bookprice);
			
		 viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub				
			}
		});		
		
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	public class SwipeAdapter extends FragmentPagerAdapter {

		public SwipeAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
		// Fragment f;
			switch(position)
			{
			case 0: 
				BookSpecsView bs=new BookSpecsView(TitleOrISBN,value);
				return bs;			
			case 1:
				BookReviewsView br=new BookReviewsView(TitleOrISBN,value);
				return br;				
			case 2:
				BookPriceView bp=new BookPriceView(TitleOrISBN,value);
				return bp;
			}
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
	 	
	}	
	
}
