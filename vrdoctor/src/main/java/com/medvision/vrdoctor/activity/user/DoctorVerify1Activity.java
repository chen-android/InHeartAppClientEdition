package com.medvision.vrdoctor.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.requestbody.VerifyInfoReq;
import com.medvision.vrdoctor.network.UserService;
import com.medvision.vrdoctor.utils.Constant;
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

public class DoctorVerify1Activity extends AppCompatActivity {

	private static final int REQUEST_IMAGE = 1;
	@InjectView(R.id.doctor_verify_iv)
	ImageView mDoctorVerifyIv;
	@InjectView(R.id.doctor_verify_next_bt)
	Button mDoctorVerifyNextBt;
	@InjectView(R.id.activity_doctor_verify)
	LinearLayout mActivityDoctorVerify;
	@InjectView(R.id.doctor_verify_name_et)
	EditText mDoctorVerifyNameEt;
	@InjectView(R.id.doctor_verify_id_et)
	EditText mDoctorVerifyIdEt;
	private ArrayList<String> paths = new ArrayList<>();

	private String token;
	private UserService userService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_verify1);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_doctor_verify));
		token = getIntent().getStringExtra("token");
		userService = HttpMethods.getInstance().getClassInstance(UserService.class);
	}

	@OnClick({R.id.doctor_verify_iv, R.id.doctor_verify_next_bt})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.doctor_verify_iv:
				imgSelect();
				break;
			case R.id.doctor_verify_next_bt:
				commitInfo();
				break;
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
				Picasso.with(this).load(new File(path.get(0))).fit().into(mDoctorVerifyIv);
			}
		}
	}

	private void commitInfo() {
		File file = new File(paths.get(0));
		if (file.exists()) {
//			RequestBody requestBody = MultipartBody.create(MultipartBody.FORM, file);
//
//			userService.uploadVerifyImg(requestBody,token,file.getName())
//					.map(new HttpResultFunc<>())
//					.subscribeOn(Schedulers.io())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(new ProgressSubscriber<>(DoctorVerify1Activity.this, verifyImg -> {
//						ToastUtil.showMessage(DoctorVerify1Activity.this, verifyImg.getImageId());
//					}));

			RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
			MultipartBody.Builder builder = new MultipartBody.Builder();
			builder.addFormDataPart("fileData", file.getName(), requestBody)
					.addFormDataPart("token", token)
					.addFormDataPart("filename", file.getName());
			userService.uploadVerifyImg(builder.build())
					.map(new HttpResultFunc<>())
					.flatMap(verifyImg -> {
						VerifyInfoReq verifyInfoReq = new VerifyInfoReq();
						verifyInfoReq.setIdNumber(mDoctorVerifyIdEt.getText().toString());
						verifyInfoReq.setImageId(verifyImg.getImageId());
						verifyInfoReq.setRealname(mDoctorVerifyNameEt.getText().toString());
						verifyInfoReq.setToken(token);
						return userService.uploadVerifyInfo(verifyInfoReq);
					})
					.map(new HttpResultFunc<>())
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new ProgressSubscriber<>(DoctorVerify1Activity.this, noData -> {
						new SweetAlertDialog(DoctorVerify1Activity.this)
								.setTitleText("提示")
								.setContentText("资料提交成功")
								.setConfirmText("确认")
								.setConfirmClickListener(sweetAlertDialog -> {
									sweetAlertDialog.dismiss();
									Intent intent = new Intent(DoctorVerify1Activity.this, DoctorVerify2Activity.class);
									intent.putExtra("status", Constant.VERIFY_STATUS_ING);
									startActivity(intent);
									finish();
								}).show();
					}));
		}
	}
}
