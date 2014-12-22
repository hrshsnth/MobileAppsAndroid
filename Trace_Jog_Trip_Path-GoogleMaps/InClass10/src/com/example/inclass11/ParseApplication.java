package com.example.inclass11;

import com.parse.Parse;
import com.parse.ParseACL;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		

		// Add your initialization code here
	    Parse.initialize(this, "wyaWNlB34zteT8IQp4FSlK9MVjtvzVAPYG4s0l2G", "K5JcBqVopQrYBS1C56LyULCw8yZAf0M93szm1qJ9");
	    
	   /* ParseObject testObject = new ParseObject("Check");
	    testObject.put("harish", "harish");
	    testObject.saveInBackground();*/

	   // ParseUser.enableAutomaticUser();
	    ParseACL defaultACL = new ParseACL();
	      
	    // If you would like all objects to be private by default, remove this line.
	    defaultACL.setPublicReadAccess(true);
	    
	    ParseACL.setDefaultACL(defaultACL, true);
}
}
