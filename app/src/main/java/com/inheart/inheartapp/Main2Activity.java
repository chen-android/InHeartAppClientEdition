package com.inheart.inheartapp;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;

import com.inheart.inheartapp.fragment.ConsultationFragment;
import com.inheart.inheartapp.fragment.HomeFragment;
import com.inheart.inheartapp.fragment.MessageFragment;
import com.inheart.inheartapp.fragment.MineFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Main2Activity extends AppCompatActivity {

	@InjectView(R.id.main2_pts)
	PagerTabStrip mMain2Pts;
	@InjectView(R.id.main2_vp)
	ViewPager mMain2Vp;

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
			SpannableStringBuilder ssb = new SpannableStringBuilder("  " + titles.get(position)); // space added before text
			// for
			Drawable myDrawable = getResources().getDrawable(
					R.drawable.ic_launcher);
			myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(),
					myDrawable.getIntrinsicHeight());
			ImageSpan span = new ImageSpan(myDrawable,
					ImageSpan.ALIGN_BOTTOM);

			ForegroundColorSpan fcs = new ForegroundColorSpan(Color.GREEN);// 字体颜色设置为绿色
			ssb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置图标
			ssb.setSpan(fcs, 1, ssb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置字体颜色
			ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			return ssb;
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
