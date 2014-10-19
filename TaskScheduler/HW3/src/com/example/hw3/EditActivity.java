package com.example.hw3;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.hw3try.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import android.util.Log;
import android.view.*;
/**
 * Homework 3
 * EditActivity.java
 * @author HARISH SAINATH GANAPATHY(800833319)
 *
 */
public class EditActivity extends Activity implements View.OnClickListener {

	EditText date,time,title,desc,loc;
	DatePickerDialog datedialog;
	TimePickerDialog timedialog;
	int Year,Month,Day,hour,minute,value;
	Button save,selecttask;
	Task t=new Task();
	RadioGroup priority;
	AlertDialog.Builder builder;
	AlertDialog dialog;
	ArrayList<Task> taskdetail=new ArrayList<Task>();
	ArrayList<String> tasknames=new ArrayList<String>();
	String[] tasks={};
	String titleval,descval,timeval,location,dateval,priorityval,taskname,AM_PM;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		selecttask=(Button)findViewById(R.id.button2);
		selecttask.setOnClickListener(this);
		builder=new AlertDialog.Builder(this);
		
		title=(EditText)findViewById(R.id.editText1);
		desc=(EditText)findViewById(R.id.editText2);
		
		date=(EditText)findViewById(R.id.editText3);
		time=(EditText)findViewById(R.id.editText4);
		loc=(EditText)findViewById(R.id.editText5);
		date.setOnClickListener(this);
		time.setOnClickListener(this);
		final Calendar c = Calendar.getInstance();
		Year = c.get(Calendar.YEAR);
		Month = c.get(Calendar.MONTH);
		Day = c.get(Calendar.DAY_OF_MONTH);
		priority=(RadioGroup)findViewById(R.id.radioGroup1);
		priority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
		        case R.id.radio0:
		        	priorityval="High";
		        break;
		            
		        case R.id.radio1:
		        	priorityval="Medium";
		        break;
		        case R.id.radio2:
		        	priorityval="Low";
		        break;
		        default:
		        	priorityval="High";
		     	   break ;
		        }
			}
		});
		save=(Button)findViewById(R.id.button1);
		save.setOnClickListener(this);
		
		taskdetail =(ArrayList<Task>)getIntent().getExtras().get("TaskObject");
				Log.d("AL size",""+taskdetail.size());
		
		for (Task t : taskdetail) {
			
			tasknames.add(t.getTitle());
		}
		tasks=tasknames.toArray(new String[tasknames.size()]);
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button2)
		{
			
			builder.setTitle("Pick a Task").setItems(tasks, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					value=which;
					taskname=tasks[which];
					Log.d("taskname", ""+taskname);
				for (Task task  : taskdetail) {
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
		else if(v.getId()==R.id.editText3)
		{
			 datedialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					
					date.setText(dayOfMonth + "-"
	                        + (monthOfYear + 1) + "-" + year);
					
				}
			}, Year, Month, Day);
			datedialog.show();
		}
		else if(v.getId()==R.id.editText4)
		{
			timedialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					if(hourOfDay < 12) {
			            AM_PM = "AM";
			            
			        } else {
			            AM_PM = "PM";
			            hourOfDay=hourOfDay-12;
			        }
					time.setText(hourOfDay + ":" + minute +"  "+AM_PM );
					
				}
			}, hour, minute,false);
			timedialog.show();
		}
		else if(v.getId()==R.id.button1)
		{
			Log.d("Entered Save Button", "Entered Save Button");
			taskdetail.remove(value);			
			Task tnew=new Task();
			 titleval=title.getEditableText().toString();
		       descval=desc.getEditableText().toString();
		        dateval=date.getEditableText().toString();
		       timeval= time.getEditableText().toString();
		      location = loc.getEditableText().toString();
		      Log.d("allvalues",""+titleval+""+descval+""+dateval+""+timeval+""+location+""+priorityval);
		      if(titleval.equals("")|| descval.equals("")|| dateval.equals("")||timeval.equals("") || location.equals("")||priorityval.equals(""))
		      {
		    	Toast.makeText(this, "Please enter all the information", Toast.LENGTH_LONG).show();
		      }
		      else
		      {
		    	  
		    	  Log.d("Entered Else loop", "Entered Else Loop");
		    
		    	tnew.setTitle(titleval);
		    	 tnew.setDesc(descval);
		    	 tnew.setDate(dateval);
		    	 tnew.setTime(timeval);
		    	 tnew.setLocation(location);
		    	 tnew.setPriority(priorityval);
		    	 taskdetail.add(tnew);
		    
		    	 Intent i=new Intent();
		    	 i.putExtra("taskobjafteredit", taskdetail);
		    	 setResult(RESULT_OK, i);
		    }
		      finish();
		}
	}
}
