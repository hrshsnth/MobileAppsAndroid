package com.example.hw4try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Assignment 4 
 * WelcomeActivity.java *
 * @author HARISH SAINATH GANAPATHY(800833319) DAYABARAN GANGATHARAN(800823490)
 * 
 */
public class WelcomeActivity extends Activity {

	Button startquiz, Exit;
	ProgressDialog pb;
	int i = 0;
	ImageView iv;

	Intent quizopen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Exit = (Button) findViewById(R.id.button1);
		startquiz = (Button) findViewById(R.id.button2);
		startquiz.setEnabled(false);
		iv = (ImageView) findViewById(R.id.imageView1);
		quizopen = new Intent(WelcomeActivity.this, QuizActivity.class);
		new retrieve()
				.execute("http://dev.theappsdr.com/lectures/trivia/index.php");

		Exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	class retrieve extends AsyncTask<String, Integer, ArrayList<Question>> {

		URL url;
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		String value = null;
		String[] input;
		ArrayList<String> inputvalues = new ArrayList<String>();
		ArrayList<Question> quiz = new ArrayList<Question>();
		Boolean bool;

		@Override
		protected ArrayList<Question> doInBackground(String... params) {
			try {
				url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setRequestMethod("GET");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				while (br.ready()) {
					sb.append(br.readLine());
				}
				value = sb.toString();
				input = value.split(";");
				int j = 1;
				for (int i = 0; i < input.length; i++) {
					boolean noImage = input[i].equalsIgnoreCase("");
					if (!noImage) {

						if (Character.isDigit(input[i].charAt(0))) {

							if (input[i].endsWith(input[i].charAt(0) + "")) {
								inputvalues.add(input[i].charAt(0) + "");
								Question q = formQuestion(inputvalues, j);
								quiz.add(q);
							} else {
								String temp[] = input[i].split(input[i]
										.charAt(0) + "");
								inputvalues.add(input[i].charAt(0) + "");
								Question q = formQuestion(inputvalues, j);
								j++;
								quiz.add(q);
								inputvalues.clear();
								inputvalues.add(temp[1]);

							}

						} else {

							inputvalues.add(input[i]);
						}

					} else {
						inputvalues.add(" ");
					}

				}
				Log.d("ArrayList size", "" + quiz.size());
				return quiz;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			publishProgress(i++);

			return quiz;
		}

		public boolean isInteger(String s) {
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return false;
			}

			return true;
		}

		public Question formQuestion(ArrayList<String> al, int j) {
			// System.out.println(al);
			ArrayList<String> answers = new ArrayList<String>();
			for (int i = 2; i < al.size() - 1; i++) {
				answers.add(al.get(i));
			}

			Question q = new Question(al.get(0), al.get(1), "Q" + j, al.get(al
					.size() - 1), answers);
			q.setQuestion(al.get(0));
			q.setImageurl(al.get(1));
			q.setAnswers(answers);
			q.setCorrectanswer(al.get(al.size() - 1));
			q.setQuestionnumber("Q" + j);
			return q;

		}

		@Override
		protected void onPostExecute(final ArrayList<Question> result) {

			super.onPostExecute(result);
			pb.dismiss();
			iv.setImageResource(R.drawable.bazinga);
			startquiz.setEnabled(true);

			startquiz.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					quizopen.putExtra("Quiz", result);
					startActivity(quizopen);
				}
			});

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pb = new ProgressDialog(WelcomeActivity.this);
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
	}
}
