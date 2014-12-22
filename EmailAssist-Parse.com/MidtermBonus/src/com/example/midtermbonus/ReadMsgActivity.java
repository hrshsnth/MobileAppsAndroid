package com.example.midtermbonus;

import java.util.List;

import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ReadMsgActivity extends Activity {

	TextView to,message;
	List<ParseObject> obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_msg);
		
		to=(TextView)findViewById(R.id.textViewTo);
		message=(TextView)findViewById(R.id.textViewMessage);
		
		String msg=getIntent().getExtras().getString("Message");
		String sender=getIntent().getExtras().getString("Sender");
		to.setText(sender);
		message.setText(msg);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.read_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_delete) {
			//code for delete message
		
			String name=message.getText().toString();
			String idValue = null;
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
			query.whereEqualTo("message",name );
	       
	        try {
				List<ParseObject> obj=query.find();
			for(ParseObject ob:obj)
			{
				idValue=ob.getObjectId();
				ob.delete();
			}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	     /*   ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Message");
	        query1.getInBackground(idValue, new GetCallback<ParseObject>() {
	          public void done(ParseObject object, ParseException e) {
	            if (e == null) {
	             try {
					object.delete();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	             Log.d("Deteled", "DELETED");
	            } 
	          }
	        });*/
	        
	        Toast.makeText(ReadMsgActivity.this, "Message Deleted", Toast.LENGTH_SHORT).show();
	        Intent i=new Intent(ReadMsgActivity.this,InboxActivity.class);
	        ReadMsgActivity.this.finish();
			startActivity(i);
			
			return true;
		} else if (id == R.id.action_reply) {
			//code for reply
			Intent i=new Intent(ReadMsgActivity.this,ComposeMsgActivity.class);
			i.putExtra("Sender", to.getText());
	        ReadMsgActivity.this.finish();
			startActivity(i);
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
