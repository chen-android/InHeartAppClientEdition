package com.medvision.vrdoctor.network;

import com.cs.networklibrary.entity.HttpResult;
import com.medvision.vrdoctor.beans.User;
import com.medvision.vrdoctor.beans.requestbody.UserReq;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public interface UserService {
	@POST("doctor/login")
	Observable<HttpResult<User>> requestLogin(@Body UserReq userReq);
}
