package com.medvision.vruser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.medvision.vruser.activity.user.LoginActivity;
import com.medvision.vruser.database.SpUtils;

public class InitialActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);
		if (SpUtils.getInstance().getUser() != null) {
			startActivity(new Intent(this, Main2Activity.class));
		} else {
			startActivity(new Intent(this, LoginActivity.class));
		}
	}
}
