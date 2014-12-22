package com.example.finalproject;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowBookDetailsActivity extends Activity {

	TextView title,author,isbn,desc;
	ImageView bookimage;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_book_details);
		
		ArrayList<Book> Books=(ArrayList<Book>) getIntent().getExtras().get("BookValues");
		String Title=getIntent().getExtras().getString("Title");
		title=(TextView)findViewById(R.id.textView1);		
		author=(TextView)findViewById(R.id.textView2);		
		isbn=(TextView)findViewById(R.id.textView3);		
		desc=(TextView)findViewById(R.id.textView4);
		
		bookimage=(ImageView)findViewById(R.id.imageView1);
		
		for(Book b:Books)
		{
			if(b.getBooktitle().equalsIgnoreCase(Title))
			{
				title.setText(b.getBooktitle());
				author.setText(b.getBookauthor());
				isbn.setText(b.getBookisbn());
				desc.setText(b.getBookdesc());
				if(b.getBookimageurl().isEmpty())
				{
					bookimage.setImageResource(R.drawable.nobookicon);
				}
				else
				{
					Picasso.with(this).load(b.getBookimageurl()).into(bookimage);	
				}
				
			}
				
		}		
	}
}
