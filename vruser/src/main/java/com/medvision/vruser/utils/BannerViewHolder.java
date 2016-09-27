package com.medvision.vruser.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.medvision.vruser.R;

/**
 * Created by Administrator on 2016/9/24 0024.
 */

public class BannerViewHolder implements Holder {
	private ImageView iv;

	@Override
	public View createView(Context context) {
		iv = new ImageView(context);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		return iv;
	}

	@Override
	public void UpdateUI(Context context, int position, Object data) {
		iv.setImageResource(R.drawable.icon_banner);
	}
}
