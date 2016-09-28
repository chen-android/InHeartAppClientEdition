package com.medvision.vruser.activity.consultation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cs.widget.utils.Navigation;
import com.medvision.vruser.R;

public class ExpertDetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expert_detail);
		Navigation.getInstance(this).setTitle("专家详情").setBack();
	}
}
