package com.medvision.vrdoctor.activity.user;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cs.common.utils.ToastUtil;
import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.requestbody.RegisterReq;
import com.medvision.vrdoctor.beans.requestbody.SmsReq;
import com.medvision.vrdoctor.network.UserService;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

	@InjectView(R.id.register_username_et)
	EditText mRegisterUsernameEt;
	@InjectView(R.id.register_send_sms_bt)
	Button mRegisterSendSmsBt;
	@InjectView(R.id.register_sms_et)
	EditText mRegisterSmsEt;
	@InjectView(R.id.register_pwd_et)
	EditText mRegisterPwdEt;
	@InjectView(R.id.register_confirm_pwd_et)
	EditText mRegisterConfirmPwdEt;
	@InjectView(R.id.register_submit_bt)
	Button mRegisterSubmitBt;
	@InjectView(R.id.register_user_protocol_tv)
	TextView mRegisterUserProtocolTv;

	private UserService userService;
	private String smsCode;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_register));
		mRegisterUserProtocolTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		userService = HttpMethods.getInstance().getClassInstance(UserService.class);
	}

	@OnClick({R.id.register_send_sms_bt, R.id.register_submit_bt, R.id.register_user_protocol_tv})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.register_send_sms_bt:
				String phone = mRegisterUsernameEt.getText().toString();
				if (phone.isEmpty() || phone.length() != 11) {
					mRegisterUsernameEt.setError("手机号不正确,请检查!");
					ToastUtil.showMessage(RegisterActivity.this, "手机号不正确,请检查!");
					return;
				}
				requestSms(new SmsReq(phone));
				break;
			case R.id.register_submit_bt:
				if (checkDataComplete()) {
					requestRegister(new RegisterReq(mRegisterUsernameEt.getText().toString(), mRegisterPwdEt.getText().toString(), mRegisterSmsEt.getText().toString()));
				}

				break;
			case R.id.register_user_protocol_tv:
				break;
		}
	}

	private void requestRegister(RegisterReq registerReq) {
		userService.requestRegister(registerReq)
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(RegisterActivity.this, o -> {
					new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE)
							.setTitleText("注册成功")
							.setConfirmText("确认")
							.setConfirmClickListener(sweetAlertDialog -> {
								Intent i = new Intent();
								i.putExtra("username", mRegisterUsernameEt.getText().toString());
								i.putExtra("password", mRegisterPwdEt.getText().toString());
								setResult(RESULT_OK, i);
								finish();
							})
							.show();

				}));
	}

	private void requestSms(SmsReq smsReq) {
		userService.requestSms(smsReq)
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(RegisterActivity.this, o -> {
					startTimer();
					smsCode = o;
				}));
	}

	private void startTimer() {
		mRegisterSendSmsBt.setTextColor(getResources().getColor(R.color.gray));
		mRegisterSendSmsBt.setText(String.format("获取验证码（%s）", "60"));
		mRegisterSendSmsBt.setEnabled(false);
		Observable.interval(1, TimeUnit.SECONDS).take(59)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<Long>() {
					@Override
					public void onCompleted() {
						mRegisterSendSmsBt.setText("获取验证码");
						mRegisterSendSmsBt.setTextColor(getResources().getColor(R.color.colorPrimary));
						mRegisterSendSmsBt.setEnabled(true);
					}

					@Override
					public void onError(Throwable e) {
					}

					@Override
					public void onNext(Long aLong) {
						mRegisterSendSmsBt.setText(String.format("获取验证码（%s）", (59l - aLong) + ""));
					}
				});
	}

	private boolean checkDataComplete() {
		boolean b = true;
		if (TextUtils.isEmpty(mRegisterUsernameEt.getText().toString())) {
			ToastUtil.showMessage(this, "手机号不能为空");
			b = false;
		}
		if (TextUtils.isEmpty(mRegisterSmsEt.getText().toString())) {
			ToastUtil.showMessage(this, "验证码不能为空");
			b = false;
		}
		if (!smsCode.isEmpty() && !mRegisterSmsEt.getText().toString().equals(smsCode)) {
			ToastUtil.showMessage(this, "验证码不正确");
			b = false;
		}
		if (TextUtils.isEmpty(mRegisterPwdEt.getText().toString()) || mRegisterPwdEt.getText().toString().length() < 6) {
			ToastUtil.showMessage(this, "密码不能少于6位");
			b = false;
		}
		if (!mRegisterPwdEt.getText().toString().equals(mRegisterConfirmPwdEt.getText().toString())) {
			ToastUtil.showMessage(this, "两次密码输入不一致");
			b = false;
		}
		return b;
	}
}
