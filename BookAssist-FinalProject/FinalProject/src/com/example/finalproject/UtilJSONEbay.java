package com.example.finalproject;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UtilJSONEbay  {
	
	static public class AppJSONParser{
		static public ArrayList<BookPrice> parsedetail(String in) throws IOException, JSONException
		{ 
			ArrayList<BookPrice> book=new ArrayList<BookPrice>();
			BookPrice b=new BookPrice();
			
				JSONObject root=new JSONObject(in);
				JSONArray items=root.getJSONArray("Item");
				
				JSONObject first=items.getJSONObject(0);
				String title=first.getString("Title");
				b.setTitle(title);
				//Log.d("Title ebay", title);
				JSONObject priceblock=first.getJSONObject("ConvertedCurrentPrice");
				String price=priceblock.getString("Value");
				b.setPrice(price);
				//Log.d("price",price );
				JSONObject shipblock=first.getJSONObject("ShippingCostSummary");
				JSONObject shipblock2=shipblock.getJSONObject("ShippingServiceCost");
				String shipping=shipblock2.getString("Value");
				
				if(Double.parseDouble(shipping)==0)
				{
					shipping="Free Shipping";
				}
				else
				{
					shipping=shipblock2.getString("Value");
				}
				b.setShipping(shipping);		
				
				String actualurl=first.getString("ViewItemURLForNaturalSearch");
				b.setURL(actualurl);
				//Log.d("shipping",shipping);
				book.add(b);
				
				return book;
		}
	}
}
