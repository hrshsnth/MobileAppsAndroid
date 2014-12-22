package com.example.finalproject;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class BookListActivity extends Activity {

	BookAdapter adapter;
	ListView booklist;
	String value;
	boolean TitleOrISBN=false;
	ArrayList<Book> Booktemp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		
		booklist=(ListView)findViewById(R.id.listView1);
		
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
		
		
		if(TitleOrISBN)
		{
			new BookGoogleAsync("List",this,this).execute("https://www.googleapis.com/books/v1/volumes?q="+value+"&key=AIzaSyBughvYoGGK6QpA-RFkN07kn5nPwp75mO0");	
		}
		else
		{
			String inputtitle =value.replaceAll(" ","%20");
			new BookGoogleAsync("List",this,this).execute("https://www.googleapis.com/books/v1/volumes?q="+inputtitle+"&key=AIzaSyBughvYoGGK6QpA-RFkN07kn5nPwp75mO0");
		}	
		
		//new BookGoogleAsync("List", this,this).execute("https://www.googleapis.com/books/v1/volumes?q="+value+"&key=AIzaSyBughvYoGGK6QpA-RFkN07kn5nPwp75mO0");
		
	}
	
	public void setupData(ArrayList<Book> book)
	{
		adapter = new BookAdapter(BookListActivity.this, R.layout.listbook, book);
    	booklist.setAdapter(adapter);
    	Booktemp=new ArrayList<Book>();
    	Booktemp.addAll(book);
    	booklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i=new Intent(BookListActivity.this,BookDetailsActivity.class);
				i.putExtra("ISBN", Booktemp.get(position).getBookisbn());
				i.putExtra("Title",Booktemp.get(position).getBooktitle());
				startActivity(i);
			}
		});
	}
}
