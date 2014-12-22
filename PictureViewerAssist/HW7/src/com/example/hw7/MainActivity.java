package com.example.hw7;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
/**
 * HW7
 * MainActivity.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class MainActivity extends Activity {

	EditText query;
	ImageView history;
	Button submit;
	AlertDialog.Builder builder;
	RuntimeExceptionDao<Search, Integer> runtimedao;
	AlertDialog dialog;
	DatabaseHelper dbHelp;
	Intent i;
	GenericRawResults<String[]> values;
	String[] dialogvalues={};
	List<Search> check;
	HashSet<String> dialogval;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query=(EditText) findViewById(R.id.editText1);
       submit=(Button)findViewById(R.id.button1);
        history=(ImageView)findViewById(R.id.listimg);
     	builder=new AlertDialog.Builder(this);
    	dbHelp=OpenHelperManager.getHelper(MainActivity.this, DatabaseHelper.class);
    

		try {
			runtimedao=dbHelp.getRuntimeDao();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
        history.setOnClickListener(new View.OnClickListener() {
       
        	
			@Override
			public void onClick(View v) {
				
				try {
					check=runtimedao.queryBuilder().distinct().selectColumns("query").orderBy("date", false).query();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<String> temp= new ArrayList<String>();
				for(Search s:check)
				{
					temp.add(s.getQuery());
				}
				
				dialogvalues=temp.toArray(new String[temp.size()]);
				builder.setTitle("Search History").setItems(dialogvalues, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						query.setText(dialogvalues[which]);
						
					}
				});
				
				dialog=builder.create();
				dialog.show();
			
			}
		});
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i=new Intent(MainActivity.this,GalleryActivity.class);
				String value=query.getText().toString().trim();
				//value.replaceAll("\\s+","");
				Log.d("SearchQUery",value.replaceAll("\\s+",""));
				i.putExtra("Search", value.replaceAll("\\s+",""));
				
				startActivity(i);
				
				
			}
		});
        
    }
	 
}
