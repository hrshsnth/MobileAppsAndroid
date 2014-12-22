package com.example.hw7;

import java.io.IOException;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
/**
 * HW7
 * AppUtilJSON.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class AppUtilJSON  {


	
	static public class AppJSONParser{
		static public ArrayList<Search> parsedetail(String in) throws IOException, JSONException
		{ 
			ArrayList<Search> Appdetail=new ArrayList<Search>();
			
				JSONObject root=new JSONObject(in);
				
				JSONArray entryelement=root.getJSONArray("photos");
				for(int i=0;i<entryelement.length();i++)
				{
					Search s=new Search();
					
					JSONObject entryobj=entryelement.getJSONObject(i);
					String title=entryobj.getString("image_url");
					//Log.d("ImageUrl", title+"");
					
					//al.add(title);
					s.setPhotoURL(title);
					//p.setPhotourl(title);
					JSONObject catobj=entryobj.getJSONObject("user");
				
					String category=catobj.getString("userpic_url");
					Log.d("category", category+"");
					s.setPhotoOwnerURL(category);
					 //p.setOurl(category);
					
					
					String name=entryobj.getString("name");
					s.setPhotoName(name);
					//p.setPhotoname(name);
					String oname=catobj.getString("fullname");
					s.setPhotoOwner(oname);
					
					Appdetail.add(s);
					
				}
				return Appdetail;
		}

	}
	
	
	
}
