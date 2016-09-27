package com.medvision.vrdoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.activity.user.LoginActivity;
import com.medvision.vrdoctor.utils.SpUtils;

public class InitalActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inital);
		if (SpUtils.getInstance().getUser() != null) {
			startActivity(new Intent(this, MainActivity.class));
		} else {
			startActivity(new Intent(this, LoginActivity.class));
		}
	}
}
