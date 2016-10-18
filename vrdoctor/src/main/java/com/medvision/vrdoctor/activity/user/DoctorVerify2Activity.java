package com.medvision.vrdoctor.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.utils.Constant;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 验证结果界面
 */
public class DoctorVerify2Activity extends AppCompatActivity {

	@InjectView(R.id.doctor_verify_status_iv)
	ImageView mDoctorVerifyStatusIv;
	@InjectView(R.id.doctor_verify_status_tv)
	TextView mDoctorVerifyStatusTv;
	@InjectView(R.id.doctor_verify_upload_bt)
	Button mDoctorVerifyUploadBt;

	private String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_verify2);
		ButterKnife.inject(this);
		int status = getIntent().getIntExtra("status", -1);
		token = getIntent().getStringExtra("token");
		if (status == Constant.VERIFY_STATUS_ING) {
			Navigation.getInstance(this).setTitle("等待审核").setBack();
			mDoctorVerifyStatusIv.setImageResource(R.drawable.icon_verify_ing);
			mDoctorVerifyStatusTv.setText("已提交审核材料\n请耐心等待");
		} else if (status == Constant.VERIFY_STATUS_UNPASSED) {
			Navigation.getInstance(this).setTitle("认证失败").setBack();
			mDoctorVerifyStatusIv.setImageResource(R.drawable.icon_verify_fail);
			mDoctorVerifyStatusTv.setText("认证失败");
			mDoctorVerifyUploadBt.setVisibility(View.VISIBLE);
		}
	}

	@OnClick(R.id.doctor_verify_upload_bt)
	public void onClick() {
		Intent intent = new Intent(this, DoctorVerify1Activity.class);
		intent.putExtra("token", token);
		startActivity(intent);
		finish();
	}
}
