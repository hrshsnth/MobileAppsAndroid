package com.example.hw7;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * HW7
 * PhotoAdapter.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class PhotoAdapter extends ArrayAdapter<Search>{


	Context context;
	int mresource;
	ArrayList<Search> data;
	ImageView thumbnail;
	public PhotoAdapter(Context context, int resource, ArrayList<Search> result) {
		super(context, resource, result);
		
		this.context = context;
		this.mresource = resource;
		this.data = result;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(mresource, parent, false);
		}
		Search p=data.get(position);
		
		thumbnail = (ImageView) convertView.findViewById(R.id.imageView1);
		if(p.getPhotoURL()!=null || p.getPhotoURL().length()!=0 || p.getPhotoURL()!="")
		{
			Picasso.with(this.context).load(p.getPhotoURL()).into(thumbnail);	
		}
		else
		{
			thumbnail.setImageResource(R.drawable.photo_not_found);
		}
		
		TextView photoname = (TextView) convertView.findViewById(R.id.textView1);
		photoname.setText(p.getPhotoName());
		return convertView;
	}

}
