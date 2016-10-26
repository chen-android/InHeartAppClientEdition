package com.medvision.vrdoctor.activity.other;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cs.common.utils.RxBus;
import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.activity.user.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SettingActivity extends AppCompatActivity {

	@InjectView(R.id.setting_cache_tv)
	TextView mSettingCacheTv;
	@InjectView(R.id.setting_cache_rl)
	RelativeLayout mSettingCacheRl;
	@InjectView(R.id.setting_about_tv)
	TextView mSettingAboutTv;
	@InjectView(R.id.setting_logout)
	Button mSettingLogout;

	private RxBus mRxBus = RxBus.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_setting));
	}

	@OnClick({R.id.setting_cache_rl, R.id.setting_about_tv, R.id.setting_logout})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.setting_cache_rl:
				break;
			case R.id.setting_about_tv:
				break;
			case R.id.setting_logout:
				new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
						.setTitleText("提示")
						.setContentText("确定要退出？")
						.setConfirmText("退出")
						.setConfirmClickListener(sweetAlertDialog -> {
							mRxBus.send(new SubscriptionLogout());
							startActivity(new Intent(SettingActivity.this, LoginActivity.class));
							finish();
						}).setCancelText("取消")
						.setCancelClickListener(Dialog::dismiss)
						.show();
				break;
		}
	}

	public class SubscriptionLogout {
	}
}
