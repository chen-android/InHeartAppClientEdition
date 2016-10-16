package com.medvision.vrdoctor.network;

import com.medvision.vrdoctor.beans.ContentFilter;
import com.medvision.vrdoctor.beans.HomeContent;
import com.medvision.vrdoctor.beans.requestbody.BaseReq;
import com.medvision.vrdoctor.beans.requestbody.HomeContentReq;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by cs on 2016/10/14.
 */

public interface ContentService {

	@POST("doctor/content/search")
	Observable<HttpResult<List<HomeContent>>> getSearchContent(@Body HomeContentReq homeContentReq);

	@POST("doctor/content/disease")
	Observable<HttpResult<List<ContentFilter>>> getContentFilterDisease(@Body BaseReq baseReq);
}
