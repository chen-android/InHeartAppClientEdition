package com.medvision.vrdoctor.beans.requestbody;

import com.medvision.vrdoctor.utils.SpUtils;

/**
 * Created by cs on 2016/10/12.
 */

public class BaseReq {
	private String token;

	public BaseReq() {
		token = SpUtils.getInstance().getToken();
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
