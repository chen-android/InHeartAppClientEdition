package com.cs.widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cs on 16/10/8.
 */

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

	private static final int[] ATTR = {android.R.attr.listDivider};
	private Drawable divider;

	public DividerGridItemDecoration(Context context) {
		super();
		TypedArray a = context.obtainStyledAttributes(ATTR);
		divider = a.getDrawable(0);
		a.recycle();
	}

	public DividerGridItemDecoration setDividerDrawable(Drawable dividerDrawable) {
		this.divider = dividerDrawable;
		return this;
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
		drawHorizentalDivider(c, parent);
		drawVerticalDivider(c, parent);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		int spanCount = getSpanCount(parent);
		int childCount = parent.getAdapter().getItemCount();
		if (isLastColum(parent, parent.getChildAdapterPosition(view), spanCount, childCount)) {
			outRect.set(0, 0, 0, divider.getIntrinsicHeight());
		} else if (isLastRaw(parent, parent.getChildAdapterPosition(view), spanCount, childCount)) {
			outRect.set(0, 0, divider.getIntrinsicWidth(), 0);
		} else {
			outRect.set(0, 0, divider.getIntrinsicWidth(), divider.getIntrinsicHeight());
		}
	}

	private int getSpanCount(RecyclerView parent) {
		// 列数
		int spanCount = -1;
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
		}
		return spanCount;
	}

	private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
				return true;
		}
		return false;
	}

	private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
		RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {
			childCount = childCount - childCount % spanCount;
			if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
				return true;
		}
		return false;
	}

	private void drawHorizentalDivider(Canvas c, RecyclerView parent) {
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
			int left = child.getLeft() - layoutParams.leftMargin;
			int right = child.getRight() + layoutParams.rightMargin + divider.getIntrinsicWidth();
			int top = child.getBottom() + layoutParams.bottomMargin;
			int bottom = top + divider.getIntrinsicHeight();
			divider.setBounds(left, top, right, bottom);
			divider.draw(c);
		}
	}

	private void drawVerticalDivider(Canvas c, RecyclerView parent) {
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
			int left = child.getRight() + layoutParams.rightMargin;
			int right = left + divider.getIntrinsicWidth();
			int top = child.getTop() - layoutParams.topMargin;
			int bottom = child.getBottom() + layoutParams.bottomMargin;
			divider.setBounds(left, top, right, bottom);
			divider.draw(c);
		}
	}
}
