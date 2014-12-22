package com.example.finalproject;

import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookSpecsView extends Fragment{
	
	TextView title,author,isbn,desc;
	ImageView bookimage;
	String  value;
	View view;
	boolean TitleISBNCheck;
	Button AddtoCatalog,AddtoWish;
	AlertDialog dialog;
	AlertDialog.Builder builder;
	public BookSpecsView(){
		
	}
	public BookSpecsView(boolean check,String input)
	{
		this.value=input;	
		this.TitleISBNCheck=check;
		
	}
	String titleval,authorval,isbnval,descval,imageval;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		builder=new AlertDialog.Builder(container.getContext());
		view=inflater.inflate(R.layout.book_specs, container, false);
		title=(TextView)view.findViewById(R.id.textView1);		
		author=(TextView) view.findViewById(R.id.textView2);		
		isbn=(TextView)view.findViewById(R.id.textView3);		
		desc=(TextView)view.findViewById(R.id.textView4);
		AddtoCatalog=(Button)view.findViewById(R.id.button1);
		AddtoWish=(Button)view.findViewById(R.id.button2);
		bookimage=(ImageView)view.findViewById(R.id.imageView1);
		AddtoCatalog.setVisibility(View.VISIBLE);
		AddtoWish.setVisibility(View.VISIBLE);
		if(TitleISBNCheck)
		{
			new BookGoogleAsync("BookSpecs",container.getContext(),title,author,isbn,desc,bookimage,AddtoCatalog,AddtoWish).execute("https://www.googleapis.com/books/v1/volumes?q="+value+"&key=AIzaSyBughvYoGGK6QpA-RFkN07kn5nPwp75mO0");	
		}
		else
		{
			String inputtitle =value.replaceAll(" ","%20");
			new BookGoogleAsync("BookSpecs",container.getContext(),title,author,isbn,desc,bookimage,AddtoCatalog,AddtoWish).execute("https://www.googleapis.com/books/v1/volumes?q="+inputtitle+"&key=AIzaSyBughvYoGGK6QpA-RFkN07kn5nPwp75mO0");
		}		
		return view;
		
	}
	
}
