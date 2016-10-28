package com.medvision.vrdoctor.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.UserInfo;
import com.medvision.vrdoctor.beans.requestbody.BaseReq;
import com.medvision.vrdoctor.network.UserService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserInfoCompleteActivity extends AppCompatActivity {

	@InjectView(R.id.user_info_complete_user_head_iv)
	ImageView mUserInfoCompleteUserHeadIv;
	@InjectView(R.id.user_info_complete_user_head_rl)
	RelativeLayout mUserInfoCompleteUserHeadRl;
	@InjectView(R.id.user_info_complete_my_job_tv)
	TextView mUserInfoCompleteMyJobTv;
	@InjectView(R.id.user_info_complete_my_city_tv)
	TextView mUserInfoCompleteMyCityTv;
	@InjectView(R.id.user_info_complete_my_sign_et)
	EditText mUserInfoCompleteMySignEt;
	@InjectView(R.id.user_info_complete_my_description_et)
	EditText mUserInfoCompleteMyDescriptionEt;
	@InjectView(R.id.user_info_complete_my_job_rl)
	RelativeLayout mUserInfoCompleteMyJobRl;
	@InjectView(R.id.user_info_complete_my_city_rl)
	RelativeLayout mUserInfoCompleteMyCityRl;

	private UserService mUserService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_complete);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_user_info_complete)).setRight(getString(R.string.save), v -> saveInfo());
		mUserService = HttpMethods.getInstance().getClassInstance(UserService.class);
		requestUserInfo();
	}

	@OnClick({R.id.user_info_complete_my_job_rl, R.id.user_info_complete_my_city_rl})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.user_info_complete_my_job_rl:
				break;
			case R.id.user_info_complete_my_city_rl:
				break;
		}
	}

	private void requestUserInfo() {
		mUserService.requestUserInfo(new BaseReq())
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(this, this::setUserData));
	}

	private void setUserData(UserInfo userData) {
		mUserInfoCompleteMySignEt.setText(userData.getSignature());
	}

	private void saveInfo() {

	}
}
