package com.medvision.vrdoctor.activity.user;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.medvision.vrdoctor.beans.requestbody.UserInfoReq;
import com.medvision.vrdoctor.network.UserService;
import com.medvision.vrdoctor.utils.SpUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserInfoCompleteActivity extends AppCompatActivity {

	private static final int REQUEST_IMAGE = 1;
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
	private ArrayList<String> paths = new ArrayList<>();
	private String mExpertise;//擅长
	private String mRegion;//地区

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_complete);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_user_info_complete)).setRight(getString(R.string.save), v -> saveInfo());
		mUserService = HttpMethods.getInstance().getClassInstance(UserService.class);
		requestUserInfo();
	}

	@OnClick({R.id.user_info_complete_my_job_rl, R.id.user_info_complete_my_city_rl, R.id.user_info_complete_user_head_rl})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.user_info_complete_my_job_rl:
				break;
			case R.id.user_info_complete_my_city_rl:
				break;
			case R.id.user_info_complete_user_head_rl:
				imgSelect();
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
		String headImgPath = userData.getHeadPictureUrl();
		if (!TextUtils.isEmpty(headImgPath)) {
			Picasso.with(this).load(headImgPath).error(getResources().getDrawable(R.drawable.icon_img_default)).fit().into(mUserInfoCompleteUserHeadIv);
		}
	}

	private void imgSelect() {
		Intent intent = new Intent(this, MultiImageSelectorActivity.class);
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
		// max select image amount
		// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
		// default select images (support array list)
		intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, paths);
		startActivityForResult(intent, REQUEST_IMAGE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE) {
			if (resultCode == RESULT_OK) {
				// Get the result list of select image paths
				List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
				paths.clear();
				paths.addAll(path);
				Picasso.with(this).load(new File(path.get(0))).fit().into(mUserInfoCompleteUserHeadIv);
			}
		}
	}

	private void saveInfo() {
		if (!paths.isEmpty()) {
			File file = new File(paths.get(0));
			RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
			MultipartBody.Builder builder = new MultipartBody.Builder();
			builder.addFormDataPart("fileData", file.getName(), requestBody)
					.addFormDataPart("token", SpUtils.getInstance().getToken())
					.addFormDataPart("filename", file.getName());
			mUserService.uploadHeadImg(builder.build())
					.map(new HttpResultFunc<>())
					.flatMap(userHeadImg -> {
						UserInfoReq userInfoReq = new UserInfoReq(userHeadImg.getImageUrl(), mUserInfoCompleteMySignEt.getText().toString(),
								mExpertise, mUserInfoCompleteMyDescriptionEt.getText().toString(), mRegion);
						return mUserService.requestSaveUserInfo(userInfoReq);
					})
					.map(new HttpResultFunc<>())
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new ProgressSubscriber<>(this, noData -> new SweetAlertDialog(UserInfoCompleteActivity.this, SweetAlertDialog.SUCCESS_TYPE)
							.setTitleText("提示")
							.setContentText("保存成功")
							.setConfirmText("确认")
							.setConfirmClickListener(Dialog::dismiss)
							.show()));
		} else {
			UserInfoReq userInfoReq = new UserInfoReq("", mUserInfoCompleteMySignEt.getText().toString(),
					mExpertise, mUserInfoCompleteMyDescriptionEt.getText().toString(), mRegion);
			mUserService.requestSaveUserInfo(userInfoReq)
					.map(new HttpResultFunc<>())
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new ProgressSubscriber<>(this, noData -> new SweetAlertDialog(UserInfoCompleteActivity.this, SweetAlertDialog.SUCCESS_TYPE)
							.setTitleText("提示")
							.setContentText("保存成功")
							.setConfirmText("确认")
							.setConfirmClickListener(Dialog::dismiss)
							.show()));
		}
	}
}
