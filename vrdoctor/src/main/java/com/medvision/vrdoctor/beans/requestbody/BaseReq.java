package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by cs on 2016/10/12.
 */

public class BaseReq {
	private String token;

	public BaseReq() {
	}

	public BaseReq(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}