package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class UserInfoReq extends BaseReq {
	private String headPictureUrl;
	private String introduction;
	private String expertise;
	private String signature;
	private String region;

	public UserInfoReq(String headPictureUrl, String introduction, String expertise, String signature, String region) {
		super();
		this.headPictureUrl = headPictureUrl;
		this.introduction = introduction;
		this.expertise = expertise;
		this.signature = signature;
		this.region = region;
	}

	public String getHeadPictureUrl() {
		return headPictureUrl;
	}

	public void setHeadPictureUrl(String headPictureUrl) {
		this.headPictureUrl = headPictureUrl;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
