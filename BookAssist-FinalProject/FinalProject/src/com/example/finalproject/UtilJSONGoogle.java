package com.example.finalproject;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class UtilJSONGoogle  {
	
	static public class AppJSONParser{
		static public ArrayList<Book> parsedetail(String in,String toggle) throws IOException, JSONException
		{ 
			ArrayList<Book> book=new ArrayList<Book>();
			JSONObject root=new JSONObject(in);
			JSONArray items=root.getJSONArray("items");
			Log.d("Entered Google UTIL", "Google UTIL");
			if(toggle.equalsIgnoreCase("List"))
			{
				Log.d("Entered Google UTIL", "BookList");
				for(int i=0;i<items.length();i++)
				{
					Book b=new Book();
					
					JSONObject first=items.getJSONObject(i);
					JSONObject volumeinfo=first.getJSONObject("volumeInfo");
					String title = null;
					if(volumeinfo.getString("title")!=null)
					{
						title= volumeinfo.getString("title");	
					}				
					//Log.d("title", title);
					b.setBooktitle(title);
					String authornames="";
					if(volumeinfo.has("authors"))
					{
						JSONArray authors=volumeinfo.getJSONArray("authors");
										
						for(int j=0;j<authors.length();j++)
						{
							if(authors.getString(j)!=null)
							{
								authornames=authornames+","+authors.getString(j);	
							}					
						}
					}
					
					//Log.d("author", authornames);
					b.setBookauthor(authornames);
					String isbnNum="";
					if(volumeinfo.has("industryIdentifiers"))
					{
						JSONArray isbnarray=volumeinfo.getJSONArray("industryIdentifiers");
						JSONObject isbn13;
						if(isbnarray.length()>1)
						{
							isbn13=(JSONObject) isbnarray.get(1);
						}
						else
						{
							isbn13=(JSONObject) isbnarray.get(0);	
						}						
						isbnNum=isbn13.getString("identifier");
						//Log.d("ISBN", isbnNum);
					}
					b.setBookisbn(isbnNum);
					String bookurlThumb="";
					if(volumeinfo.has("imageLinks"))
					{
						JSONObject bookurl=volumeinfo.getJSONObject("imageLinks");
						
						if(bookurl.getString("thumbnail")!=null)
						{
							bookurlThumb=bookurl.getString("thumbnail");	
						}
					}
					//Log.d("Book URL", bookurlThumb);
					b.setBookimageurl(bookurlThumb);
					String bookdesc="";
					if(volumeinfo.has("description"))
					{
						bookdesc=volumeinfo.getString("description");
					}
					b.setBookdesc(bookdesc);					
									
					book.add(b);					
				}
			}
			else
			{
				Book b=new Book();
				
				JSONObject first=items.getJSONObject(0);
				JSONObject volumeinfo=first.getJSONObject("volumeInfo");
				String title = null;
				if(volumeinfo.getString("title")!=null)
				{
					title= volumeinfo.getString("title");	
				}				
				//Log.d("title", title);
				b.setBooktitle(title);
				String authornames="";
				if(volumeinfo.has("authors"))
				{
					JSONArray authors=volumeinfo.getJSONArray("authors");
									
					for(int j=0;j<authors.length();j++)
					{
						if(authors.getString(j)!=null)
						{
							authornames=authornames+","+authors.getString(j);	
						}					
					}
				}
				//Log.d("author", authornames);
				b.setBookauthor(authornames);
				String isbnNum="";
				if(volumeinfo.has("industryIdentifiers"))
				{
					JSONArray isbnarray=volumeinfo.getJSONArray("industryIdentifiers");
					JSONObject isbn13;
					if(isbnarray.length()>1)
					{
						isbn13=(JSONObject) isbnarray.get(1);
					}
					else
					{
						isbn13=(JSONObject) isbnarray.get(0);	
					}		
					isbnNum=isbn13.getString("identifier");
					Log.d("ISBN", isbnNum);
				}
			
				b.setBookisbn(isbnNum);
				String bookurlThumb="";
				if(volumeinfo.has("imageLinks"))
				{
					JSONObject bookurl=volumeinfo.getJSONObject("imageLinks");
					
					if(bookurl.getString("thumbnail")!=null)
					{
						bookurlThumb=bookurl.getString("thumbnail");	
					}
				}			
				
				//Log.d("Book URL", bookurlThumb);
				b.setBookimageurl(bookurlThumb);
				String bookdesc="";
				if(volumeinfo.has("description"))
				{
					bookdesc=volumeinfo.getString("description");
				}
				b.setBookdesc(bookdesc);					
								
				book.add(b);
			}			
		return book;
		}
	}
}
