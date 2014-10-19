package com.example.inclass7;

import java.util.ArrayList;
import java.util.LinkedList;
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

/** InclassAssign 7
 * FileName : NewsAdapter.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class NewsAdapter extends ArrayAdapter<News>{


	Context context;
	int mresource;
	List<News> data;

	public NewsAdapter(Context context, int resource, LinkedList<News> objects) {
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
		News news = data.get(position);
		TextView desc = (TextView) convertView.findViewById(R.id.imagetext);
		ImageView thumbnail = (ImageView) convertView.findViewById(R.id.rowimage);
		Picasso.with(this.context).load(news.getThumbnailSmall()).into(thumbnail);
		desc.setText(news.getTitle());
		return convertView;
	}

}
