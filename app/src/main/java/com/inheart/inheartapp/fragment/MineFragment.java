package com.inheart.inheartapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inheart.inheartapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 *
 */
public class MineFragment extends Fragment {


	@InjectView(R.id.mine_name_tv)
	TextView mMineNameTv;
	@InjectView(R.id.mine_phone_tv)
	TextView mMinePhoneTv;
	@InjectView(R.id.mine_personal_rl)
	RelativeLayout mMinePersonalRl;
	@InjectView(R.id.mine_my_ask_tv)
	TextView mMineMyAskTv;
	@InjectView(R.id.mine_my_doctor_tv)
	TextView mMineMyDoctorTv;
	@InjectView(R.id.mine_my_collection_tv)
	TextView mMineMyCollectionTv;
	@InjectView(R.id.mine_my_money_tv)
	TextView mMineMyMoneyTv;
	@InjectView(R.id.mine_help_feedback_tv)
	TextView mMineHelpFeedbackTv;
	@InjectView(R.id.mine_setting_tv)
	TextView mMineSettingTv;

	public static MineFragment newInstance() {
		return new MineFragment();
	}

	public MineFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mine, container, false);
		ButterKnife.inject(this, view);
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@OnClick({R.id.mine_personal_rl, R.id.mine_my_ask_tv, R.id.mine_my_doctor_tv, R.id.mine_my_collection_tv, R.id.mine_my_money_tv, R.id.mine_help_feedback_tv, R.id.mine_setting_tv})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.mine_personal_rl:
				break;
			case R.id.mine_my_ask_tv:
				break;
			case R.id.mine_my_doctor_tv:
				break;
			case R.id.mine_my_collection_tv:
				break;
			case R.id.mine_my_money_tv:
				break;
			case R.id.mine_help_feedback_tv:
				break;
			case R.id.mine_setting_tv:
				break;
		}
	}
}
