package com.example.midtermbonus;

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

public class LoginActivity extends Activity {

	
		
		EditText username,password;
		Button login,createaccount;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			
			ParseUser currentUser = ParseUser.getCurrentUser();
			if (currentUser != null) {
				
				Intent i=new Intent(this,InboxActivity.class);
				startActivity(i);
				this.finish();
				//setContentView(R.layout.activity_events);
			} else {
				setContentView(R.layout.activity_login);
				username=(EditText)findViewById(R.id.editTextUsername);
				password=(EditText)findViewById(R.id.editTextPassword);
				login=(Button)findViewById(R.id.buttonLogin);
				createaccount=(Button)findViewById(R.id.buttonSignup);
				
				login.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
				
						ParseUser.logInInBackground(username.getEditableText().toString(), password.getEditableText().toString(), new LogInCallback() {

							@Override
							public void done(ParseUser user, ParseException e) {
								 if (user != null) {
								    Intent i=new Intent(LoginActivity.this,InboxActivity.class);
								    startActivity(i);
								    } else {
								     Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
								    }
							}
							 
							});
						
					}
				});
				
				createaccount.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
					LoginActivity.this.finish();
					startActivity(i);
					}
				});
				
			}
			
		}
		@Override
		protected void onDestroy() {
			
			super.onDestroy();
			ParseUser.logOut();
		}
	
}