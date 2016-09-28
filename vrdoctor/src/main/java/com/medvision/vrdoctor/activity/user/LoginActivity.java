package com.medvision.vrdoctor.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cs.common.utils.ToastUtil;
import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.requestbody.UserReq;
import com.medvision.vrdoctor.network.UserService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);
		userService = HttpMethods.getInstance().getClassInstance(UserService.class);
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
				requestLogin(new UserReq(mLoginUsernameEt.getText().toString(), mLoginPwdEt.getText().toString()));
				break;
		}
	}


	private void requestLogin(UserReq userReq) {
		userService.requestLogin(userReq)
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(LoginActivity.this, user -> {
					ToastUtil.showMessage(LoginActivity.this, "登陆成功");
				}, error -> {
					String[] eInfo = error.split("&");
					if (eInfo[0].equals("-4")) {

					}
				}));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_REGISTER) {
			if (resultCode == RESULT_OK) {

			}
		}
	}
}
