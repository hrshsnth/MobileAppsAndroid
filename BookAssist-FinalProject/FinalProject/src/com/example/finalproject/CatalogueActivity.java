package com.example.finalproject;

import java.util.ArrayList;
import java.util.List;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ArrayAdapter;

public class CatalogueActivity extends FragmentActivity implements ActionBar.TabListener{

	private ViewPager viewPager;
	ArrayAdapter<String> Arrayadapter;
	String[] books;
	ArrayList<String> templist;
    private SwipeAdapterCatalog adapter;    
    ActionBar.Tab Education,Novel,Comics,Music,Sports,Health;
	ActionBar actionBar;
	ArrayList<Book> EducationList,NovelList,ComicsList,MusicList,sportsList,HealthList;
	ArrayList<Book> temp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalogue);
		
		viewPager = (ViewPager) findViewById(R.id.activity_catalogDetails);
		adapter = new SwipeAdapterCatalog(getSupportFragmentManager());
		
		viewPager.setAdapter(adapter);
		actionBar = getActionBar();

		actionBar.setDisplayShowHomeEnabled(false);

		actionBar.setDisplayShowTitleEnabled(false);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Education = actionBar.newTab().setText("Education");
		Novel = actionBar.newTab().setText("Novel");
		Comics = actionBar.newTab().setText("Comics");
		Music = actionBar.newTab().setText("Music");
		Sports = actionBar.newTab().setText("Sports");
		Health = actionBar.newTab().setText("Health");

		Education.setTabListener(this);
		Novel.setTabListener(this);
		Comics.setTabListener(this);
		Music.setTabListener(this);
		Sports.setTabListener(this);
		Health.setTabListener(this);

		actionBar.addTab(Education);
		actionBar.addTab(Novel);
		actionBar.addTab(Comics);
		actionBar.addTab(Music);
		actionBar.addTab(Sports);
		actionBar.addTab(Health);

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
		
		ParseUser user=ParseUser.getCurrentUser();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("BookCatalogue");
		query.whereEqualTo("User",user.getUsername());
		query.whereEqualTo("BookCategory", "Education");
		try {
			EducationList=getfromParse(query);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Log.d("Education List", EducationList.toString());
		if(EducationList==null || EducationList.isEmpty())
		{
			EducationList=new ArrayList<Book>();
		}
		
		ParseQuery<ParseObject> query1 = ParseQuery.getQuery("BookCatalogue");
		query1.whereEqualTo("User",user.getUsername());
		query1.whereEqualTo("BookCategory", "Novel");
		try {
			NovelList=getfromParse(query1);
		} catch (ParseException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		if(NovelList==null || NovelList.isEmpty())
		{
			NovelList=new ArrayList<Book>();
		}
		
		//Log.d("Novel List", NovelList.toString());
		
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("BookCatalogue");
		query2.whereEqualTo("User",user.getUsername());
		query2.whereEqualTo("BookCategory", "Comics");
		try {
			ComicsList=getfromParse(query2);
		} catch (ParseException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		if(ComicsList==null || ComicsList.isEmpty())
		{
			ComicsList=new ArrayList<Book>();
		}
		
		//Log.d("Comics List", ComicsList.toString());
		
		ParseQuery<ParseObject> query3 = ParseQuery.getQuery("BookCatalogue");
		query3.whereEqualTo("User",user.getUsername());
		query3.whereEqualTo("BookCategory", "Music");
		try {
			MusicList=getfromParse(query3);
		} catch (ParseException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		//Log.d("Music List", MusicList.toString());
		if(MusicList==null || MusicList.isEmpty())
		{
			MusicList=new ArrayList<Book>();
		}
		
		
		ParseQuery<ParseObject> query4 = ParseQuery.getQuery("BookCatalogue");
		query4.whereEqualTo("User",user.getUsername());
		query4.whereEqualTo("BookCategory", "Sports");
		try {
			sportsList=getfromParse(query4);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(sportsList==null || sportsList.isEmpty())
		{
			sportsList=new ArrayList<Book>();
		}
		//Log.d("Sports List", sportsList.toString());

		ParseQuery<ParseObject> query5 = ParseQuery.getQuery("BookCatalogue");
		query5.whereEqualTo("User",user.getUsername());
		query5.whereEqualTo("BookCategory", "Health");
		try {
			HealthList=getfromParse(query5);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(HealthList==null || HealthList.isEmpty())
		{
			HealthList=new ArrayList<Book>();
		}
		//Log.d("Health List", HealthList.toString());
	}
	public ArrayList<Book> getfromParse(ParseQuery<ParseObject> getQuery) throws ParseException
	{
		temp=new ArrayList<Book>();
		List<ParseObject> listObj=getQuery.find();
			if(!listObj.isEmpty())
				{
					for(ParseObject obj:listObj)
					{
					Book b=new Book();
					b.setBooktitle(obj.get("BookTitle").toString());
					b.setBookauthor(obj.get("BookAuthor").toString());
					b.setBookisbn(obj.get("BookISBN").toString());
					b.setBookdesc(obj.get("BookDescription").toString());
					b.setBookimageurl(obj.get("BookIMGURL").toString());
					temp.add(b);					
					}
				}
			return temp;
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
	
	public class SwipeAdapterCatalog extends FragmentPagerAdapter {

		public SwipeAdapterCatalog(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
		
			CatalogFragment cf;
			switch(position)
			{
			case 0: 	
				
				//Log.d("Education List", EducationList.toString());
				templist=new ArrayList<String>();
				if(!EducationList.isEmpty())
				{
					for(Book b: EducationList)
					{
						templist.add(b.getBooktitle());
					}
					//books=templist.toArray(new String[temp.size()]);
					//Log.d("Education List", books[0]);
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				else
				{
					//books=new String[0];
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				cf=new CatalogFragment(EducationList, Arrayadapter, templist);
				return cf;	
				
			case 1:		
				//Log.d("Novel List", NovelList.toString());
				
				templist=new ArrayList<String>();
				if(!NovelList.isEmpty())
				{
					for(Book b: NovelList)
					{
						templist.add(b.getBooktitle());
					}
				 	//books=templist.toArray(new String[temp.size()]);
					//Log.d("Novel List", books[0]);
				 	Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
				}
				else
				{
					//books=new String[0];
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				cf=new CatalogFragment(NovelList, Arrayadapter, templist);
				return cf;				
			case 2:	
				//Log.d("Comics List", ComicsList.toString());
				
				templist=new ArrayList<String>();
				if(!ComicsList.isEmpty())
				{
					for(Book b: ComicsList)
					{
						templist.add(b.getBooktitle());
					}
					//books=templist.toArray(new String[temp.size()]);
					//Log.d("Comics List", books[0]);
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				else
				{
					//books=new String[0];
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				cf=new CatalogFragment(ComicsList, Arrayadapter, templist);
				return cf;				
			case 3: 	
				Log.d("Music List", MusicList.toString());
				
				templist=new ArrayList<String>();
				if(!MusicList.isEmpty())
				{
					for(Book b: MusicList)
					{
						templist.add(b.getBooktitle());
					}
				//	books=templist.toArray(new String[temp.size()]);
					//Log.d("Music List", books[0]);
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				else
				{
					//books=new String[0];
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				cf=new CatalogFragment(MusicList, Arrayadapter, templist);
				return cf;
				
			case 4:				
				Log.d("Sports List", sportsList.toString());
				templist=new ArrayList<String>();
				if(!sportsList.isEmpty())
				{
					for(Book b: sportsList)
					{
						templist.add(b.getBooktitle());
					}
				//	books=templist.toArray(new String[temp.size()]);
					//Log.d("Sports List", books[0]);
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				else
				{
					//books=new String[0];
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				cf=new CatalogFragment(sportsList, Arrayadapter, templist);
				return cf;				
			
			case 5:		
							
				templist=new ArrayList<String>();
				if(!HealthList.isEmpty())
				{
					for(Book b: HealthList)
					{
						templist.add(b.getBooktitle());
					}
					//books=templist.toArray(new String[temp.size()]);
					//Log.d("Health List", books[0]);
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				else
				{
					//books=new String[0];
					//Arrayadapter = new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, books);
					Arrayadapter=new ArrayAdapter<String>(CatalogueActivity.this, android.R.layout.simple_list_item_1, templist);
				}
				cf=new CatalogFragment(HealthList, Arrayadapter, templist);
				return cf;
			}
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 6;
		}
	 	
	}		
}
