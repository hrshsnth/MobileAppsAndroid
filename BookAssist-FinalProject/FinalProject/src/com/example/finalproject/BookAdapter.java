package com.example.finalproject;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookAdapter extends ArrayAdapter<Book>{

	Context context;
	int mresource;
	List<Book> data;

	public BookAdapter(Context context, int resource, ArrayList<Book> objects) {
		super(context, resource, objects);
		
		this.context = context;
		this.mresource = resource;
		this.data = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(mresource, parent, false);
		}
		Book book = data.get(position);
		TextView title = (TextView) convertView.findViewById(R.id.textView1);
		ImageView thumbnail = (ImageView) convertView.findViewById(R.id.imageView1);
		if(book.getBookimageurl().isEmpty())
		{
			thumbnail.setImageResource(R.drawable.nobookicon);						
		}
		else	
		{
			Log.d("BookImage", book.getBookimageurl());
			Picasso.with(this.context).load(book.getBookimageurl()).into(thumbnail);		
		}
		
		title.setText(book.getBooktitle());
		return convertView;
	}

}
