package com.example.finalproject;

import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class BookUtilParser {
	static public class BookXMLPullParser extends DefaultHandler {
		
		static public BookPrice parseData(InputStream in)
				throws XmlPullParserException, IOException {
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(in, "UTF-8");
			int itemCount =1;
			boolean item = false;
			boolean Amount = false;
			
			BookPrice Book = null;
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {
				case XmlPullParser.START_TAG:
					if((itemCount==1)&& parser.getName().equals("Item"))
					{
						Book = new BookPrice();
						item=true;
					}
					else if (item && parser.getName().equals("Title")) {
						String value=parser.nextText().trim();
						Log.d("Amazon-title", value);
						Book.setTitle(value);
					} 
					else if (item && parser.getName().equals("LowestNewPrice")) {
						Log.d("LowestNewPrice", "LowestNewPrice");
						Amount=true;
						
					
					} 
					else if(item && parser.getName().equals("FormattedPrice"))
					{
						if(Amount)
						{
							String price=parser.nextText().trim();
							Log.d("Amazon Price", price);
							Book.setPrice(price);	
							Amount=false;
						}
						
					}
					else if(item && parser.getName().equals("DetailPageURL"))
					{
						String bookurl=parser.nextText().trim();
						Book.setURL(bookurl);
					}
					
					break;
			
				case XmlPullParser.END_TAG:
					
					if((itemCount==1)&& parser.getName().equals("Item"))
					{
						item=false;
						Amount=false;
						itemCount++;
					}
					break;
					
				default:
					break;
				}
				event = parser.next();
			}
		//	Log.d("Object Book", Book.getTitle()+" "+Book.getPrice());
			return Book;
		}
	}
}

