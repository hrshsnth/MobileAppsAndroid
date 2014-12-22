package com.example.midtermbonus;

import java.util.ArrayList;
import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeMsgActivity extends Activity {

	ImageView contacts;
	AlertDialog.Builder builder;
	AlertDialog dialog;
	ArrayList<String> al;
	String[] dialogvalues;
	TextView to;
	 List<ParseObject> list;
	 Button send;
	 EditText Message;
	 
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_msg);
		
		contacts=(ImageView)findViewById(R.id.imageViewContacts);
		builder=new AlertDialog.Builder(this);
		al=new ArrayList<String>();
		to=(TextView)findViewById(R.id.textViewTo);
		Message=(EditText)findViewById(R.id.editTextMessage);
		send=(Button)findViewById(R.id.buttonSend);
		
		if(getIntent().getExtras().getString("Sender").equals(""))
		{
			contacts.setClickable(true);
			contacts.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 //ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
					 ParseQuery<ParseUser> query = ParseUser.getQuery();
					 query.findInBackground(new FindCallback<ParseUser>() {
						  public void done(List<ParseUser> objects, ParseException e) {
						    if (e == null) {
						      for(ParseObject obj:objects)
						      {
						    	  al.add(obj.get("firstname").toString()+" "+obj.get("lastname").toString());
						      }
						      
						      Log.d("Users", al+"");
								dialogvalues=al.toArray(new String[al.size()]);
								builder.setTitle("Users").setItems(dialogvalues, new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										
										to.setText(dialogvalues[which]);
										
									}
								});
								
								dialog=builder.create();
								dialog.show();
						      
						    } 
						  }
						});
					
				}
			});
		}
		else
		{
			contacts.setClickable(false);			
			to.setText(getIntent().getExtras().getString("Sender"));
		}
		
		
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(to.getText().toString().equals("") || Message.getEditableText().toString().equals(""))
				{
					 Toast.makeText(ComposeMsgActivity.this, "Please Choose Contact & Fill Message", Toast.LENGTH_SHORT).show();
				}
				else
				{
					
					ParseObject obj=new ParseObject("Message");
					
					ParseUser user= ParseUser.getCurrentUser();
					
					obj.put("sender", user.get("firstname")+" "+user.get("lastname"));
					obj.put("receiver",to.getText().toString());
					obj.put("message", Message.getEditableText().toString());
					obj.put("isRead",true);
					obj.saveInBackground();
					 Toast.makeText(ComposeMsgActivity.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();
					Intent i=new Intent(ComposeMsgActivity.this,InboxActivity.class);
					ComposeMsgActivity.this.finish();
					startActivity(i);
					
					
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
