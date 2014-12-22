package com.example.finalproject;

import jim.h.common.android.zxinglib.integrator.IntentIntegrator;
import jim.h.common.android.zxinglib.integrator.IntentResult;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {

	Button scan,search;
	//TextView format,content;
	EditText format,content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		scan=(Button)findViewById(R.id.button1);
		format=(EditText)findViewById(R.id.editText1);
		content=(EditText)findViewById(R.id.editText2);
		
		search=(Button)findViewById(R.id.button2);
		
		
		scan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			/*	IntentIntegrator scanIntegrator = new IntentIntegrator(SearchActivity.this);
				scanIntegrator.initiateScan();*/
				   // set the last parameter to true to open front light if available
                IntentIntegrator.initiateScan(SearchActivity.this, R.layout.capture,
                        R.id.viewfinder_view, R.id.preview_view, true);
				
			}
		});
		
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {		
				
				if(format.getEditableText().toString().equals("") && content.getEditableText().toString().equals(""))
				{
					Toast.makeText(SearchActivity.this,"Please Enter either ISBN or Title", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Intent i=new Intent(SearchActivity.this,BookListActivity.class);
					i.putExtra("ISBN", format.getEditableText().toString());
					i.putExtra("Title", content.getEditableText().toString());
					startActivity(i);	
				}
				
			}
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (scanningResult != null) {
			//we have a result
			String scanContent = scanningResult.getContents();
			//String scanFormat = scanningResult.getFormatName();
			format.setText(scanContent);
			//content.setText(scanContent);
			}
		else{
		    Toast toast = Toast.makeText(getApplicationContext(), 
		        "No scan data received!", Toast.LENGTH_SHORT);
		    toast.show();
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
