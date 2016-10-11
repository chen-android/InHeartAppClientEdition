package com.medvision.vrdoctor;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();

		assertEquals("com.medvision.vrdoctor", appContext.getPackageName());

		String os = Build.DEVICE;
		String version = Build.VERSION.RELEASE;
		String a = Build.DISPLAY;
		String b = Build.HARDWARE;
		String c = Build.HOST;
		String d = Build.ID;
		String e = Build.MODEL;
		String f = Build.PRODUCT;
		String g = Build.TYPE;
		String h = Build.USER;
		String i = Build.SERIAL;

		PackageManager manager = appContext.getPackageManager();
		PackageInfo packageInfo = manager.getPackageInfo(appContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
		String aa = packageInfo.versionName;
	}
}
