package com.inheart.inheartapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cs.widget.tab.PagerSlidingTabStrip;
import com.inheart.inheartapp.fragment.ConsultationFragment;
import com.inheart.inheartapp.fragment.HomeFragment;
import com.inheart.inheartapp.fragment.MessageFragment;
import com.inheart.inheartapp.fragment.MineFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Main2Activity extends AppCompatActivity {


	@InjectView(R.id.main2_vp)
	ViewPager mMain2Vp;
	@InjectView(R.id.main2_psts)
	PagerSlidingTabStrip mMain2Psts;

	private ArrayList<String> titles = new ArrayList<>();
	private Fragment[] fragments = {
			HomeFragment.newInstance("", ""),
			ConsultationFragment.newInstance("", ""),
			MessageFragment.newInstance("", ""),
			MineFragment.newInstance("", "")
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		ButterKnife.inject(this);
		titles.add("内容");
		titles.add("问诊");
		titles.add("消息");
		titles.add("个人");
		mMain2Vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {


		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "";
		}

		@Override
		public Fragment getItem(int position) {
			return fragments[position];
		}

		@Override
		public int getCount() {
			return fragments.length;
		}
	}

}
