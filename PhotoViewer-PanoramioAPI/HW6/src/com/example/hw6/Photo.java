package com.example.hw6;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * HW #6 FileName : Photo.java
 * 
 * @author Dayabaran Gangatharan(800823490) & Harish Sainath Ganapathy
 *         Subramaniam(800833319)
 * 
 */
public class Photo implements Parcelable {

	String owner_name;
	String pubdate;
	String title;
	String image_url;

	public Photo() {
		super();
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Photo(Parcel in) {
		String[] data = new String[4];

		in.readStringArray(data);
		this.owner_name = data[0];
		this.pubdate = data[1];
		this.title = data[2];
		this.image_url = data[3];

	}

	@Override
	public String toString() {
		return this.title;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] { this.owner_name, this.pubdate,
				this.title, this.image_url });

	}

	public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
		public Photo createFromParcel(Parcel in) {
			return new Photo(in);
		}

		public Photo[] newArray(int size) {
			return new Photo[size];
		}
	};
}
