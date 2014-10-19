package com.example.hw4try;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
/**
 * Assignment 4
 * SplashActivity.java * 
 * @author HARISHSAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN(800823490)
 * 
 */
public class SplashActivity extends Activity {

	MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
			mediaPlayer.stop();
			Intent i=new Intent(getBaseContext(),WelcomeActivity.class);
            startActivity(i);
            finish();
            
			}
		});
    }
}
