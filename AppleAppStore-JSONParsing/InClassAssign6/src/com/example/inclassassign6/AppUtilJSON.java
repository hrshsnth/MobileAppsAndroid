package com.example.inclassassign6;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
/**
 * InClassAssign6
 *AppUtilJSON.java
 * @author HARISHSAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN(800823490)
 *
 */
public class AppUtilJSON  {


	
	static public class AppJSONParser{
		static public ArrayList<App> parsedetail(String in) throws IOException, JSONException
		{ 
			ArrayList<App> Appdetail=new ArrayList<App>();
			ArrayList<String> al=new ArrayList<String>();
		
				JSONObject root=new JSONObject(in);
				//System.out.println(in);
				JSONObject feedelement=(JSONObject) root.get("feed");
				JSONArray entryelement=feedelement.getJSONArray("entry");
				//System.out.println(entryelement);
				
				
				for(int i=0;i<entryelement.length();i++)
				{
					App a=new App();
					JSONObject entryobj=entryelement.getJSONObject(i);
					JSONObject newobj=entryobj.getJSONObject("title");
					String title=newobj.getString("label");
					Log.d("title", title+"");
					a.setTitle(title);
					
					JSONObject catobj=entryobj.getJSONObject("category");
					JSONObject attobj=catobj.getJSONObject("attributes");
					String category=attobj.getString("term");
					Log.d("category", category+"");
					a.setCategory(category);
					
					JSONObject priceobj=entryobj.getJSONObject("im:price");
					JSONObject priceobj1=priceobj.getJSONObject("attributes");
					String price=priceobj1.getString("amount");
					Log.d("price", price+"");
					a.setPrice(String.valueOf(Double.parseDouble(price)));
					
				
					
					JSONObject summaryobj=entryobj.getJSONObject("summary");
					String summary=summaryobj.getString("label");
					String finalvalue=summary.substring(0, 200);
					Log.d("summary", finalvalue+"");
					a.setSummary(finalvalue);
					
					
					JSONArray urlJSONArray = entryobj.getJSONArray("im:image");
					JSONObject urlJSONObj = urlJSONArray.getJSONObject(0);
					a.setImageURL(urlJSONObj.getString("label"));
					Log.d("ImageURL", urlJSONObj.getString("label")+"");
				Appdetail.add(a);
				
				}
			
			
				return Appdetail;
			
			
			
			
		}

	}
	
	
	
}
