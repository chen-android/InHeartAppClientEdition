package com.medvision.vruser.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.medvision.vruser.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserInfoCompleteActivity extends AppCompatActivity {

	@InjectView(R.id.user_info_complete_username_et)
	EditText mUserInfoCompleteUsernameEt;
	@InjectView(R.id.user_info_complete_id_et)
	EditText mUserInfoCompleteIdEt;
	@InjectView(R.id.user_info_complete_age_et)
	EditText mUserInfoCompleteAgeEt;
	@InjectView(R.id.user_info_complete_emergency_contact_et)
	EditText mUserInfoCompleteEmergencyContactEt;
	@InjectView(R.id.user_info_complete_emergency_phone_et)
	EditText mUserInfoCompleteEmergencyPhoneEt;
	@InjectView(R.id.user_info_complete_address_et)
	EditText mUserInfoCompleteAddressEt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_complete);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setTitle(getString(R.string.title_user_info)).setBack().setRight(getString(R.string.save), v -> saveInfo());
	}

	private void saveInfo() {

	}
}
