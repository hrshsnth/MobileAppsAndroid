package com.example.finalproject;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {	

	EditText username,password;
	Button login,createaccount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        login=(Button)findViewById(R.id.button1);
        
    	ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			
			Intent i=new Intent(this,MainMenuActivity.class);
			startActivity(i);
			this.finish();
			//setContentView(R.layout.activity_events);
		} else {
			setContentView(R.layout.activity_main);
			username=(EditText)findViewById(R.id.editText1);
			password=(EditText)findViewById(R.id.editText2);
			login=(Button)findViewById(R.id.button1);
			createaccount=(Button)findViewById(R.id.button2);
			
			login.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
			
					ParseUser.logInInBackground(username.getEditableText().toString(), password.getEditableText().toString(), new LogInCallback() {

						@Override
						public void done(ParseUser user, ParseException e) {
							 if (user != null) {
							    Intent i=new Intent(MainActivity.this,MainMenuActivity.class);
							    startActivity(i);
							    finish();
							    } else {
							     Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
							    }
						}
						 
						});
					
				}
			});
			
			createaccount.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,SignUpActivity.class);
				MainActivity.this.finish();
				startActivity(i);
				}
			});
			
		}
		
	}    
}
