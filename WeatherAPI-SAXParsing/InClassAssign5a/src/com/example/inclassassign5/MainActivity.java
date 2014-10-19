package com.example.inclassassign5;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * InClassAssign5
 * MainActivity.java
 * @author HARISHSAINATH GANAPATHY (800833319) DAYABARAN GANGATHARAN(800823490)
 *
 */
public class MainActivity extends Activity implements SAXAsync.getWeatherData {

	EditText ed;
	TextView Temp,humid,pressure,wind,cloud,precip;
	Switch s;
	Button b;
	String input,toggle;
	String[] inputvalues;
	boolean switchvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ed=(EditText) findViewById(R.id.editText1);
        s=(Switch)findViewById(R.id.switch1);
        b=(Button)findViewById(R.id.button1);
        toggle="imperial";
       s.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		   	   
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			if(isChecked==true)
			{
				toggle="metric";
			}
			else
			{
				toggle="imperial";
			}
			
		}
       });
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				input=ed.getEditableText().toString(); 
				 Log.d("input value", input);
				 Log.d("Switch value", toggle);
				if(input.equalsIgnoreCase("")||input==null)
				{
					Toast.makeText(MainActivity.this, "Please Enter the City", Toast.LENGTH_SHORT).show();
				}
				else
				{				
				new SAXAsync(MainActivity.this).execute("http://api.openweathermap.org/data/2.5/find?q="+input+"&units="+toggle+"&mode=xml");
				}
			}
		});
     
    }
    @Override
	public void setupData(Weather result) {
    	 Temp=(TextView)findViewById(R.id.textView7);
         humid=(TextView)findViewById(R.id.textView8);
         pressure=(TextView)findViewById(R.id.textView9);
         wind=(TextView)findViewById(R.id.textView10);
         cloud=(TextView)findViewById(R.id.textView11);
         precip=(TextView)findViewById(R.id.textView12);
		
        Temp.setText(result.getTemperature());
		humid.setText(result.getHumidity());
		pressure.setText(result.getPressure());
		wind.setText(result.getWindspeed()+","+result.getWinddirection());
		cloud.setText(result.getClouds());
		precip.setText(result.getPrecipitation());
		
	}
	
}
