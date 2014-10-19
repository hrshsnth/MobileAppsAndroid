package com.example.hw3;

import java.util.ArrayList;

import com.example.hw3try.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
/**
 * Homework 3
 * DisplayActivity.java
 * @author HARISH SAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN (800823490)
 *
 */
public class DisplayActivity extends Activity implements View.OnClickListener {
	
	ArrayList<Task> taskdetail=new ArrayList<Task>();

	EditText date,time,title,desc,loc;
	Button previous,first,last, next,finish;
	RadioGroup priority;
	String value;
	Task t=new Task();
	int i=0,id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		priority=(RadioGroup)findViewById(R.id.radioGroup1);
		for (int i = 0; i < priority.getChildCount(); i++) {
			priority.getChildAt(i).setEnabled(false);
			}
		first=(Button)findViewById(R.id.button1);
		previous=(Button)findViewById(R.id.button2);
		next=(Button)findViewById(R.id.button4);
		last=(Button)findViewById(R.id.button5);
		finish=(Button)findViewById(R.id.button3);
		first.setOnClickListener(this);
		previous.setOnClickListener(this);
		next.setOnClickListener(this);
		last.setOnClickListener(this);
		finish.setOnClickListener(this);
		
		title=(EditText)findViewById(R.id.editText1);
		desc=(EditText)findViewById(R.id.editText2);
		
		date=(EditText)findViewById(R.id.editText3);
		time=(EditText)findViewById(R.id.editText4);
		loc=(EditText)findViewById(R.id.editText5);
		
		taskdetail =(ArrayList<Task>)getIntent().getExtras().get("TaskObject");
	
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1)
		{
			t=taskdetail.get(0);
			setValues(t);
		}
		else if(v.getId()==R.id.button5)
		{
			t=taskdetail.get(taskdetail.size()-1);
			setValues(t);
		}
		else if(v.getId()==R.id.button4)
		{
			value=title.getText().toString();
			int j=findDisplayItem(value);
			
			if(j+1<taskdetail.size())
			{
				t=taskdetail.get(j+1);
			setValues(t);
			}else
			{
				t=taskdetail.get(j);
			setValues(t);
				
			}
		}
		else if(v.getId()==R.id.button2)
		{
			value=title.getText().toString();
			int j=findDisplayItem(value);
			if(j-1>0)
			{
			t=taskdetail.get(j-1);
			setValues(t);
			}
			else
			{
				t=taskdetail.get(0);
				setValues(t);
			}
		}
		else if(v.getId()==R.id.button3)
		{
			finish();
		}
		
	}
	public void setValues(Task t)
	{
	title.setText(t.getTitle());
	desc.setText(t.getDesc());
	date.setText(t.getDate());
	time.setText(t.getTime());
	loc.setText(t.getLocation());
	String temp=t.getPriority();
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
	
	public int findDisplayItem(String value)
	{
		for(int i=0;i<taskdetail.size();i++)
		{
			t=taskdetail.get(i);
			if(value.equalsIgnoreCase(t.getTitle()))
			{
				id=i;
				
			}
			
		}
		return id;
	}

	
}
