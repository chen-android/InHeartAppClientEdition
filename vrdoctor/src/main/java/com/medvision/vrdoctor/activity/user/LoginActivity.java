package com.medvision.vrdoctor.activity.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cs.common.utils.SystemUtils;
import com.cs.common.utils.ToastUtil;
import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.activity.MainActivity;
import com.medvision.vrdoctor.beans.User;
import com.medvision.vrdoctor.beans.requestbody.UserReq;
import com.medvision.vrdoctor.network.UserService;
import com.medvision.vrdoctor.utils.Constant;
import com.medvision.vrdoctor.utils.SpUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kr.co.namee.permissiongen.PermissionGen;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

	private static final int REQUEST_CODE_REGISTER = 0;

	@InjectView(R.id.login_username_et)
	EditText mLoginUsernameEt;
	@InjectView(R.id.login_pwd_et)
	EditText mLoginPwdEt;
	@InjectView(R.id.login_register_tv)
	TextView mLoginRegisterTv;
	@InjectView(R.id.login_forget_pwd_tv)
	TextView mLoginForgetPwdTv;
	@InjectView(R.id.login_confirm_bt)
	Button mLoginConfirmBt;

	private UserService userService;
	private User user;
	private boolean hasPermission;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);
		userService = HttpMethods.getInstance().getClassInstance(UserService.class);
		user = SpUtils.getInstance().getUser();
		PermissionGen.with(this)
				.addRequestCode(100)
				.permissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
				.request();

	}

	@OnClick({R.id.login_register_tv, R.id.login_forget_pwd_tv, R.id.login_confirm_bt})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.login_register_tv:
				startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), REQUEST_CODE_REGISTER);
				break;
			case R.id.login_forget_pwd_tv:
				break;
			case R.id.login_confirm_bt:
				performLogin();
				break;
		}
	}

	private void performLogin() {
		UserReq userReq = new UserReq(mLoginUsernameEt.getText().toString(), mLoginPwdEt.getText().toString());
		if (hasPermission) {
			userReq.setAppId(SystemUtils.getIMEI(this));
			userReq.setAppVersion(SystemUtils.getAppVersion(this));
			userReq.setDeviceModel(SystemUtils.getDeviceModel());
			userReq.setDeviceSystem(SystemUtils.getDeviceSystem());
			userReq.setDeviceVersion(SystemUtils.getDeviceSystemVersion());
		}
		requestLogin(userReq);
	}


	private void requestLogin(UserReq userReq) {
		userService.requestLogin(userReq)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(LoginActivity.this, result -> {
					if (result.getCode().equals(Constant.LOGIN_STATUS_SUCCESS) || Constant.LOGIN_STATUS_CLOSE.equals(result.getCode())) {
						User user = result.getData();
						user.setCode(result.getCode());
						user.setPassword(userReq.getPassword());
						SpUtils.getInstance().saveUser(user);
						startActivity(new Intent(LoginActivity.this, MainActivity.class));
						finish();
					} else if (Constant.LOGIN_STATUS_PWD_ERROR.equals(result.getCode())) {
						ToastUtil.showMessage(LoginActivity.this, result.getMessage());
					} else if (Constant.LOGIN_STATUS_NO_USER.equals(result.getCode())) {
						ToastUtil.showMessage(LoginActivity.this, result.getMessage());
					} else if (Constant.LOGIN_STATUS_UNAUTHORIZED.equals(result.getCode())) {
						User user = result.getData();
						user.setCode(result.getCode());
						user.setPassword(userReq.getPassword());
						SpUtils.getInstance().saveUser(user);
						new SweetAlertDialog(LoginActivity.this)
								.setTitleText("提示")
								.setContentText("您还未认证医师，是否现在去认证？")
								.setConfirmText("确认")
								.setCancelText("取消")
								.setConfirmClickListener(sweetAlertDialog -> {
									Intent intent = new Intent(LoginActivity.this, DoctorVerify1Activity.class);
									intent.putExtra("token", result.getData().getToken());
									startActivity(intent);
									sweetAlertDialog.dismiss();
								})
								.setCancelClickListener(SweetAlertDialog::dismiss).show();
					} else if (Constant.LOGIN_STATUS_COMMITED.equals(result.getCode())) {
						Intent intent = new Intent(LoginActivity.this, DoctorVerify2Activity.class);
						intent.putExtra("status", Constant.VERIFY_STATUS_ING);
						startActivity(intent);
					} else if (Constant.LOGIN_STATUS_UNPASSED.equals(result.getCode())) {
						Intent intent = new Intent(LoginActivity.this, DoctorVerify2Activity.class);
						intent.putExtra("status", Constant.VERIFY_STATUS_UNPASSED);
						intent.putExtra("token", result.getData().getToken());
						startActivity(intent);
					}
				}, false));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_REGISTER) {
			if (resultCode == RESULT_OK) {

			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == 100) {
			hasPermission = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
			if (user != null && !TextUtils.isEmpty(user.getUsername()) && !TextUtils.isEmpty(user.getPassword())) {
				mLoginUsernameEt.setText(user.getUsername());
				mLoginPwdEt.setText(user.getPassword());
				mLoginConfirmBt.performClick();
			}
		}
	}
}
