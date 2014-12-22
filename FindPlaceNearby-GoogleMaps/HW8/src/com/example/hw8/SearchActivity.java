package com.example.hw8;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
/**
 * HW8
 * SearchActivity.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class SearchActivity extends Activity {

	TextView placetype,radiusval;
	SeekBar radius;
	ImageView select;
	AlertDialog.Builder builder;
	AlertDialog dialog;
	String[] types={"airport","atm","bank","cafe","parking","food","school"};
	Button search;
	LocationManager locMan;
	LocationListener locaListner;
	Double Lat,Long;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
     
        locMan=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
	       
        placetype=(TextView)findViewById(R.id.textView2);
        radius=(SeekBar)findViewById(R.id.seekBar1);
        select=(ImageView)findViewById(R.id.imageView1);
        search=(Button)findViewById(R.id.button1);
        builder=new AlertDialog.Builder(this);
        radiusval=(TextView)findViewById(R.id.textView4);
      
        locaListner=new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				Lat=location.getLatitude();
				Long=location.getLongitude();
				Log.d("LATLONGGG", Lat+"  "+Long);
			}
		};

		locMan.requestLocationUpdates(locMan.GPS_PROVIDER, 0, 100, locaListner);
        
        select.setImageResource(R.drawable.history);
        radius.setMax(50000);
        radiusval.setText("0");
       // types=new String[7];
		
       radius.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			radiusval.setText(progress+"");
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
	});
       
        
        select.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				builder.setTitle("Choose Type").setItems(types,new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						placetype.setText(types[which]);
						
					}
				});
				dialog=builder.create();
				dialog.show();
			}
		});
        
        
        search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(placetype.getText()!=null && !radiusval.getText().equals("0"))
				{
					if(Lat==null && Long ==null)
					{
						Toast.makeText(SearchActivity.this, "GPS Not Enabled ", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Intent i=new Intent(SearchActivity.this,MapActivity.class);
						i.putExtra("PlaceType", placetype.getText());
						i.putExtra("Radius", radiusval.getText());
						i.putExtra("Lat", Lat);
						i.putExtra("Long", Long);
						startActivity(i);
					}
						
				}
				else
				{
					Toast.makeText(SearchActivity.this, "Select Type and Give Radius", Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(!locMan.isProviderEnabled(locMan.GPS_PROVIDER))
		{
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setTitle("Enable GPS")
			.setMessage("Click YES to enable GPS")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent i=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(i);
				}
			}).setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
					finish();
				}
			}); 
			AlertDialog alert=builder.create();
			alert.show();
		}
		
	}
    public class Geotask extends AsyncTask<String, Void, List<Address>>
    {
    	Context mContext;
    	public Geotask(Context temp)
    	{
    		this.mContext=temp;
    	}
		@Override
		protected List<Address> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Address> addresslist=null;
			 
			Geocoder geo=new Geocoder(mContext);
			try {
				addresslist=geo.getFromLocationName(params[0], 10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return addresslist;
		}
		@Override
		protected void onPostExecute(List<Address> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.d("final result", result.toString());
		}
    	
    }
}
