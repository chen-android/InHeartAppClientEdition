package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by cs on 2016/10/11.
 */

public class VerifyInfoReq {
	private String idNumber;
	private String realname;
	private String imageId;

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}