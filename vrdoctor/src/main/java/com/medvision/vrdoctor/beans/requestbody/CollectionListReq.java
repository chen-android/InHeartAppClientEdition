package com.medvision.vrdoctor.beans.requestbody;

/**
 * Created by cs on 2016/10/17.
 */

public class CollectionListReq extends BaseReq {
	private int paging;

	public int getPaging() {
		return paging;
	}

	public void setPaging(int paging) {
		this.paging = paging;
	}
}
