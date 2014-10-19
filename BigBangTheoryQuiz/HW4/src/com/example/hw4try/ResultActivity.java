package com.example.hw4try;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.*;
/**
 * Assignment 4
 * ResultActivity.java * 
 * @author HARISHSAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN(800823490)
 * 
 */
public class ResultActivity extends Activity implements View.OnClickListener{

	ProgressBar pb;
	Button quit,tryagain;
	TextView tv,message;
	Intent tryquiz;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		int total=getIntent().getExtras().getInt("result");
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		tv=(TextView)findViewById(R.id.textView3);
		message=(TextView)findViewById(R.id.textView4);
		quit=(Button)findViewById(R.id.button1);
		tryagain=(Button)findViewById(R.id.button2);
		quit.setOnClickListener(this);
		tryagain.setOnClickListener(this);
		pb.setMax(100);
		pb.setProgress(total*10);
		
		tv.setText(total*10+"%");
		if(total==10)
		{
			message.setText("Congratulations!!!");
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.button1)
		{
			finish();
		}
		if(v.getId()==R.id.button2)
		{
			ArrayList<Question> quiz=(ArrayList<Question>) getIntent().getExtras().get("quiz");
			tryquiz=new Intent(this,QuizActivity.class);
			tryquiz.putExtra("Quiz", quiz);
			startActivity(tryquiz);
			finish();
		}
	}
}
