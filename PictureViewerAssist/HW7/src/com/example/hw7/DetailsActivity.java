package com.example.hw7;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * HW7
 * DetailsActivity.java
 * @author HARISHSAINATH GANAPATHY(800833319) & Dayabaran Gangatharan(800823490) 
 *
 */
public class DetailsActivity extends Activity {

	ImageView photo,owner,addcomment;
	TextView photoname,ownername,display;
	EditText comment;
	RuntimeExceptionDao<Search, Integer> runtimedao;
	AlertDialog dialog;
	DatabaseHelper dbHelp;
	RelativeLayout mainlayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		mainlayout=(RelativeLayout)findViewById(R.id.ActivityDetails);
		photoname =(TextView) findViewById(R.id.textView1);
		ownername=(TextView)findViewById(R.id.textView2);
		display=(TextView)findViewById(R.id.textView3);
		photo=(ImageView)findViewById(R.id.imageView1);
		owner=(ImageView)findViewById(R.id.imageView2);
		addcomment=(ImageView)findViewById(R.id.imageView3);
		comment=(EditText)findViewById(R.id.editText1);
		
		photoname.setText(getIntent().getStringExtra("photoname"));
		ownername.setText(getIntent().getStringExtra("ownname"));
		
		dbHelp=OpenHelperManager.getHelper(DetailsActivity.this, DatabaseHelper.class);
	    

		try {
			runtimedao=dbHelp.getRuntimeDao();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}


		String text = null;
		
		
			List<Search> result=runtimedao.queryForEq("PhotoURL",getIntent().getStringExtra("photoIMG"));
			for(Search s:result)
			{
				text=s.getComments();
				
			}
			if(text.length()!=0)
			{
				Log.d("Params ",mainlayout+"   "+comment+"   "+addcomment );
				comment.setVisibility(View.INVISIBLE);
				addcomment.setVisibility(View.INVISIBLE);
				//mainlayout.removeView(comment);
				//mainlayout.removeView(addcomment);
				display.setText("Your Note: "+text);
			}
			else
			{
				display.setVisibility(View.INVISIBLE);
				//mainlayout.removeView(display);
				Log.d("No Comment", "No Comment");
				comment.setVisibility(View.VISIBLE);
				addcomment.setVisibility(View.VISIBLE);
				comment.setText("");
			}
		
		
		String PhotoIMG=getIntent().getStringExtra("photoIMG");
		String PhotoOWN=getIntent().getStringExtra("photoOWN");
		if(PhotoIMG!=null || PhotoIMG.length()!=0 || PhotoIMG!="")
		{
			Picasso.with(this).load(getIntent().getStringExtra("photoIMG")).into(photo);	
		}
		else
		{
			photo.setImageResource(R.drawable.photo_not_found);
		}
		
		if(PhotoOWN!=null || PhotoOWN.length()!=0 || PhotoOWN!="")
		{
			Picasso.with(this).load(getIntent().getStringExtra("photoOWN")).into(owner);	
		}
		else
		{
			owner.setImageResource(R.drawable.user_not_found);
		}
		
		
		
				
		addcomment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				if(comment.getEditableText().toString().length()!=0)
				{
					Log.d("Comment Value", comment.getEditableText().toString().trim());
					runtimedao.executeRawNoArgs("UPDATE Search set Comments='"
							+ comment.getEditableText() + "' where PhotoURL='"
							+ getIntent().getStringExtra("photoIMG")+"'");	
					
					comment.setVisibility(View.INVISIBLE);
					addcomment.setVisibility(View.INVISIBLE);
					display.setVisibility(View.VISIBLE);
					//mainlayout.removeView(comment);
					//mainlayout.removeView(addcomment);
					display.setText("Your Note: "+comment.getEditableText().toString());
					Toast.makeText(DetailsActivity.this, "Comment Added", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(DetailsActivity.this, "Please Enter a Comment", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
}
