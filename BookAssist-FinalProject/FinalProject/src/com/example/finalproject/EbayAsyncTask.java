package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EbayAsyncTask extends AsyncTask<String, Void, ArrayList<BookPrice>> {

	TextView title,Price,Shipping;
	Context context;
	ProgressDialog pb;
	ImageView ebayimg;
	String URL="";

	public EbayAsyncTask(Context con,TextView titleval, TextView priceval,TextView shipval,ImageView img) {
		super();
		this.context=con;
		this.title=titleval;
		this.Price=priceval;
		this.Shipping=shipval;
		this.ebayimg=img;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ArrayList<BookPrice> doInBackground(String... params) {
		URL url = null;
		try {
			Log.d("URL Ebay", params[0]);
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
				//Log.d("Book", builder.toString());
				return UtilJSONEbay.AppJSONParser.parsedetail(builder
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
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		pb=new ProgressDialog(context);
	
		pb.setCancelable(false);
		pb.show();
	}
	@Override
	protected void onPostExecute(ArrayList<BookPrice> result) {
		super.onPostExecute(result);
		Log.d("Ebay Book List", result+ "");
		if(result!=null){
		for(BookPrice b:result)
		{
			if(b.getTitle()!=null)
			title.setText(b.getTitle());
			if(b.getPrice()!=null)
			Price.setText("Price: US $ "+b.getPrice());
			if(b.getShipping().equalsIgnoreCase("Free Shipping"))
			{
				Shipping.setText("Shipping Cost: Free Shipping");
			}
			else
			{
				Shipping.setText("Shipping Cost: US $ "+b.getShipping());	
			}
			URL=b.getURL();
			ebayimg.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
					context.startActivity(browserIntent);
					
				}
			});
		}		
		}
		pb.dismiss();
	}
}