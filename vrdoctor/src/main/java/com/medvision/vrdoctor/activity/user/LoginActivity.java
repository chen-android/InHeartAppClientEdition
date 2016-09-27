package com.medvision.vrdoctor.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.User;
import com.medvision.vrdoctor.beans.requestbody.UserReq;
import com.medvision.vrdoctor.network.UserService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

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

	@OnClick(R.id.login_confirm_bt)
	public void onClick() {
		requestLogin(new UserReq(mLoginUsernameEt.getText().toString(), mLoginPwdEt.getText().toString()));
	}

	private void requestLogin(UserReq userReq) {
		userService.requestLogin(userReq)
				.map(new HttpResultFunc<User>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<User>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onNext(User user) {
						Toast.makeText(LoginActivity.this, "login success " + user.getRealname(), Toast.LENGTH_SHORT).show();
					}
				});
	}
}
