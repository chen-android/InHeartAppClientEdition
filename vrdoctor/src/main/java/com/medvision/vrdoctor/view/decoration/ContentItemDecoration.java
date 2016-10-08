package com.medvision.vrdoctor.view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cs on 16/10/8.
 */

public class ContentItemDecoration extends RecyclerView.ItemDecoration {

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		int count = parent.getChildCount();
		int position = parent.getChildAdapterPosition(view);
		if ((position + 1) % 2 == 0) {
			if (position < 2) {
				outRect.set(0, 20, 0, 20);
			} else if (position == (count - 1)) {//判断底部,不画分割
				outRect.set(0, 0, 0, 0);
			} else {
				outRect.set(0, 0, 0, 20);
			}
		} else {
			if (position < 2) {
				outRect.set(0, 20, 20, 20);
			} else if (position == (count - 1)) {//判断底部,不画分割
				outRect.set(0, 0, 0, 0);
			} else {
				outRect.set(0, 0, 20, 20);
			}
		}
	}


}
