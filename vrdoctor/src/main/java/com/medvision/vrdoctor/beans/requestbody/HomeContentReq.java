package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by cs on 2016/10/14.
 */

public class HomeContentReq extends BaseReq {
	private String diseaseId;
	private String type;
	private String therapyId;
	private int page;
	private String Keyword;

	public String getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTherapyId() {
		return therapyId;
	}

	public void setTherapyId(String therapyId) {
		this.therapyId = therapyId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
}
