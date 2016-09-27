package com.medvision.vrdoctor.activity.user;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.medvision.vrdoctor.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

	@InjectView(R.id.register_username_et)
	EditText mRegisterUsernameEt;
	@InjectView(R.id.register_send_sms_bt)
	Button mRegisterSendSmsBt;
	@InjectView(R.id.register_sms_et)
	EditText mRegisterSmsEt;
	@InjectView(R.id.register_nickname_et)
	EditText mRegisterNicknameEt;
	@InjectView(R.id.register_password_et)
	EditText mRegisterPasswordEt;
	@InjectView(R.id.register_submit_bt)
	Button mRegisterSubmitBt;
	@InjectView(R.id.register_user_protocol_tv)
	TextView mRegisterUserProtocolTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ButterKnife.inject(this);
		mRegisterUserProtocolTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
	}

	@OnClick({R.id.register_send_sms_bt, R.id.register_submit_bt, R.id.register_user_protocol_tv})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.register_send_sms_bt:
				break;
			case R.id.register_submit_bt:
				break;
			case R.id.register_user_protocol_tv:
				break;
		}
	}
}
