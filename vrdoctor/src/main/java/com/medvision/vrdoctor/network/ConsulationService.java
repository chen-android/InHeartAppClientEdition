package com.medvision.vrdoctor.network;

import com.cs.networklibrary.entity.NoData;
import com.medvision.vrdoctor.beans.requestbody.ConsulationSetReq;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/6 0006.
 */

public interface ConsulationService {
	@POST("doctor/setMinPrice")
	Observable<HttpResult<NoData>> requestSetConsulationPrice(@Body ConsulationSetReq consulationSetReq);
}
