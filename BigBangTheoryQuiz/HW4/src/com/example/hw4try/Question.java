package com.example.hw4try;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Assignment 4
 * Question.java
 * @author HARISHSAINATH GANAPATHY(800833319)
 *
 */
public class Question implements Parcelable {

	private String question;
	private String imageurl;
	private String Questionnumber;
	private String correctanswer;
	private ArrayList<String> answers;
	public String getQuestionnumber() {
		return Questionnumber;
	}
	public void setQuestionnumber(String questionnumber) {
		Questionnumber = questionnumber;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getCorrectanswer() {
		return correctanswer;
	}
	public void setCorrectanswer(String correctanswer) {
		this.correctanswer = correctanswer;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	public Question(String question, String imageurl, String questionnumber,
			String correctanswer, ArrayList<String> answers) {
		super();
		this.question = question;
		this.imageurl = imageurl;
		Questionnumber = questionnumber;
		this.correctanswer = correctanswer;
		this.answers = answers;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(question);
		dest.writeString(imageurl);
		dest.writeString(Questionnumber);
		dest.writeString(correctanswer);
		dest.writeList(answers);
	}
	
	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
		public Question createFromParcel(Parcel in) {
			return new Question(in);
		}

		public Question[] newArray(int size) {
			return new Question[size];
		}
	};

	private Question(Parcel in) {
		
		this.question=in.readString();
		this.imageurl=in.readString();
		this.Questionnumber=in.readString();
		this.correctanswer=in.readString();
		this.answers=in.readArrayList(null);
		
		}
	
	
}
