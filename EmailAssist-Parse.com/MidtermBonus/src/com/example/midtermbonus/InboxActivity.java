package com.example.midtermbonus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * MidTermBonus
 *InboxActivity.java
 * @author HARISHSAINATH GANAPATHY(800833319)
 *
 */
public class InboxActivity extends Activity {

	ListView messages;
	ArrayList<MessageDetail> al=new ArrayList<MessageDetail>();
	 MessageAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inbox);
		
		
		messages=(ListView)findViewById(R.id.listViewInbox);
		ParseUser user=ParseUser.getCurrentUser();
		al=new ArrayList<MessageDetail>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
        query.whereEqualTo("receiver", user.get("firstname")+" "+user.get("lastname"));
        query.findInBackground(new FindCallback<ParseObject>() {
	
		@Override
		public void done(List<ParseObject> objects, ParseException e) {
			
			if(!objects.isEmpty())
			{
				for(ParseObject obj:objects)
				{
					MessageDetail md=new MessageDetail();
										
					md.setSender(obj.get("sender").toString());
					Date d=	obj.getCreatedAt();
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
					
					md.setDate(dateFormat.format(d));
					
					md.setMessage(obj.get("message").toString());
					if(obj.get("isRead").toString().equals("true"))
					{
						md.setRead(true);
					}
					else
					{
						md.setRead(false);
					}
					
					al.add(md);					
				}			   
				adapter = new MessageAdapter(InboxActivity.this, R.layout.inboxmessage, al);
				messages.setAdapter(adapter);
			}			
		}
       });
        
        messages.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ParseUser user=ParseUser.getCurrentUser();
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
	        query.whereEqualTo("receiver", user.get("firstname")+" "+user.get("lastname"));
	        String ID = null;
			try {
				List<ParseObject> temp=query.find();
				for(ParseObject obj:temp)
				{
					ID=obj.getObjectId();
				}
				
				ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Message");
				query1.getInBackground(ID, new GetCallback<ParseObject>() {
				  public void done(ParseObject obj, ParseException e) {
				    if (e == null) {
				    obj.put("isRead", false);
				      obj.saveInBackground();
				    }
				  }
				});
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Intent i=new Intent(InboxActivity.this,ReadMsgActivity.class);
			i.putExtra("Message", al.get(position).getMessage());
			i.putExtra("Sender", al.get(position).getSender());
			startActivity(i);
			
		}
	});
    
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.inbox_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_compose) {
			//code for composing new message
			
			Intent i=new Intent(InboxActivity.this,ComposeMsgActivity.class);
			i.putExtra("Sender", "");
		    startActivity(i);
			return true;
			
		} else if (id == R.id.action_refresh) {
			//code for refresh 
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/*@Override
	protected void onDestroy() {
		
		super.onDestroy();
		ParseUser.logOut();
	}*/
}
