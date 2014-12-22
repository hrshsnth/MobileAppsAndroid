package com.example.finalproject;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookPriceView extends Fragment{

	TextView ebaytitle,ebayprice,ebayshipping,amazontitle,amazonprice;
	String  value;
	String GetTitle;
	boolean TitleISBNCheck;
	ImageView ebayimg,azimg;
	LinearLayout elayout,alayout;

	public BookPriceView(boolean check,String input) {
		this.value=input;
		this.TitleISBNCheck=check;
		
		// TODO Auto-generated constructor stub
	}
	String titleval,authorval,isbnval,descval,imageval;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.bookprice, container, false);
		ebaytitle=(TextView)view.findViewById(R.id.textView1);
		ebayprice=(TextView)view.findViewById(R.id.textView2);
		ebayshipping=(TextView)view.findViewById(R.id.textView3);
		ebayimg=(ImageView)view.findViewById(R.id.imageView1);
		amazontitle=(TextView)view.findViewById(R.id.textView4);
		amazonprice=(TextView)view.findViewById(R.id.textView5);
		azimg=(ImageView)view.findViewById(R.id.imageView2);
		
		
		String inputtitle =value.replaceAll(" ","%20");
		if(TitleISBNCheck)
		{
			new BookGoogleAsync("BookPrice",container.getContext(),ebaytitle,ebayprice,ebayshipping,ebayimg,amazontitle,amazonprice,azimg)
			.execute("https://www.googleapis.com/books/v1/volumes?q="+value+"&key=AIzaSyBughvYoGGK6QpA-RFkN07kn5nPwp75mO0");	
		}
		else
		{		
			new BookGoogleAsync("BookPrice",container.getContext(),ebaytitle,ebayprice,ebayshipping,ebayimg,amazontitle,amazonprice,azimg)
			.execute("https://www.googleapis.com/books/v1/volumes?q="+inputtitle+"&key=AIzaSyBughvYoGGK6QpA-RFkN07kn5nPwp75mO0");
		}			
		return view;
	}

	
}
