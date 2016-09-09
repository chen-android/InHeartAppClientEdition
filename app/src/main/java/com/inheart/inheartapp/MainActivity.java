package com.inheart.inheartapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	@InjectView(R.id.main_fl)
	FrameLayout mMainFl;
	@InjectView(R.id.main_tab_content_iv)
	ImageButton mMainTabContentIv;
	@InjectView(R.id.main_tab_consultation_ib)
	ImageButton mMainTabConsultationIb;
	@InjectView(R.id.main_tab_message_ib)
	ImageButton mMainTabMessageIb;
	@InjectView(R.id.main_tab_mine_ib)
	ImageButton mMainTabMineIb;
	@InjectView(R.id.main_ll)
	LinearLayout mMainLl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
	}

	@OnClick({R.id.main_tab_content_iv, R.id.main_tab_consultation_ib, R.id.main_tab_message_ib, R.id.main_tab_mine_ib, R.id.main_ll})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.main_tab_content_iv:
				break;
			case R.id.main_tab_consultation_ib:
				break;
			case R.id.main_tab_message_ib:
				break;
			case R.id.main_tab_mine_ib:
				break;
			case R.id.main_ll:
				break;
		}
	}
}
