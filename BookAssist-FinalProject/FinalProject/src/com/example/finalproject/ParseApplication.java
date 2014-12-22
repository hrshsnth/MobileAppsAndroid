package com.example.finalproject;

import com.parse.Parse;
import com.parse.ParseACL;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		

		// Add your initialization code here
	    Parse.initialize(this, "3XEAZCkp0F0sROvHdJaeX9S9FyEYH1HbmqnwcYvy", "QvSKvSUcReqjoPkzqiBGmkkQ5vwS0ixTsO5O9OiZ");
	    
	    ParseACL defaultACL = new ParseACL();
	      
	    // If you would like all objects to be private by default, remove this line.
	    defaultACL.setPublicReadAccess(true);
	    
	    ParseACL.setDefaultACL(defaultACL, true);
	
}
}
