package com.example.finalproject;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class WishListActivity extends Activity {

	ListView wishlist;
	ArrayList<String> titles;
	ArrayAdapter<String> Arrayadapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list);
		
		wishlist=(ListView)findViewById(R.id.listView1);
		titles=new ArrayList<String>();
		ParseUser user=ParseUser.getCurrentUser();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("BookWishList");
		query.whereEqualTo("User",user.getUsername());
		
		List<ParseObject> listObj;
		try {
			listObj = query.find();
			if(!listObj.isEmpty())
			{
				for(ParseObject obj:listObj)
				{
					titles.add(obj.get("BookTitle").toString());
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Arrayadapter=new ArrayAdapter<String>(WishListActivity.this, android.R.layout.simple_list_item_1, titles);
		wishlist.setAdapter(Arrayadapter);
		
		wishlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent i=new Intent(WishListActivity.this,BookListActivity.class);
				i.putExtra("ISBN","");
				i.putExtra("Title", titles.get(position));
				startActivity(i);
			}
		});
		
		wishlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ParseUser user=ParseUser.getCurrentUser();
				ParseQuery<ParseObject> query = ParseQuery.getQuery("BookWishList");		
				query.whereEqualTo("User",user.getUsername());
				query.whereEqualTo("BookTitle", titles.get(position));
				try {
					List<ParseObject> list=query.find();
					for(ParseObject obj:list)
					{
						if(obj.get("BookTitle").toString().equalsIgnoreCase(titles.get(position)))
						{
							obj.delete();							
							titles.remove(titles.get(position));
							Toast.makeText(WishListActivity.this, "Deleted Book from Wish List", Toast.LENGTH_SHORT).show();
						}
					}
					Arrayadapter.notifyDataSetChanged();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;				
			}
		});			
			Arrayadapter.setNotifyOnChange(true);
		
	}
}
