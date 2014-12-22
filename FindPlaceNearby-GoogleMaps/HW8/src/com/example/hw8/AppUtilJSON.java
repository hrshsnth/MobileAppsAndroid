package com.example.hw8;

import java.io.IOException;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * HW8
 * AppUtilJSON.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class AppUtilJSON  {
	
	static public class AppJSONParser{
		static public ArrayList<Places> parsedetail(String in) throws IOException, JSONException
		{ 
			ArrayList<Places> places=new ArrayList<Places>();
			
				JSONObject root=new JSONObject(in);
				
				JSONArray entryelement=root.getJSONArray("results");
				
				for(int i=0;i<entryelement.length();i++)
				{
					Places p=new Places();
					
					JSONObject entryobj=entryelement.getJSONObject(i);
					String name=entryobj.getString("name");
					p.setName(name);
					
					JSONObject geometry=entryobj.getJSONObject("geometry");
					JSONObject location=geometry.getJSONObject("location");
					Double lat=location.getDouble("lat");
					Double longit=location.getDouble("lng");
					p.setLatitude(lat);
					p.setLongitude(longit);
					
					String icon=entryobj.getString("icon");
					p.setIconURL(icon);
						if(entryobj.isNull("opening_hours"))
						{	
							p.setOpennow(false);
						}
						else
						{
							JSONObject open=entryobj.getJSONObject("opening_hours");
							boolean opennow=open.getBoolean("open_now");
							p.setOpennow(opennow);
						}
				
						
					JSONArray types=entryobj.getJSONArray("types");
					String type=(String)types.get(0);
					p.setType(type);
					
					places.add(p);
				
				}
				return places;
		}
	}
}
