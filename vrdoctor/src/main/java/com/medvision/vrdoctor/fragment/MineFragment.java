package com.medvision.vrdoctor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.activity.content.VrVideoActivity;
import com.medvision.vrdoctor.activity.other.SettingActivity;
import com.medvision.vrdoctor.activity.user.CollectionActivity;
import com.medvision.vrdoctor.activity.user.UserInfoCompleteActivity;
import com.medvision.vrdoctor.beans.User;
import com.medvision.vrdoctor.utils.SpUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


	@InjectView(R.id.mine_personal_iv)
	ImageView mMinePersonalIv;
	@InjectView(R.id.mine_name_tv)
	TextView mMineNameTv;
	@InjectView(R.id.mine_phone_tv)
	TextView mMinePhoneTv;
	@InjectView(R.id.mine_personal_rl)
	RelativeLayout mMinePersonalRl;
	@InjectView(R.id.mine_my_ask_tv)
	TextView mMineMyAskTv;
	@InjectView(R.id.mine_my_collection_tv)
	TextView mMineMyCollectionTv;
	@InjectView(R.id.mine_my_ask_setting_tv)
	TextView mMineMyAskSettingTv;
	@InjectView(R.id.mine_setting_tv)
	TextView mMineSettingTv;

	private User mUser;

	public MineFragment() {
		mUser = SpUtils.getInstance().getUser();
	}

	public static MineFragment newInstance() {
		return new MineFragment();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mine, container, false);
		ButterKnife.inject(this, view);
		if (mUser != null) {
			mMineNameTv.setText(mUser.getUsername());
		}
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@OnClick({R.id.mine_personal_rl, R.id.mine_my_ask_tv, R.id.mine_my_collection_tv, R.id.mine_my_ask_setting_tv, R.id.mine_setting_tv})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.mine_personal_rl:
				startActivity(new Intent(getActivity(), UserInfoCompleteActivity.class));
				break;
			case R.id.mine_my_ask_tv:
				break;
			case R.id.mine_my_collection_tv:
				startActivity(new Intent(getActivity(), CollectionActivity.class));
				break;
			case R.id.mine_my_ask_setting_tv:
				startActivity(new Intent(getActivity(), VrVideoActivity.class));
				break;
			case R.id.mine_setting_tv:
				startActivity(new Intent(getActivity(), SettingActivity.class));
				break;
		}
	}
}
