package com.medvision.vruser.utils;

import android.content.Context;

import com.cs.common.database.MyModulePreference;
import com.cs.common.database.SpDictionary;
import com.cs.common.database.SpUtilsBase;
import com.medvision.vruser.beans.User;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public class SpUtils extends SpUtilsBase {
	private static SpUtils instance;
	private MyModulePreference mAppPreferences;

	public SpUtils() {
	}

	public static SpUtils getInstance() {
		if (instance == null) {
			instance = new SpUtils();
			return instance;
		} else {
			return instance;
		}
	}

	public void init(Context context) {
		if (mAppPreferences == null) {
			mAppPreferences = new MyModulePreference(context, context.getPackageName());
		}
	}

	public User getUser() {
		Object module = getInstance().getModule(SpDictionary.SP_USER);
		if (module != null) {
			return (User) module;
		} else {
			Logger.wtf("用户信息为空");
			return null;
		}
	}

	public String getUserId() {
		String userId = "";
		Object module = getInstance().getModule(SpDictionary.SP_USER);
		if (module != null) {
//			userId = ((User) module).getUid();
		}
		return userId;
	}

	public void clearUserData() {
		getInstance().remove(SpDictionary.SP_USER);
	}
}
