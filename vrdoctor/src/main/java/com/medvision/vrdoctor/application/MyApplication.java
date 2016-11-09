package com.medvision.vrdoctor.application;

import android.support.multidex.MultiDexApplication;

import com.cs.networklibrary.util.PropertiesUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.medvision.vrdoctor.utils.SpUtils;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public class MyApplication extends MultiDexApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		SpUtils.getInstance().init(this);
		PropertiesUtil.init(this);
		EMOptions options = new EMOptions();
		// 默认添加好友时，是不需要验证的，改成需要验证
		options.setAcceptInvitationAlways(false);
		EaseUI.getInstance().init(this, options);
		EMClient.getInstance().setDebugMode(true);
	}
}
