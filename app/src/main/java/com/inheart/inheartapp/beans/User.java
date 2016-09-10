package com.inheart.inheartapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cs on 16/9/10.
 */
public class User implements Parcelable {
	private String username;
	private int age;

	protected User(Parcel in) {
		username = in.readString();
		age = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(username);
		dest.writeInt(age);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}
