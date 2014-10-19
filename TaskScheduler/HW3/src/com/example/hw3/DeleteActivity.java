package com.example.hw3;

import java.util.ArrayList;
import com.example.hw3try.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
/**
 * Homework 3
 * DeleteActivity.java
 * @author HARISH SAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN (800823490)
 *
 */
public class DeleteActivity extends Activity implements View.OnClickListener {

EditText date,time,title,desc,loc;
	
	DatePickerDialog datedialog;
	TimePickerDialog timedialog;
	Button delete,cancel,selecttask;
	Task t=new Task();
	RadioGroup priority;
	AlertDialog.Builder builder;
	AlertDialog dialog;
	ArrayList<Task> taskdetail=new ArrayList<Task>();
	ArrayList<String> tasknames=new ArrayList<String>();
	String[] tasks={};
	String taskname;
	String titleval,descval,timeval,location,dateval,priorityval;
	int value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		
		selecttask=(Button)findViewById(R.id.button2);
		selecttask.setOnClickListener(this);
		builder=new AlertDialog.Builder(this);
		
		title=(EditText)findViewById(R.id.editText1);
		desc=(EditText)findViewById(R.id.editText2);
		
		date=(EditText)findViewById(R.id.editText3);
		time=(EditText)findViewById(R.id.editText4);
		loc=(EditText)findViewById(R.id.editText5);
		priority=(RadioGroup)findViewById(R.id.radioGroup1);
		for (int i = 0; i < priority.getChildCount(); i++) {
			priority.getChildAt(i).setEnabled(false);
			}
		delete=(Button)findViewById(R.id.button1);
		delete.setOnClickListener(this);
		cancel=(Button)findViewById(R.id.button3);
		cancel.setOnClickListener(this);
	
		taskdetail =(ArrayList<Task>)getIntent().getExtras().get("TaskObject");
		
		for (Task t : taskdetail) {
			
			tasknames.add(t.getTitle());
		}
		tasks=tasknames.toArray(new String[tasknames.size()]);
	
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button2)
		{
			Log.d("Entered Delete Activity", "Entered Delete Activity"+"  "+tasknames.size()+"  " +tasks.length );
			builder.setTitle("Pick a Task").setItems(tasks, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					value=which;
					taskname=tasks[which];
					//t=taskdetail.get(tasks[which]);
					for (Task task : taskdetail) {
					if(task.getTitle().equalsIgnoreCase(taskname))
					{
						title.setText(task.getTitle());
						desc.setText(task.getDesc());
						date.setText(task.getDate());
						time.setText(task.getTime());
						loc.setText(task.getLocation());
						String temp=task.getPriority();
						switch(temp)
						{
						case "High":
							priority.check(R.id.radio0);
							
							break;
						case "Low":
							priority.check(R.id.radio2);
							break;
						case "Medium":
							priority.check(R.id.radio1);
							break;
						}	
					}
					}
					
				}
			});
			dialog=builder.create();
			dialog.show();
		}
		
		else if(v.getId()==R.id.button1)
		{
			taskdetail.remove(value);
			 Intent i=new Intent();
	    	 i.putExtra("taskobjafterdelete", taskdetail);
	    	 setResult(RESULT_OK, i);
	    	 finish();
			
		}
		else if(v.getId()==R.id.button3)
		{
		      finish();
		}
	}
	
}
