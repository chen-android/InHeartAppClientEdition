package com.medvision.vruser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cs.widget.tab.PagerSlidingTabStrip1;
import com.cs.widget.tab.TextImageRes;
import com.inheart.inheartapp.R;
import com.medvision.vruser.fragment.ConsultationFragment;
import com.medvision.vruser.fragment.ContentFragment;
import com.medvision.vruser.fragment.MessageFragment;
import com.medvision.vruser.fragment.MineFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Main2Activity extends AppCompatActivity {


	@InjectView(R.id.main2_vp)
	ViewPager mMain2Vp;
	@InjectView(R.id.main2_psts)
	PagerSlidingTabStrip1 mMain2Psts;

	private ArrayList<TextImageRes> tabs = new ArrayList<>();
	private Fragment[] fragments = {
			ContentFragment.newInstance("", ""),
			ConsultationFragment.newInstance(),
			MessageFragment.newInstance(),
			MineFragment.newInstance()
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		ButterKnife.inject(this);
		tabs.add(new TextImageRes("内容", R.drawable.icon_content_selector, R.color.color_blue_black_selector));
		tabs.add(new TextImageRes("问诊", R.drawable.icon_ask_selector, R.color.color_blue_black_selector));
		tabs.add(new TextImageRes("消息", R.drawable.icon_message_selector, R.color.color_blue_black_selector));
		tabs.add(new TextImageRes("个人", R.drawable.icon_personal_selector, R.color.color_blue_black_selector));
		mMain2Vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		mMain2Psts.setViewPager(mMain2Vp);
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
