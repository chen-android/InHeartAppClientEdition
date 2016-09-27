package com.medvision.vrdoctor.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cs.widget.tab.PagerSlidingTabStrip1;
import com.medvision.vrdoctor.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

	@InjectView(R.id.main_vp)
	ViewPager mMainVp;
	@InjectView(R.id.main_psts)
	PagerSlidingTabStrip1 mMainPsts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
	}
}
