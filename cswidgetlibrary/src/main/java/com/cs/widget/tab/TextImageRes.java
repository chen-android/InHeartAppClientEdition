package com.cs.widget.tab;

/**
 * Created by cs on 16/9/22.
 */

public class TextImageRes {
	private String text;
	private int resId;
	private int textColorId;

	public TextImageRes() {
	}

	public TextImageRes(String text, int resId, int textColorId) {
		this.text = text;
		this.resId = resId;
		this.textColorId = textColorId;
	}

	String getText() {
		return text;
	}

	int getResId() {
		return resId;
	}

	int getTextColorId() {
		return textColorId;
	}
}
