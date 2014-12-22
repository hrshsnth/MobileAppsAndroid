package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookGoogleAsync extends AsyncTask<String, Void, ArrayList<Book>> {

	TextView title,author,isbn,desc,ebayTitle,ebayPrice,ebayShipping,aztitle,azprice;
	ImageView bookimageURL,ebayimg,azimg;
	Context context;
	ProgressDialog pb;
	String SendTitle;
	String toggle;
	Button AddtoCatalog,AddtoWishList;
	AlertDialog dialog;
	AlertDialog.Builder builder;
	String[] genres={"Education","Novel","Comics","Music","Sports","Health"};
	ArrayList<Book> forAddtoCat=new ArrayList<Book>();
	Activity activity;

	public BookGoogleAsync(String type,Context con,TextView titleval, TextView authorval
			,TextView ISBNval,TextView descval,ImageView bookimageval,Button AddtoCat,Button AddtoWish) {
		super();
		this.context=con;
		this.title=titleval;
		this.author=authorval;
		this.isbn=ISBNval;
		this.desc=descval;
		this.bookimageURL=bookimageval;
		this.AddtoCatalog=AddtoCat;
		this.AddtoWishList=AddtoWish;
		this.toggle=type;
		// TODO Auto-generated constructor stub
	}
	public BookGoogleAsync(String type,Context con,TextView ebaytitle,TextView price,TextView ship,ImageView eimg,
			TextView atitle, TextView aprice,ImageView aimg)
	{
		this.context=con;
		this.ebayTitle=ebaytitle;
		this.ebayPrice=price;
		this.ebayShipping=ship;
		this.aztitle=atitle;
		this.azprice=aprice;
		this.ebayimg=eimg;
		this.azimg=aimg;
		this.toggle=type;
	}
	public BookGoogleAsync(String type,Activity activity,Context con)
	{
		this.toggle=type;
		this.activity=activity;
		this.context=con;
		
	}



	@Override
	protected ArrayList<Book> doInBackground(String... params) {
		URL url = null;
		try {
			Log.d("URL Google Books", params[0]);
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
				Log.d("Toggle Value", toggle);
				return UtilJSONGoogle.AppJSONParser.parsedetail(builder
						.toString(),toggle);
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
	protected void onPostExecute(ArrayList<Book> result) {
		super.onPostExecute(result);
		Log.d("Google Book List", result+ "");
		if(toggle.equalsIgnoreCase("BookSpecs"))
		{
			for(Book b:result)
			{
				Log.d("Title",b.getBooktitle());
				title.setText(b.getBooktitle());
				
				author.setText(b.getBookauthor());
				isbn.setText(b.getBookisbn());
				desc.setText(b.getBookdesc());
				if(b.getBookimageurl().equals(""))
				{
					bookimageURL.setImageResource(R.drawable.nobookicon);
				}
				else
				{
					Picasso.with(context).load(b.getBookimageurl()).into(bookimageURL);
				}
				
			}	
			forAddtoCat.addAll(result);
			builder=new AlertDialog.Builder(context);
			
			AddtoCatalog.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					builder.setTitle("Add to Category").setItems(genres, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {						
							ParseUser user= ParseUser.getCurrentUser();
							ParseObject obj=new ParseObject("BookCatalogue");
							obj.put("User", user.getUsername());
							obj.put("BookTitle",forAddtoCat.get(0).getBooktitle());
							obj.put("BookAuthor",forAddtoCat.get(0).getBookauthor());
							obj.put("BookISBN",forAddtoCat.get(0).getBookisbn());
							obj.put("BookDescription",forAddtoCat.get(0).getBookdesc());
							obj.put("BookIMGURL",forAddtoCat.get(0).getBookimageurl());
							obj.put("BookCategory", genres[which]);
							obj.saveInBackground();
							AddtoCatalog.setVisibility(View.GONE);
							 Toast.makeText(context, "Book Added to My Book Shelf", Toast.LENGTH_SHORT).show();
							 
						}
					});
					
					dialog=builder.create();
					dialog.show();
				}
			});
		 AddtoWishList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParseUser user= ParseUser.getCurrentUser();
				ParseObject obj=new ParseObject("BookWishList");
				obj.put("User", user.getUsername());
				obj.put("BookTitle",forAddtoCat.get(0).getBooktitle());
				obj.put("BookAuthor",forAddtoCat.get(0).getBookauthor());
				obj.put("BookISBN",forAddtoCat.get(0).getBookisbn());
				obj.put("BookDescription",forAddtoCat.get(0).getBookdesc());
				obj.put("BookIMGURL",forAddtoCat.get(0).getBookimageurl());
				obj.saveInBackground();
				//AddtoWishList.setVisibility(View.GONE);
				 Toast.makeText(context, "Book Added to Wish List", Toast.LENGTH_SHORT).show();
			}
		});
		}
		else if(toggle.equalsIgnoreCase("List"))
		{
			Log.d("Result in List", result.toString());
			((BookListActivity) activity).setupData(result);
		}
		else
		{
			String gettitle=result.get(0).getBooktitle().replaceAll(" ","%20");
			new EbayAsyncTask(context,ebayTitle,ebayPrice, ebayShipping,ebayimg).execute("http://open.api.ebay.com/shopping?appid=HARISHSA-bb9c-4700-ba32-e942b8e5d3ab&version=517&siteid=0&callname=FindItems&QueryKeywords="+gettitle+"&responseencoding=JSON");

			
			  SignedRequestsHelper obj = null;
			
				try {
					obj = new SignedRequestsHelper();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  HashMap<String, String> map = new HashMap<String, String>();
	          map.put("Service","AWSECommerceService");
	          map.put("Operation","ItemSearch");
	          map.put("Condition","New");
	          map.put("Availability","Available");
	          map.put("SearchIndex","All");
	          map.put("Keywords",result.get(0).getBooktitle());
	          map.put("AssociateTag", "twitter0041-20");
	          map.put("ResponseGroup", "OfferFull, ItemAttributes");
	          //map.put("MerchantId", "Amazon");
	          String finalval = obj.sign(map);
	          Log.d("Amazon URL", finalval);
	          new PullParserAsync(context, aztitle, azprice,azimg).execute(finalval);
	         // System.out.println(result);		
		}		
		pb.dismiss();
	}
}