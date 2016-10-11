package com.medvision.vrdoctor.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public class User implements Parcelable, Serializable {
	private String uid;
	private String realname;
	private String password;
	private String token;
	private String username;
	private String code;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.uid);
		dest.writeString(this.realname);
		dest.writeString(this.password);
		dest.writeString(this.token);
		dest.writeString(this.username);
		dest.writeString(this.code);
	}

	protected User(Parcel in) {
		this.uid = in.readString();
		this.realname = in.readString();
		this.password = in.readString();
		this.token = in.readString();
		this.username = in.readString();
		this.code = in.readString();
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
