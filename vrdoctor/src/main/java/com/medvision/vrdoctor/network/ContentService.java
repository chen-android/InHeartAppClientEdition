package com.medvision.vrdoctor.network;

import com.medvision.vrdoctor.beans.FilterDisease;
import com.medvision.vrdoctor.beans.FilterTherapy;
import com.medvision.vrdoctor.beans.FilterType;
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
	/**
	 * 搜索主页内容
	 *
	 * @param homeContentReq
	 * @return
	 */
	@POST("doctor/content/search")
	Observable<HttpResult<List<HomeContent>>> getSearchContent(@Body HomeContentReq homeContentReq);

	/**
	 * 获取筛选内容列
	 *
	 * @param baseReq
	 * @return
	 */
	@POST("doctor/content/type")
	Observable<HttpResult<List<FilterType>>> getFilterType(@Body BaseReq baseReq);

	/**
	 * 获取筛选病种列
	 *
	 * @param baseReq
	 * @return
	 */
	@POST("doctor/content/disease")
	Observable<HttpResult<List<FilterDisease>>> getFilterDisease(@Body BaseReq baseReq);

	/**
	 * 获取筛选疗法列
	 *
	 * @param baseReq
	 * @return
	 */
	@POST("doctor/content/therapy")
	Observable<HttpResult<List<FilterTherapy>>> getFilterTherapy(@Body BaseReq baseReq);
}
