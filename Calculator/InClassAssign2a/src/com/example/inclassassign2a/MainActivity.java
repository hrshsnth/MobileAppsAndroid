package com.example.inclassassign2a;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;


/**
 * @author HARISH SAINATH GANAPATHY (800833319) and Dayabaran Gangatharan(800823490)
 * InClassAssignment2
 */
public class MainActivity extends Activity implements View.OnClickListener {

	EditText ed1;
	EditText ed2;
	EditText tv;
	
	
	//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 =(EditText) findViewById(R.id.editText1);
        
        //input1= Integer.parseInt(ed1.getText().toString());
    	Log.d("input1","Input1" );
        ed2=(EditText) findViewById(R.id.editText2);
    	
        //input2= Integer.parseInt(ed2.getText().toString());
    	
        Log.d("input2","Input2" );
    	 tv=(EditText) findViewById(R.id.editText3);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		
		Double input1=Double.parseDouble(ed1.getText().toString());
		Double input2=Double.parseDouble(ed2.getText().toString());
		if(input1==0 && input2==0)
		{
			Toast.makeText(this, "Enter Input", Toast.LENGTH_LONG).show();
		}
		else
		{
		Double result=(double) 0;
		if(v.getId()==R.id.button1)
		{
			result=input1+input2;
			Math.round(result);
			tv.setText(String.valueOf(result));
		}else if(v.getId()==R.id.button2)
		{
			if(input1>input2)
			{
				result=input1-input2;
				Math.round(result);
			tv.setText(String.valueOf(result));
			}
			else
			{
				result=input2-input1;
				Math.round(result);
				tv.setText(String.valueOf(result));
			}
		}else if(v.getId()==R.id.button3)
		{
			result=input1*input2;
			Math.round(result);
			tv.setText(String.valueOf(result));
		}
		else if(v.getId()==R.id.button4)
		{
			/*if(input2==0 || input1==0)
			{
				//Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show();
			}
			else */if(input1>input2)
					{
				if(input2==0)
				{
					Toast.makeText(this, "Divide By Zero", Toast.LENGTH_LONG).show();
				}
				else
				{
					result=input1/input2;
					Math.round(result);
					tv.setText(String.valueOf(result));
				}
					}
				else
				{
				tv.setText(String.valueOf(0));
				}
		}
		else if(v.getId()==R.id.button5)
		{
			ed1.setText("");
			ed2.setText("");
			tv.setText("");
		}
		}	
	}
	
    
}
