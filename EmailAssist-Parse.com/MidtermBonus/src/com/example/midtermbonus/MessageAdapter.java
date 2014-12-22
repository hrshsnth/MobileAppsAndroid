package com.example.midtermbonus;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageAdapter extends ArrayAdapter<MessageDetail>{


	Context context;
	int mresource;
	ArrayList<MessageDetail> data;
	ImageView thumbnail;
	TextView sender,date,message;
	
	
	public MessageAdapter(Context context, int resource, ArrayList<MessageDetail> result) {
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
		MessageDetail p=data.get(position);
		
		thumbnail = (ImageView) convertView.findViewById(R.id.imageView1);
		sender=(TextView) convertView.findViewById(R.id.textView1);
		date=(TextView) convertView.findViewById(R.id.textView2);
		message=(TextView) convertView.findViewById(R.id.textView3);
		
		if(p.isRead)
		{
			thumbnail.setImageResource(R.drawable.circle_blue);
			
		}
		else
		{
			thumbnail.setImageResource(R.drawable.circle_grey);
		}
		sender.setText(p.getSender());
		
		date.setText(p.getDate());
		if(p.getMessage().length()>45)
		{
		message.setText(p.getMessage().substring(0, 45));
		}
		else
		{
			message.setText(p.getMessage());
		}
		return convertView;
	}

}