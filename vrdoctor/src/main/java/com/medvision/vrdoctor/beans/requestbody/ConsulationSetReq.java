package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by Administrator on 2016/11/6 0006.
 */

public class ConsulationSetReq extends BaseReq {
	private String doctorID;
	private String price;

	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
