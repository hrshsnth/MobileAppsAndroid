package com.example.inclassassign5;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Xml;
/**
 * InClassAssign5
 * WeatherUtilSAX.java
 * @author HARISHSAINATH GANAPATHY (800833319) DAYABARAN GANGATHARAN(800823490)
 *
 */
public class WeatherUtilSAX  {

	static public class WeatherSXParser extends DefaultHandler{
	
		
		Weather w,weather;
		//ArrayList<Weather> al;
		static public Weather parsedetail(InputStream in) throws IOException, SAXException
		{ 
			WeatherSXParser parser=new WeatherSXParser();
			Xml.parse(in, Xml.Encoding.UTF_8, parser);
			return parser.getW();
			
		}
		public Weather getW() {
			return w;
		}

		@Override
		public void startDocument() throws SAXException {
			
			super.startDocument();
			w=new Weather();
		}
 	
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			
			super.startElement(uri, localName, qName, attributes);
			//w=new Weather();
		//al=new ArrayList<Weather>();
			if(localName.equals("temperature"))				
			{
				w.setTemperature(attributes.getValue("value").trim()+" "+attributes.getValue("unit"));
				
			}
			else if(localName.equals("humidity"))
			{
				w.setHumidity(attributes.getValue("value").trim()+" "+attributes.getValue("unit"));
			}
			else if(localName.equals("pressure"))
			{
				w.setPressure(attributes.getValue("value").trim()+" "+attributes.getValue("unit"));
			}
			else if(localName.equals("clouds"))
			{
				w.setClouds(attributes.getValue("name").trim());
			}
			else if(localName.equals("speed"))
			{
				w.setWindspeed(attributes.getValue("name"));				
			}
			else if(localName.equals("direction"))
			{
				w.setWinddirection(attributes.getValue("name"));				
			}
			else if(localName.equals("precipitation"))
			{
				w.setPrecipitation(attributes.getValue("mode"));
			}
			/*al.add(w);
			for(Weather w:al)
			{
				System.out.println(w.getTemperature());
				System.out.println(w.getPressure());
				System.out.println(w.getClouds());
			}*/
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			
			super.endElement(uri, localName, qName);
		
		}

		
	}
}
