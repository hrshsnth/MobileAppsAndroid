package com.example.finalproject;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class BookReviewsView extends Fragment {

	WebView w;
	String value;
	boolean TitleISBNCheck;
	public BookReviewsView(boolean check,String input)
	{
		this.value=input;
		this.TitleISBNCheck=check;
	}
	
	public BookReviewsView() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//String url=getArguments().getString("ReviewValues");
		View view=inflater.inflate(R.layout.bookreviews, container, false);
		
		w=(WebView)view.findViewById(R.id.webView1);
		if(TitleISBNCheck)
		{
			new JSONAsyncTask(container.getContext(),w).execute("https://www.goodreads.com/book/isbn?isbn="+value+"&key=WoTRDvuZFiEl2EQ2XkWPtQ&format=json");	
		}
		else
		{
			String inputtitle =value.replaceAll(" ","%20");
			new JSONAsyncTask(container.getContext(),w).execute("https://www.goodreads.com/book/title.json?&key=WoTRDvuZFiEl2EQ2XkWPtQ&title="+inputtitle);
		}
		
				
		return view;
	}
	
}
