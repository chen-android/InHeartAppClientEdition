package com.medvision.vruser.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cs.widget.utils.Navigation;
import com.medvision.vruser.R;

import butterknife.ButterKnife;

public class UserInfoCompleteActivity extends AppCompatActivity {

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
