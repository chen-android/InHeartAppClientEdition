package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by cs on 16/9/28.
 */

public class SmsReq {
	private String mobile;

	public SmsReq() {
	}

	public SmsReq(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
