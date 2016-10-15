package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public class UserReq extends BaseReq {
	private String username;
	private String password;
	private String appId;
	private String deviceVersion;
	private String deviceModel;
	private String deviceSystem;
	private String appVersion;
	private String channel;
	private String pushId;

	public UserReq() {
	}

	public UserReq(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceSystem() {
		return deviceSystem;
	}

	public void setDeviceSystem(String deviceSystem) {
		this.deviceSystem = deviceSystem;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
}
