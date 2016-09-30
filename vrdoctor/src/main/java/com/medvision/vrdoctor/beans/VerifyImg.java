package com.medvision.vrdoctor.beans;

/**
 * Created by cs on 16/9/30.
 */

public class VerifyImg {
	private String imageId;

	public VerifyImg(String imageId) {
		this.imageId = imageId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}
