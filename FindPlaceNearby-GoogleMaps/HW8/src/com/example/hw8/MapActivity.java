package com.example.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * HW8
 * MapActivity.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class MapActivity extends Activity {

	 GoogleMap mMap;
	ArrayList<Places> placeslist=new ArrayList<Places>();
	List<LatLng> al;
	LatLngBounds.Builder builder;
	CameraUpdate cu;
	String iconurl,placename,placetype;
	ViewGroup parent;	
	Marker markval;
	HashMap<Marker, Places> markerdetails=new HashMap<Marker, Places>();
	RuntimeExceptionDao<Places, String> runtimedao;
	DatabaseHelper dbHelp;
	ProgressDialog pb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		 
		dbHelp=OpenHelperManager.getHelper(MapActivity.this, DatabaseHelper.class);
		try {
			runtimedao=dbHelp.getRuntimeDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();		 
		  mMap.setMyLocationEnabled(true);
		   al=new ArrayList<LatLng>();
		   pb=new ProgressDialog(this);
		   pb.setMessage("Loading Map");
		   pb.setCancelable(false);
		   pb.show();
		String place_type=getIntent().getExtras().getString("PlaceType");
		String radius=getIntent().getExtras().getString("Radius");
		Double lat=(Double)getIntent().getExtras().get("Lat");
		Double lng=(Double)getIntent().getExtras().get("Long");
		Log.d("place_type", place_type);
		Log.d("radius", place_type);
		Log.d("latLong", lat+""+lng);
		String URL="https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
				"location="+lat+","+lng+"&radius="+radius+"&types="+place_type+"&key=AIzaSyD7Y8su1pKGhRVcI2cUJ3jhzlesF10COkA";
		Log.d("URL", URL);		
		new JSONAsyncTask().execute(URL);		
			
	}	
	
	public class JSONAsyncTask extends AsyncTask<String, Void, ArrayList<Places>> {

		@Override
		protected ArrayList<Places> doInBackground(String... params) {
			URL url = null;
			try {
				url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				int responseCode = con.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(con.getInputStream()));
					StringBuilder builder = new StringBuilder();
					String line = reader.readLine();

					while (line != null) {
						builder.append(line);
						line = reader.readLine();
					}
					//Log.d("JSON", builder.toString());
					return AppUtilJSON.AppJSONParser.parsedetail(builder
							.toString());
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Places> result) {
			super.onPostExecute(result);
			placeslist=result;
			pb.dismiss();
			for(Places p: result)
			{				
				LatLng val=new LatLng(p.getLatitude(), p.getLongitude());				
				Marker m=mMap.addMarker(new MarkerOptions().position(val));
					markerdetails.put(m,p);			
					
					//m.showInfoWindow();
				al.add(val);
			}
			mMap.setInfoWindowAdapter(new markerwindow(markerdetails));
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
			
			mMap.setOnMarkerClickListener(new plotmarker(markerdetails,false));
			//plotmarkers(markerdetails);
		}
	}
	public class plotmarker implements GoogleMap.OnMarkerClickListener{
		
		HashMap<Marker, Places> instance;
		boolean favorite;
		public plotmarker(HashMap<Marker, Places> value,boolean test)
		{
			this.instance=value;
			this.favorite=test;
		}
		
		@Override
		public boolean onMarkerClick(Marker mark) {
			
			if(favorite)
			{
				mark.showInfoWindow();	
			}
			else
			{
				mark.showInfoWindow();
				Places place=instance.get(mark);
				if(runtimedao.isTableExists())
				{
					List<Places> temp=runtimedao.queryForAll();
					boolean check=checkexists(temp, place.getLatitude(),place.getLongitude(),place.getName());
					if(check)
					{
						Toast.makeText(MapActivity.this, "Already Exists in DB", Toast.LENGTH_LONG).show();
					}
					else
					{
						runtimedao.create(new Places(place.getName(),
								place.getLatitude(),place.getLongitude(),
								place.isOpennow(),place.getType(),
								place.getIconURL()							
								));			
						Toast.makeText(MapActivity.this, "Saved to DB", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					runtimedao.create(new Places(place.getName(),
							place.getLatitude(),place.getLongitude(),
							place.isOpennow(),place.getType(),
							place.getIconURL()							
							));
					Toast.makeText(MapActivity.this, "Saved to DB", Toast.LENGTH_SHORT).show();
				}
			}
				return true;	
		}
		
	}

	public class markerwindow implements GoogleMap.InfoWindowAdapter
	{
		HashMap<Marker, Places> values;
		public markerwindow(HashMap<Marker, Places> received)
		{
			this.values=received;
		}
				
		@Override
		public View getInfoContents(Marker mark) {
			View view=(View)getLayoutInflater().inflate(R.layout.marker, null);
			Places place=values.get(mark);
			ImageView icon=(ImageView)view.findViewById(R.id.imageView1);
			TextView name=(TextView)view.findViewById(R.id.textView1);
			TextView type=(TextView)view.findViewById(R.id.textView2);
			if(place.getIconURL()==null)
			{
				icon.setImageResource(R.drawable.noimageicon);
			}
			else
			{
				Picasso.with(MapActivity.this).load(place.getIconURL()).into(icon);	
			}	
			if(place.isOpennow())
			{
				name.setTextColor(Color.parseColor("#FF0000"));
				type.setTextColor(Color.parseColor("#FF0000"));					
			}
			else
			{
				name.setTextColor(Color.parseColor("#808080"));
				type.setTextColor(Color.parseColor("#808080"));				
			}
			name.setText(place.getName());
			type.setText(place.getType());				
			return view;			
		}

		@Override
		public View getInfoWindow(Marker mark) {
			return null;
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.Favorites) {
			mMap.clear();
			pb.show();
			List<Places> check=runtimedao.queryForAll();
			HashMap<Marker, Places> favoritemarker=new HashMap<Marker, Places>();
			List<LatLng> latlng=new ArrayList<LatLng>();
			if(!check.isEmpty())
			{
				for(Places p: check)
				{
					LatLng val=new LatLng(p.getLatitude(), p.getLongitude());
					
					Marker m=mMap.addMarker(new MarkerOptions().position(val));
					favoritemarker.put(m,p);			
						
						//m.showInfoWindow();
						latlng.add(val);
				}	
				pb.dismiss();
				mMap.setInfoWindowAdapter(new markerwindow(favoritemarker));
				builder=new LatLngBounds.Builder();			       
				for(LatLng x:latlng)
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
				mMap.setOnMarkerClickListener(new plotmarker(favoritemarker,true));
			}
			else
			{
				pb.dismiss();
				Toast.makeText(MapActivity.this, "No Favorite Places found", Toast.LENGTH_SHORT).show();
			}
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean checkexists(List<Places> check,Double latt, Double longg,String name)
	{
		boolean validate=false;
		for(Places p:check)
		{
			if(p.getLatitude().equals(latt) && p.getLongitude().equals(longg) && p.getName().equalsIgnoreCase(name))
			{				
			validate=true;
			}			
		}
		return validate;
	}
	
}
