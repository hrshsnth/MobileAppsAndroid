package com.example.hw2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Homework 2
 * HW2
 * @author DAYABARAN GANGATHARAN(800823490) and HARISHSAINATH GANAPATHY(800833319)
 * 
 */


public class MainActivity extends Activity implements OnClickListener {

	TextView price;
	String totalcost;
	Double totalprice;
	LinearLayout toprow;
	LinearLayout bottomrow;
	ProgressBar capacity;
	AlertDialog.Builder builder;

	private ArrayList<ImageView> mViews = new ArrayList<ImageView>();
	List<View> mV = new ArrayList<View>();

	/**
	 * Dialog Box Creation and Component Initialization
 	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Map<String, Double> myMap = new HashMap<String, Double>() {
			{
				put("apple", 1.25);
				put("banana", 0.7);
				put("cake", 10.5);
				put("cheese", 4.55);
				put("cookie", 3.99);
				put("milk", 2.95);
				put("toast", 1.99);
			}
		};
		price = (TextView) findViewById(R.id.textView2);
		toprow = (LinearLayout) findViewById(R.id.toprow);
		bottomrow = (LinearLayout) findViewById(R.id.secondrow);
		capacity = (ProgressBar) findViewById(R.id.progressBar1);
		capacity.setMax(10);
		capacity.setProgress(0);
		builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.alert_dialog_title).setItems(R.array.items,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							addCartItem(R.drawable.apple, myMap.get("apple"));
							break;
						case 1:
							addCartItem(R.drawable.banana, myMap.get("banana"));
							break;
						case 2:
							addCartItem(R.drawable.cake, myMap.get("cake"));
							break;
						case 3:
							addCartItem(R.drawable.cheese, myMap.get("cheese"));
							break;
						case 4:
							addCartItem(R.drawable.cookie, myMap.get("cookie"));
							break;
						case 5:
							addCartItem(R.drawable.milk, myMap.get("milk"));
							break;
						case 6:
							addCartItem(R.drawable.toast, myMap.get("toast"));
							break;
						default:
							break;
						}

					}
				});

		Button btnAdd = (Button) findViewById(R.id.button1);
		btnAdd.setOnClickListener(this);
		Button btnClear = (Button) findViewById(R.id.button2);
		btnClear.setOnClickListener(this);

	}

/**
 *Add Items to Cart 
 */
 private void addCartItem(int drawable, Double cost) {
		
		if (capacity.getProgress() >= 10) {
			Toast.makeText(this, "Maximum Cart capacity !", Toast.LENGTH_LONG).show();
		} else {
			ImageView imageView = new ImageView(this);
			imageView.setTag(cost);
			imageView.setId(drawable);
			imageView.setOnClickListener((OnClickListener) MainActivity.this);

			imageView.setLayoutParams(new LayoutParams((int) getResources()
					.getDimension(R.dimen.width), (int) getResources()
					.getDimension(R.dimen.height)));
			imageView.setImageResource(drawable);
			capacity.setProgress(capacity.getProgress() + 1);
			mViews.add(imageView);
			displayList(mViews);
			totalcost = price.getText().toString().substring(1);
			totalprice = Double.valueOf(totalcost) + cost;
			price.setText("$" + String.format("%.2f", totalprice));
		}
	}

/**
 * Display ImageViews to the Layout
 */
	protected void displayList(List<ImageView> list) {
		toprow.removeAllViews();
		bottomrow.removeAllViews();
		for (ImageView imageView : list) {
			if (toprow.getChildCount() < 5) {
				toprow.addView(imageView);
			} else if (bottomrow.getChildCount() < 5) {
				bottomrow.addView(imageView);
			}
		}
	}

/**
 * Handling Buttons in the Main Screen
 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			builder.show();
			break;
		case R.id.button2:
			toprow.removeAllViews();
			bottomrow.removeAllViews();
			price.setText(getResources().getText(R.string.valcost));
			capacity.setProgress(0);
			mViews.clear();
			break;
		default:
			deleteItem(v);
			break;
		}
	}

/**
 * Delete Item from Cart
 */
	private void deleteItem(View v) {
		mViews.remove(v);
		displayList(mViews);
		Double cost = (Double) v.getTag();
		totalcost = price.getText().toString().substring(1);
		totalprice = Double.valueOf(totalcost) - cost;
		price.setText("$" + String.format("%.2f", totalprice));

		capacity.setProgress(capacity.getProgress() - 1);

	}

/**
 * Handling Screen Rotation
 */
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("images", mViews.size());
		savedInstanceState.putInt("progress", capacity.getProgress());
		int i = 0;
		for (ImageView imageview : mViews) {
			savedInstanceState.putDouble("cost" + i,
					(Double) imageview.getTag());
			savedInstanceState.putInt("IMG_" + i, imageview.getId());
			i++;
		}

	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		capacity.setProgress(savedInstanceState.getInt("total"));
		int size = savedInstanceState.getInt("images");
		for (int i = 0; i < size; i++) {
			addCartItem(savedInstanceState.getInt("IMG_" + i),
					savedInstanceState.getDouble("cost" + i));
		}
		displayList(mViews);
	}

}
