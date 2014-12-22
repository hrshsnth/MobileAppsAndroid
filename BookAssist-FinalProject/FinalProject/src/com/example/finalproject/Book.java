package com.example.finalproject;

import java.io.Serializable;

public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String booktitle;
	String bookauthor;
	String bookisbn;
	String bookimageurl;
	String bookdesc;
	String product_url;

	
	
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getBookisbn() {
		return bookisbn;
	}
	public void setBookisbn(String bookisbn) {
		this.bookisbn = bookisbn;
	}
	public String getBookimageurl() {
		return bookimageurl;
	}
	public void setBookimageurl(String bookimageurl) {
		this.bookimageurl = bookimageurl;
	}
	public String getBookdesc() {
		return bookdesc;
	}
	public void setBookdesc(String bookdesc) {
		this.bookdesc = bookdesc;
	}
	
	public String getProduct_url() {
		return product_url;
	}
	public void setProduct_url(String product_url) {
		this.product_url = product_url;
	}
	@Override
	public String toString() {
		return "Book [booktitle=" + booktitle + ", bookauthor=" + bookauthor
				+ ", bookisbn=" + bookisbn + ", bookimageurl=" + bookimageurl
				+ ", bookdesc=" + bookdesc + ", product_url=" + product_url
				+ "]";
	}
	
	
	
	
}
