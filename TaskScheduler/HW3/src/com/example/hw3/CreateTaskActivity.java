package com.example.hw3;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.hw3try.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
/**
 * Homework 3
 * CreateTaskActivity.java
 * @author HARISH SAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN (800823490)
 *
 */
public class CreateTaskActivity extends Activity implements View.OnClickListener{

	EditText date,time,title,desc,loc;
	AlertDialog.Builder build;
	DatePickerDialog datedialog;
	TimePickerDialog timedialog;
	RadioButton high;
	int Year,Month,Day,hour,minute;
	String AM_PM;
	RadioGroup priority;
	Button save;
	String titleval,descval,timeval,location,dateval,priorityval;
	Task t=new Task();
	
	ArrayList<Task> taskdetail=new ArrayList<Task>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);
		priority=(RadioGroup)findViewById(R.id.radioGroup1);
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
		
		save=(Button)findViewById(R.id.button1);
		save.setOnClickListener(this);
		
		time.setKeyListener(null);
		date.setKeyListener(null);
		priority =(RadioGroup) findViewById(R.id.radioGroup1);
		
		   priorityval="High";
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
		        	priorityval="";
		     	   break ;
		        }
			}
		});
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.editText3)
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
		else if(v.getId()== R.id.button1)
		{
			  titleval=title.getEditableText().toString();
		       descval=desc.getEditableText().toString();
		        dateval=date.getEditableText().toString();
		       timeval= time.getEditableText().toString();
		      location = loc.getEditableText().toString();
		      Log.d("allvalues",""+titleval+""+descval+""+dateval+""+timeval+""+location+""+priorityval);
		      if(titleval.matches("")|| descval.matches("")|| dateval.matches("")||timeval.matches("") || location.matches("")||priorityval.matches(""))
		      {
		    	Toast.makeText(this, "Please enter all the information", Toast.LENGTH_LONG).show();
		    	
		      }
		      else
		      {
		    	  Log.d("Entered Else loop", "Entered Else Loop");
		    	
		    	  taskdetail=(ArrayList<Task>)getIntent().getExtras().get("TaskObject");
		    	 t.setTitle(titleval);
		    	 t.setDesc(descval);
		    	 t.setDate(dateval);
		    	 t.setTime(timeval);
		    	 t.setLocation(location);
		    	 t.setPriority(priorityval);
		    	
		    	 taskdetail.add(t);
		    	 Intent i=new Intent();
		    	 i.putExtra("taskobj", taskdetail);
		    	 setResult(RESULT_OK, i);
		    	 finish();
		    }
		     
		}
	}
}
