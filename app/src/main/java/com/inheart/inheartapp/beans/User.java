package com.inheart.inheartapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cs on 16/9/10.
 */
public class User implements Parcelable {
	private String userId;
	private String username;
	private int age;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.userId);
		dest.writeString(this.username);
		dest.writeInt(this.age);
	}

	public User() {
	}

	protected User(Parcel in) {
		this.userId = in.readString();
		this.username = in.readString();
		this.age = in.readInt();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}
