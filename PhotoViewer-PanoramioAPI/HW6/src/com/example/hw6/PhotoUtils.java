package com.example.hw6;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * HW #6 FileName : PhotoUtils.java
 * 
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy
 *         Subramaniam(800833319)
 * 
 */
public class PhotoUtils {
	static public class PhotoJSONParser {
		static ArrayList<Photo> parseApp(String in) throws JSONException {
			ArrayList<Photo> photolist = new ArrayList<Photo>();
			Photo photo;
			JSONObject root = new JSONObject(in);
			JSONArray appArray = root.getJSONArray("photos");

			for (int i = 0; i < appArray.length(); i++) {
				JSONObject appJSONObject = appArray.getJSONObject(i);

				photo = new Photo();
				photo.setTitle(appJSONObject.getString("photo_title"));
				photo.setPubdate(appJSONObject.getString("upload_date"));
				photo.setImage_url(appJSONObject.getString("photo_file_url"));
				photo.setOwner_name(appJSONObject.getString("owner_name"));

				photolist.add(photo);
				photo = null;
			}
			return photolist;
		}
	}
}
