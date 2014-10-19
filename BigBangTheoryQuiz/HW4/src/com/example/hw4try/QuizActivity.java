package com.example.hw4try;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Assignment 4
 * QuizActivity.java * 
 * @author HARISHSAINATH GANAPATHY(800833319)
 * 
 */
public class QuizActivity extends Activity {

	ArrayList<Question> quizdetail = new ArrayList<Question>();

	TextView tv, questionnum, Timer;
	ImageView quizimage;
	ProgressDialog pb;
	Boolean bool;
	Button next, quit;
	RadioGroup options;
	int i = 0;
	int j = 1;
	String Question, ImageURL, QuestionNum, CorrectAnswer;
	Intent resultactivity;
	int lastvalue;
	int result, total, temp, errorcode = 9;
	ArrayList<Integer> optionClicked = new ArrayList<Integer>();
	ArrayList<Integer> CorrectAnswers = new ArrayList<Integer>();
	CountDownTimer cn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		quizdetail = (ArrayList<Question>) getIntent().getExtras().get("Quiz");

		resultactivity = new Intent(this, ResultActivity.class);
		for (Question q : quizdetail) {
			lastvalue = quizdetail.lastIndexOf(q);
			CorrectAnswers.add(Integer.parseInt(q.getCorrectanswer()));

		}

		tv = (TextView) findViewById(R.id.textView3);
		questionnum = (TextView) findViewById(R.id.textView1);
		Timer = (TextView) findViewById(R.id.textView2);
		quizimage = (ImageView) findViewById(R.id.imageView1);
		quit = (Button) findViewById(R.id.button1);
		next = (Button) findViewById(R.id.button2);
		options = (RadioGroup) findViewById(R.id.radioGroup1);

		for (Question q : quizdetail) {
			if (quizdetail.indexOf(q) == 0) {

				tv.setText(q.getQuestion());
				new retrieveImage().execute(q.getImageurl());
				ArrayList<String> firstanswers = q.getAnswers();
				questionnum.setText(q.getQuestionnumber());
				int radio = 0;
				for (String s : firstanswers) {

					RadioButton rd = new RadioButton(QuizActivity.this);
					rd.setId(radio);
					radio++;
					rd.setText(s);
					options.addView(rd);

				}
			}
		}
		cn = new CountDownTimer(24000, 1000) {

			public void onTick(long millisUntilFinished) {
				Timer.setText(millisUntilFinished / 1000 + " Seconds");

			}

			public void onFinish() {
				loadnext();
			}
		}.start();
		quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cn.cancel();
				finish();

			}
		});
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				cn.cancel();
				loadnext();
			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		cn.cancel();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		cn.cancel();
	}

	public void loadnext() {
		optionClicked.add(options.getCheckedRadioButtonId());
		options.clearCheck();

		if (j == lastvalue + 1) {
			System.out.println(optionClicked);
			System.out.println(CorrectAnswers);
			for (int i = 0; i < optionClicked.size(); i++) {
				if (CorrectAnswers.get(i) == (optionClicked.get(i))) {
					total = total + 1;

				}
			}
			ArrayList<Question> tryagain = (ArrayList<Question>) getIntent()
					.getExtras().get("Quiz");
			resultactivity.putExtra("quiz", tryagain);

			resultactivity.putExtra("result", total);
			cn.cancel();
			startActivity(resultactivity);
			finish();

		} else {
			options.removeAllViews();

			ArrayList<String> answers = getNext(j);
			tv.setText(Question);
			questionnum.setText(QuestionNum);
			if (ImageURL.equalsIgnoreCase(" ")) {
				quizimage.setImageResource(R.drawable.noimage);
			} else {
				new retrieveImage().execute(ImageURL);
			}
			int radio = 0;

			for (String s : answers) {

				RadioButton rd = new RadioButton(QuizActivity.this);
				rd.setId(radio);
				radio++;
				rd.setText(s);
				options.addView(rd);

			}
			j++;
			answers.clear();
		}

	}

	public ArrayList<String> getNext(int j) {
		ArrayList<String> answers = new ArrayList<String>();
		for (Question q : quizdetail) {

			if (quizdetail.indexOf(q) == j) {

				Question = q.getQuestion();
				ImageURL = q.getImageurl();
				answers = new ArrayList<String>();
				answers = q.getAnswers();
				QuestionNum = q.getQuestionnumber();
				CorrectAnswer = q.getCorrectanswer();
				cn.start();
			}

		}
		return answers;
	}

	class retrieveImage extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			URL url = null;
			try {
				url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setRequestMethod("GET");
				Bitmap image = BitmapFactory.decodeStream(con.getInputStream());
				return image;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			publishProgress(i++);
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pb = new ProgressDialog(QuizActivity.this);
			pb.setMessage("Loading image");
			pb.setProgress(0);
			pb.setMax(100);
			pb.setCancelable(false);
			pb.show();

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			pb.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pb.dismiss();
			quizimage.setImageBitmap(result);

		}

	}
}
