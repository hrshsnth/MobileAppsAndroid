package com.example.finalproject;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CatalogFragment extends Fragment {

	ListView list;
	View view;
	ArrayList<Book> Values;
	ArrayList<String> temp;
	ArrayAdapter<String> adapter;
	ArrayList<String> books;
	public CatalogFragment(ArrayList<Book> book, ArrayAdapter<String> adapt,ArrayList<String> listVals)
	{
		this.Values=book;
		this.adapter=adapt;
		this.books=listVals;		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.catalogfragment, container, false);
		list=(ListView)view.findViewById(R.id.listView1);
		
		if(adapter!=null && !books.isEmpty())
		{
			 list.setAdapter(adapter);
			 
			 list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent i=new Intent(getActivity(),ShowBookDetailsActivity.class);
					i.putExtra("BookValues", Values);
					i.putExtra("Title", books.get(position));
					startActivity(i);					
				}
			});
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ParseUser user=ParseUser.getCurrentUser();
				ParseQuery<ParseObject> query = ParseQuery.getQuery("BookCatalogue");		
				query.whereEqualTo("User",user.getUsername());
				query.whereEqualTo("BookTitle", books.get(position));
				try {
					List<ParseObject> list=query.find();
					for(ParseObject obj:list)
					{
						if(obj.get("BookTitle").toString().equalsIgnoreCase(books.get(position)))
						{
							obj.delete();							
							books.remove(books.get(position));
							Toast.makeText(getActivity(), "Deleted Book from Shelf", Toast.LENGTH_SHORT).show();
						}
					}
					adapter.notifyDataSetChanged();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;				
			}
		});			
			adapter.setNotifyOnChange(true);		
		}
		return view;
	}		
}
