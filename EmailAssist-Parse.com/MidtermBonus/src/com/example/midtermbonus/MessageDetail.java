package com.example.midtermbonus;

import java.util.Date;

public class MessageDetail {

	String sender;
	String date;
	String Message;
	boolean isRead;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String receiver) {
		this.date = receiver;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
}
