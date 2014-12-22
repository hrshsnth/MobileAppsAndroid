package com.example.midtermbonus;

import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {

	EditText firstname,lastname,username,password,confpassword;
	Button signup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	
		firstname=(EditText)findViewById(R.id.editTextFirstName);
			lastname=(EditText)findViewById(R.id.editTextLastName);
			username=(EditText)findViewById(R.id.editTextUsername);
			password=(EditText)findViewById(R.id.editTextPassword);
			confpassword=(EditText)findViewById(R.id.editTextConfirmPassword);
			
			
			signup=(Button)findViewById(R.id.buttonSignup);
			
			
			signup.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				if(firstname.getEditableText().toString().equals("") || lastname.getEditableText().toString().equals("") || 
						username.getEditableText().toString().equals("") ||
						password.getEditableText().toString().equals("") ||
						confpassword.getEditableText().toString().equals("")
						)	
				{
					Toast.makeText(SignUpActivity.this, "Please Enter all the details", Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(password.getEditableText().toString().equals(confpassword.getEditableText().toString()))
					{
						
						ParseQuery<ParseUser> query = ParseUser.getQuery();
						query.whereEqualTo("username", username.getEditableText().toString());
						query.findInBackground(new FindCallback<ParseUser>() {
							
							@Override
							public void done(List<ParseUser> objects, ParseException e) {
								
									if(!objects.isEmpty())
									{
										Toast.makeText(SignUpActivity.this, "Username Already Registered", Toast.LENGTH_SHORT).show();	
									}
									else
									{
										ParseUser user = new ParseUser();
										user.setUsername(username.getEditableText().toString());
										user.put("firstname",firstname.getEditableText().toString() );
										user.put("lastname", lastname.getEditableText().toString());
										user.setPassword(password.getEditableText().toString());
										user.signUpInBackground(new SignUpCallback() {

											@Override
											public void done(ParseException e) {
												if(e==null)
												{
													Toast.makeText(SignUpActivity.this, "User Successfully Logged In", Toast.LENGTH_SHORT).show();
													Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
													SignUpActivity.this.finish();
													startActivity(i);
												}
												
											}
											
										});
										
									}
																		
							}
						});
						
						
						
					}
					else
					{
						Toast.makeText(SignUpActivity.this, "Password did not match", Toast.LENGTH_SHORT).show();
					}
					
					
				
				}
				
					
					
				}
			});
			
					
			
	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		ParseUser.logOut();
	}
}
