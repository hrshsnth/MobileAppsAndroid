package com.example.hw5;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

/** HW #5
 * FileName : PhotoAsyncTask.java
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy Subramaniam(800833319)
 *  
 */
public class PhotoAsyncTask extends AsyncTask<String, Void, Drawable>{
	
	

	@Override
	protected Drawable doInBackground(String... params) {
		Drawable drawable = null;
		try {
			if(params[0]!=null){
			InputStream in = (InputStream) new URL(params[0]).getContent();
			drawable = Drawable.createFromStream(in, "Source Name");
			}
			else{
				return null;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drawable;
	}

	@Override
	protected void onPostExecute(Drawable result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
	}
}
