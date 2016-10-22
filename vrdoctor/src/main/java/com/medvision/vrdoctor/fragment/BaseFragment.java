package com.medvision.vrdoctor.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by cs on 2016/10/21.
 */

public abstract class BaseFragment extends Fragment {
	public boolean onBackPressed() {
		return false;
	}
}
