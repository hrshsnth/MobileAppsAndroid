package com.example.finalproject;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AppUtilJSON  {
	
	static public class AppJSONParser{
		static public String parsedetail(String in) throws IOException, JSONException
		{ 
			String output = null;
			
				String iFrameSrc = null;
				
				JSONObject root=new JSONObject(in);
				String html=root.getString("reviews_widget");
				//Log.d("From JSON", html);
				
				Document doc=Jsoup.parse(html);
				//Element iFrame=doc.getElementById("the_iframe");
				Elements images = doc.select("iframe[src~=(?i)\\.]");
				for(Element e:images)
				{
					//Log.d("iFrame Source",e.attr("src") );
					iFrameSrc=e.attr("src");
				}
				output=iFrameSrc.replace("DEVELOPER_ID", "WoTRDvuZFiEl2EQ2XkWPtQ");

			
				
			return output;
		}
	}
}
