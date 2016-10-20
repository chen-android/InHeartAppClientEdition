package com.medvision.vrdoctor.activity.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

	@InjectView(R.id.setting_cache_tv)
	TextView mSettingCacheTv;
	@InjectView(R.id.setting_cache_rl)
	RelativeLayout mSettingCacheRl;
	@InjectView(R.id.setting_about_tv)
	TextView mSettingAboutTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_setting));
	}

	@OnClick({R.id.setting_cache_rl, R.id.setting_about_tv})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.setting_cache_rl:
				break;
			case R.id.setting_about_tv:
				break;
		}
	}
}
