package com.example.finalproject;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class UtilJSONBestSeller {
	static public class AppJSONParser{
		static public ArrayList<Book> parsedetail(String in) throws IOException, JSONException
		{ 
			ArrayList<Book> book=new ArrayList<Book>();
			JSONObject root=new JSONObject(in);
			JSONObject result = root.getJSONObject("results");
			JSONArray items=result.getJSONArray("books");
			//Log.d("Entered BestSeller UTIL", "BestSEller UTIL");
			
				//Log.d("Entered BestSeller UTIL", "BookList");
				for(int i=0;i<items.length();i++)
				{
					Book b=new Book();
					
					JSONObject first=items.getJSONObject(i);
					String title=first.getString("title");
								
					//Log.d("title", title);
					b.setBooktitle(title);
					String bookurlThumb="";

							bookurlThumb=first.getString("book_image");	
				
					//Log.d("Book URL", bookurlThumb);
					b.setBookimageurl(bookurlThumb);
					String url="";
					
					url=first.getString("amazon_product_url");	
					b.setProduct_url(url);
					//Log.d("prod_url", url);	
					book.add(b);					
				}
			
					
		return book;
		}
	}
}
