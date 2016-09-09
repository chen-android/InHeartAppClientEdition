package com.inheart.inheartapp.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inheart.inheartapp.R;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);
	}
}
