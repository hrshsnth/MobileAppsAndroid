package com.example.inclassassign4;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.*;

/**
 * InClassAssginment4 MainActivity.java
 * 
 * @author HARISHSAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN(800823490)
 * 
 */
public class MainActivity extends Activity implements View.OnClickListener {

	ImageView photo, next, prev;
	ProgressDialog pb;
	static int intial = 0;
	int cacheSize = 1024 * 30;
	LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(cacheSize) {

		@Override
		protected void entryRemoved(boolean evicted, String key,
				Bitmap oldValue, Bitmap newValue) {
			// TODO Auto-generated method stub
			super.entryRemoved(evicted, key, oldValue, newValue);
		}

		@Override
		protected int sizeOf(String key, Bitmap value) {
			// TODO Auto-generated method stub
			return value.getByteCount() / 1024;
		}

	};

	public void addBitmaptoCache(String key, Bitmap image) {
		 
			synchronized (cache) {
				if (getBitmapfromCache(key) == null) {
			         cache.put(key, image);
			     
			   }
		}
			
		
	}

	public Bitmap getBitmapfromCache(String key) {
		return cache.get(key);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		photo = (ImageView) findViewById(R.id.imageView1);
		photo.setImageBitmap(null);
		next = (ImageView) findViewById(R.id.imageView3);
		prev = (ImageView) findViewById(R.id.imageView2);

		next.setOnClickListener(this);
		prev.setOnClickListener(this);
		pb = new ProgressDialog(this);
		if (isConnected()) {

			new GetImage()
					.execute("http://dev.theappsdr.com/lectures/inclass_http/index.php?pid=0");

		} else {
			Toast.makeText(MainActivity.this, "Not Connected",
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean isConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo n = cm.getActiveNetworkInfo();
		if (n != null && n.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	class GetImage extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected void onPreExecute() {

			pb.setMessage("Loading Image");

			pb.setCancelable(false);
			pb.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				photo.setImageBitmap(result);
			} else {
				Log.d("Image Not Found", "Image Not Found");
			}
			pb.dismiss();

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			pb.setProgress(values[0]);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			URL url;
			int i = 0;
			Bitmap bf = null;
			try {
				url = new URL(params[0]);
				Log.d("Image URL", params[0]);

				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setRequestMethod("GET");
				bf = BitmapFactory.decodeStream(con.getInputStream());
				addBitmaptoCache(String.valueOf(intial), bf);

				// return bf;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			publishProgress(i++);

			return bf;
		}

	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.imageView3) {

			if (intial == 19) {
				intial = 0;
			} else {
				intial = intial + 1;
			}

			if (isConnected()) {
				Bitmap image = getBitmapfromCache(String.valueOf(intial));
				if (image != null) {
					photo.setImageBitmap(image);
				} else {
					new GetImage()
							.execute("http://dev.theappsdr.com/lectures/inclass_http/index.php?pid="
									+ intial);
				}
			} else {
				Bitmap image = getBitmapfromCache(String.valueOf(intial));
				if (image != null) {
					photo.setImageBitmap(image);
				} else {
					photo.setImageBitmap(null);
				}
				Toast.makeText(MainActivity.this, "Not Connected",
						Toast.LENGTH_LONG).show();
			}
			Log.d("Next Inital", "" + intial);

		} else if (v.getId() == R.id.imageView2) {

			if (intial == 0) {
				intial = 19;
			} else {
				intial = intial - 1;
			}
			Log.d("Previous Inital", "" + intial);
			if (isConnected()) {

				Bitmap image = getBitmapfromCache(String.valueOf(intial));
				if (image != null) {
					photo.setImageBitmap(image);
				} else {
					new GetImage()
							.execute("http://dev.theappsdr.com/lectures/inclass_http/index.php?pid="
									+ intial);
				}

			} else {

				Bitmap image = getBitmapfromCache(String.valueOf(intial));
				if (image != null) {
					photo.setImageBitmap(image);
				} else {
					photo.setImageBitmap(null);
				}
				Toast.makeText(MainActivity.this, "Not Connected",
						Toast.LENGTH_LONG).show();
			}

		}

	}
}
