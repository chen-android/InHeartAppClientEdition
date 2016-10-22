package com.medvision.vrdoctor.view.popupwindow;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.medvision.vrdoctor.R;

/**
 * Created by cs on 2016/10/13.
 */

public class PopupUtils {
	private PopupWindow pw;
	private Activity context;
	View rootView;
	RelativeLayout backgroundView;
	FrameLayout contentView;
	View otherView;
	private int duration = 300;

	public PopupUtils(Activity context) {
		this.context = context;
		rootView = View.inflate(context, R.layout.popup_list, null);
		backgroundView = (RelativeLayout) rootView.findViewById(R.id.popup_bg_rl);
		contentView = (FrameLayout) rootView.findViewById(R.id.popup_list_content_fl);
		otherView = rootView.findViewById(R.id.popup_list_other_view);
		pw = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
		otherView.setOnClickListener(v -> dismissPopup());
	}

	public PopupUtils setContentView(View view) {
		contentView.removeAllViews();
		contentView.addView(view);
		return this;
	}

	public void show() {
		if (pw != null) {
			contentView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_from_right));
			backgroundView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_0to1));
			pw.showAtLocation(context.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, 0);
		}
	}

	public boolean isShowing() {
		if (pw != null) {
			return pw.isShowing();
		}
		return false;
	}

	public void dismissPopup() {
		if (pw != null) {
			Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_from_right);
			contentView.startAnimation(animation);
			backgroundView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_1to0));
			animation.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					pw.dismiss();
				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}
			});
		}
	}
}
