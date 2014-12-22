package com.example.inclass11;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class JogTripActivity extends Activity {

	List<ParseObject> list;
	JSONArray array;
	List<LatLng> al;
	 GoogleMap mMap;
	 Polyline line;
	 CameraUpdate cu;
	 LatLngBounds.Builder builder;
	 
	// List<LatLng> 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jog_trip);
		
		 mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			
		   mMap.setMyLocationEnabled(true);
		   
		
		String title= getIntent().getExtras().getString("title");
		al=new ArrayList<LatLng>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("JogTrip");
		query.whereEqualTo("title", title);
		
		try {
			list=query.find();
			
			for(ParseObject obj:list)
			{
			array=obj.getJSONArray("Points");
			Log.d("value", array+"");
			Log.d("size", String.valueOf(array.length()));
			}
			for(int i=0;i<array.length();i++)
			{
				//Location l=new Location();
				
				JSONObject entryobj=array.getJSONObject(i);
				LatLng p=new LatLng(entryobj.getDouble("latitude"), entryobj.getDouble("longitude"));
				
				al.add(p);
			 
			 
			}
			PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
			for (int z = 0; z < al.size(); z++) {
			    LatLng point = al.get(z);
			    options.add(point);
			}
			line = mMap.addPolyline(options);
			//mMap.moveCamera(CameraUpdateFactory.newLatLng(al.get(0)));
	        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
		//	mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(al.get(0), 13));
			mMap.addMarker(new MarkerOptions().position(al.get(0)));	
	        mMap.addMarker(new MarkerOptions().position(al.get(al.size()-1)));
	       builder=new LatLngBounds.Builder();
	       
			for(LatLng x:al)
			{
				builder.include(x);
			}
			
			
			mMap.setOnMapLoadedCallback(new OnMapLoadedCallback() {
				
				@Override
				public void onMapLoaded() {
					LatLngBounds bounds=builder.build();
					 cu=CameraUpdateFactory.newLatLngBounds(bounds,0);
						mMap.animateCamera(cu);
					
				}
			});
		
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		Intent i=new Intent(JogTripActivity.this,MainActivity.class);
		startActivity(i);
		
	}
	
}
