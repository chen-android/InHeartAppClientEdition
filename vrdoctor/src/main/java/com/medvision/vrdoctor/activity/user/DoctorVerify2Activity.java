package com.medvision.vrdoctor.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.utils.Constant;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DoctorVerify2Activity extends AppCompatActivity {

	@InjectView(R.id.doctor_verify_status_iv)
	ImageView mDoctorVerifyStatusIv;
	@InjectView(R.id.doctor_verify_status_tv)
	TextView mDoctorVerifyStatusTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_verify2);
		ButterKnife.inject(this);
		int status = getIntent().getIntExtra("status", -1);
		if (status == Constant.VERIFY_STATUS_ING) {
			Navigation.getInstance(this).setTitle("等待审核").setBack();
			mDoctorVerifyStatusIv.setImageResource(R.drawable.icon_verify_fail);
			mDoctorVerifyStatusTv.setText("已提交审核材料\n请耐心等待");
		} else if (status == Constant.VERIFY_STATUS_UNPASSED) {
			Navigation.getInstance(this).setTitle("认证失败").setBack();
			mDoctorVerifyStatusIv.setImageResource(R.drawable.icon_verify_fail);
			mDoctorVerifyStatusTv.setText("认证失败");
		}
	}
}
