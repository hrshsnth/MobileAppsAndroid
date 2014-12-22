package com.example.finalproject;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.squareup.picasso.Picasso;

public class MyFragment extends Fragment {
	
	ArrayList<Book> books;
	int pos;
	public static Fragment newInstance(MainMenuActivity context, int pos, 
			float scale,ArrayList<Book> listBook, boolean IsBlured)
	{
		
		Bundle b = new Bundle();
		b.putInt("pos", pos);
		b.putFloat("scale", scale);
		b.putBoolean("IsBlured", IsBlured);
		b.putSerializable("list", listBook);

		return Fragment.instantiate(context, MyFragment.class.getName(), b);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		
		LinearLayout l = (LinearLayout)
				inflater.inflate(R.layout.mf, container, false);
		l.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.d("Hi3", "there");
			}
		});
		pos = this.getArguments().getInt("pos");
			books=(ArrayList<Book>) this.getArguments().getSerializable("list");
			Log.d("Fragment", books.toString());
		TextView tv = (TextView) l.findViewById(R.id.viewID);
		ImageView iv = (ImageView) l.findViewById(R.id.content);
		tv.setText(books.get(pos).getBooktitle());
		Picasso.with(getActivity()).load(books.get(pos).getBookimageurl()).into(iv);
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Log.d("hi", books.get(pos).getProduct_url());
				Intent i=new Intent(getActivity(),BookListActivity.class);
				i.putExtra("ISBN", "");
				i.putExtra("Title", books.get(pos).getBooktitle());
				startActivity(i);
			}
		});
		MyLinearLayout root = (MyLinearLayout) l.findViewById(R.id.root);
		float scale = this.getArguments().getFloat("scale");
		root.setScaleBoth(scale);
		boolean isBlured=this.getArguments().getBoolean("IsBlured");
		if(isBlured)
		{
			ViewHelper.setAlpha(root,MyPagerAdapter.getMinAlpha());
			ViewHelper.setRotationY(root, MyPagerAdapter.getMinDegree());
		}
		return l;
	}
}
