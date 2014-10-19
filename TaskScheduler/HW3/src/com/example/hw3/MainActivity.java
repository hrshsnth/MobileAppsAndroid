package com.example.hw3;
import java.util.ArrayList;

import com.example.hw3try.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Homework 3
 * MainActivity.java
 * @author HARISH SAINATH GANAPATHY(800833319)
 *
 */
public class MainActivity extends Activity implements View.OnClickListener{

	final static int CODE_CREATE=100,CODE_EDIT=200,CODE_DELETE=300,CODE_VIEW=400;
	Button create,edit,delete,view,finish;
	ArrayList<Task> taskdetail=new ArrayList<Task>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        create=(Button)findViewById(R.id.button1);
        create.setOnClickListener(this);
        edit=(Button)findViewById(R.id.button2);
        edit.setOnClickListener(this);
        delete=(Button)findViewById(R.id.button3);
        delete.setOnClickListener(this);
        view=(Button)findViewById(R.id.button4);
        view.setOnClickListener(this);
        finish=(Button)findViewById(R.id.button5);
        finish.setOnClickListener(this);
        
    }
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1)
		{
			Intent i = new Intent(this,CreateTaskActivity.class);
			i.putExtra("TaskObject",taskdetail);
			startActivityForResult(i, CODE_CREATE);
		}
		if(v.getId()==R.id.button2)
		{
			if(taskdetail.size()!=0)
			{
			Intent i = new Intent(this,EditActivity.class);
			i.putExtra("TaskObject",taskdetail);
			startActivityForResult(i, CODE_EDIT);
			}
			else 
			{
				Toast.makeText(this, "No Tasks to Edit",Toast.LENGTH_SHORT).show();
			}
		}
		if(v.getId()==R.id.button3)
		{
			if(taskdetail.size()!=0)
			{
			Intent i = new Intent(this,DeleteActivity.class);
			
			i.putExtra("TaskObject",taskdetail);
			startActivityForResult(i, CODE_DELETE);
			}
			else 
			{
				Toast.makeText(this, "No Tasks to Delete",Toast.LENGTH_SHORT).show();
			}
			
		}
		if(v.getId()==R.id.button4)
		{
			if(taskdetail.size()!=0)
			{
			Intent i = new Intent(this,DisplayActivity.class);
			i.putExtra("TaskObject",taskdetail);
			startActivity(i);
			}
			else 
			{
				Toast.makeText(this, "No Tasks to View",Toast.LENGTH_SHORT).show();
			}
			
		}
		if(v.getId()==R.id.button5)
		{
			finish();
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==CODE_CREATE)
		{
			if(resultCode==RESULT_OK && data.getExtras().get("taskobj")!=null)
			{
				taskdetail=(ArrayList<Task>)data.getExtras().get("taskobj");
			}
		
		}
		else if(requestCode==CODE_EDIT)
		{
			if(resultCode==RESULT_OK && data.getExtras().get("taskobjafteredit")!=null)
			{
				taskdetail=(ArrayList<Task>)data.getExtras().get("taskobjafteredit");
			}
		}
		else if(requestCode==CODE_DELETE)
		{
			if(resultCode==RESULT_OK && data.getExtras().get("taskobjafterdelete")!=null)
			{
				taskdetail=(ArrayList<Task>)data.getExtras().get("taskobjafterdelete");
			}
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
