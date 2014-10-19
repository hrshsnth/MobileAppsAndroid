package com.example.hw5;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/** HW #5
 * FileName : NewsUtils.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class NewsUtils {
	static public class NewsXMLPullParser extends DefaultHandler {
		
		static final String smallThumb = "media:thumbnail";
		static final String largeThumb = "media:thumbnail";
		
		static public LinkedList<News> parseData(InputStream in)
				throws XmlPullParserException, IOException {
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(in, "UTF-8");
			LinkedList<News> newsList = new LinkedList<News>();
			Log.d("newsList-size", String.valueOf(newsList.size()));

			News news = null;
			boolean item = false;
			int itemCount =1;
			int event = parser.getEventType();
			String text="";
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {
				case XmlPullParser.START_TAG:
					if ((itemCount<=25) && parser.getName().equals("item")) {
						news = new News();
						item=true;
					//	Log.d("daya:NewsUtils-count", String.valueOf(itemCount));

					} else if (item && parser.getName().equals("title")) {
						String value=parser.nextText().trim();
						//Log.d("daya:NewsUtils-title", value);
						news.setTitle(value);
					} else if (item && parser.getName().equals("description")) {
						String value=parser.nextText().trim();
						//Log.d("daya:NewsUtils-descr", value);
						news.setDescription(value);
					} else if (item && parser.getName().equals("pubDate")) {
						String value=parser.nextText().trim();
						//Log.d("daya:NewsUtils-pubdate", value);
						news.setPubDate(value);
					} else if (item && parser.getName().equals(smallThumb)) {
						if(parser.getAttributeValue(null, "width").equals("66")){
	                        news.setThumbnailSmall(parser.getAttributeValue(null, "url").trim());
	                      //  Log.d("small thumbnail", parser.getAttributeValue(null, "url").trim());
	                    } else {
	                    	if(parser.getAttributeValue(null, "width").equals("144")){
								 news.setThumbnailLarge(parser.getAttributeValue(null, "url").trim());
			                //      Log.d("large thumbnail", parser.getAttributeValue(null, "url").trim());
			                    } 
	                    }
					} 
					break;
			
				case XmlPullParser.END_TAG:
					if((itemCount<=25)&& parser.getName().equals("item")) {
						item =false;
						itemCount++;

						//System.out.println(news.toString());
						newsList.add(news);
						//Log.d("newsList-sizeend", String.valueOf(newsList.size()));

						news=null;
					}
				default:
					break;
				}
				event = parser.next();
			}
			return newsList;
		}
	}
}

