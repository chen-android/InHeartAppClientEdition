package com.medvision.vrdoctor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medvision.vrdoctor.R;

/**
 *
 */
public class ConsultationFragment extends Fragment {


	public ConsultationFragment() {
	}

	public static ConsultationFragment newInstance() {
		return new ConsultationFragment();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_consultation, container, false);
	}

}
