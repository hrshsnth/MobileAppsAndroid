package com.example.midtermbonus;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		

		// Add your initialization code here
	    Parse.initialize(this, "sFTJrom5EYD9vc50zrnEIpNkY77KgbBbLzgUZ9Zr", "aWisArgeygPodwGd2W299gSEoRaE8uP6FMhBP5H8");
	    
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
