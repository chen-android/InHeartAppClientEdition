package com.medvision.vrdoctor.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cs.widget.tab.PagerSlidingTabStrip1;
import com.cs.widget.tab.TextImageRes;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.fragment.ConsultationFragment;
import com.medvision.vrdoctor.fragment.ContentFragment;
import com.medvision.vrdoctor.fragment.MineFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

	@InjectView(R.id.main_vp)
	ViewPager mMainVp;
	@InjectView(R.id.main_psts)
	PagerSlidingTabStrip1 mMainPsts;

	private ArrayList<TextImageRes> tabs = new ArrayList<>();
	private Fragment[] fragments = {
			ContentFragment.newInstance(),
			ConsultationFragment.newInstance(),
			MineFragment.newInstance()
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
		tabs.add(new TextImageRes("问诊", R.drawable.icon_ask_selector, R.color.color_blue_black_checked));
		tabs.add(new TextImageRes("内容", R.drawable.icon_content_selector, R.color.color_blue_black_checked));
		tabs.add(new TextImageRes("个人", R.drawable.icon_personal_selector, R.color.color_blue_black_checked));
		mMainVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		mMainPsts.setViewPager(mMainVp);
	}

	private class MyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip1.IconTabProvider {


		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragments[position];
		}

		@Override
		public int getCount() {
			return fragments.length;
		}

		@Override
		public TextImageRes getPageRes(int position) {
			return tabs.get(position);
		}
	}
}
