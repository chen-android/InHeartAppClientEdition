package com.medvision.vrdoctor.application;

import android.app.Application;

import com.cs.networklibrary.util.PropertiesUtil;
import com.medvision.vrdoctor.utils.SpUtils;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SpUtils.getInstance().init(this);
		PropertiesUtil.init(this);
	}
}
