package com.medvision.vrdoctor.network;

import com.cs.networklibrary.entity.NoData;
import com.medvision.vrdoctor.beans.Collection;
import com.medvision.vrdoctor.beans.User;
import com.medvision.vrdoctor.beans.UserHeadImg;
import com.medvision.vrdoctor.beans.UserInfo;
import com.medvision.vrdoctor.beans.VerifyImg;
import com.medvision.vrdoctor.beans.requestbody.BaseReq;
import com.medvision.vrdoctor.beans.requestbody.CollectionListReq;
import com.medvision.vrdoctor.beans.requestbody.RegisterReq;
import com.medvision.vrdoctor.beans.requestbody.SmsReq;
import com.medvision.vrdoctor.beans.requestbody.UserInfoReq;
import com.medvision.vrdoctor.beans.requestbody.UserReq;
import com.medvision.vrdoctor.beans.requestbody.VerifyInfoReq;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public interface UserService {
	//登陆
	@POST("doctor/login")
	Observable<HttpResult<User>> requestLogin(@Body UserReq userReq);

	//发送验证码
	@POST("doctor/sendCode")
	Observable<HttpResult<String>> requestSms(@Body SmsReq smsReq);

	//注册
	@POST("doctor/register")
	Observable<HttpResult<NoData>> requestRegister(@Body RegisterReq registerReq);

	//验证图片上传
	@Multipart
	@POST("doctor/authUpload")
	Observable<HttpResult<VerifyImg>> uploadVerifyImg(@Part("fileData") RequestBody fileData, @Part("token") String token, @Part("filename") String filename);

	//验证图片上传
	@POST("doctor/authUpload")
	Observable<HttpResult<VerifyImg>> uploadVerifyImg(@Body MultipartBody body);

	//上传用户头像
	@POST("doctor/headPictureUpload")
	Observable<HttpResult<UserHeadImg>> uploadHeadImg(@Body MultipartBody body);

	@POST("doctor/fillInfo")
	Observable<HttpResult<NoData>> requestSaveUserInfo(@Body UserInfoReq userInfoReq);

	//验证信息上传
	@POST("doctor/auth")

	Observable<HttpResult<NoData>> uploadVerifyInfo(@Body VerifyInfoReq verifyInfoReq);

	//获取个人收藏列表
	@POST("doctor/contentCollect/search")
	Observable<HttpResult<List<Collection>>> requestCollectionList(@Body CollectionListReq collectionListReq);

	//获取个人详细信息
	@POST("doctor/getInfo")
	Observable<HttpResult<UserInfo>> requestUserInfo(@Body BaseReq baseReq);
}

