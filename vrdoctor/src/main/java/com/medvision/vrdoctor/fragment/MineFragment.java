package com.medvision.vrdoctor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medvision.vrdoctor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {


	public MineFragment() {
	}

	public static MineFragment newInstance() {
		return new MineFragment();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_mine, container, false);
	}

}
