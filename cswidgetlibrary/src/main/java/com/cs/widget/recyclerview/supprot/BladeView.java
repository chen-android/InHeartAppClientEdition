package com.cs.widget.recyclerview.supprot;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cs.widgetlibrary.R;

/**
 * Created by cs on 16/9/13.
 */
public class BladeView extends View {

	private OnItemClickListener onItemClickListener;
	private String[] list = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private int choose = -1;
	private Paint paint;
	private PopupWindow pw;
	private TextView pwTv;
	private Handler handler = new Handler();
	private boolean showBg = false;
	private int textColor = Color.parseColor("#1e90ff");

	public BladeView(Context context) {
		super(context);
	}

	public BladeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		int[] ATTR = {android.R.attr.textColor};
		TypedArray a = context.obtainStyledAttributes(attrs, ATTR);
		textColor = a.getColor(0, textColor);
		a.recycle();
		paint = new Paint();
		paint.setColor(textColor);
		paint.setAntiAlias(true);
		paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics()));
	}

	public BladeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public interface OnItemClickListener {
		void click(String s);
	}

	public void setList(String[] list) {
		this.list = list;
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width = widthSize;
		int height = 0;

		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			Paint.FontMetrics fontMetrics = paint.getFontMetrics();
			height += (fontMetrics.bottom - fontMetrics.top) * list.length + getPaddingTop() + getPaddingBottom();
		}
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (showBg) {
			canvas.drawColor(Color.parseColor("#00000000"));
		}
		int height = getHeight();
		int width = getWidth();
		int singleHeight = height / list.length;

		for (int i = 0; i < list.length; i++) {
			float xPos = (width - paint.measureText(list[i])) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(list[i], xPos, yPos, paint);
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float y = event.getY();
		int oldChoose = choose;
		int c = (int) (y / getHeight() * list.length);
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				showBg = true;
				if (oldChoose != c) {
					if (c >= 0 && c < list.length) {
						performItemClick(c);
						choose = c;
						invalidate();
					}
				}
				break;
			case MotionEvent.ACTION_MOVE:
				showBg = true;
				if (oldChoose != c) {
					if (c >= 0 && c < list.length) {
						performItemClick(c);
						choose = c;
						invalidate();
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				showBg = false;
				choose = -1;
				dismissPopup();
				invalidate();
				break;
		}
		return true;
	}

	private void performItemClick(int item) {
		if (onItemClickListener != null) {
			onItemClickListener.click(list[item]);
			showPopup(item);
		}
	}

	private void showPopup(int item) {
		if (pw == null) {
			handler.removeCallbacks(runnable);
			pwTv = new TextView(getContext());
			pwTv.setBackgroundResource(R.drawable.shape_bg_popup_text);
			pwTv.setTextColor(Color.WHITE);
			pwTv.setTextSize(30);
			pwTv.setGravity(Gravity.CENTER);
			pw = new PopupWindow(pwTv, 140, 160);
		}
		pwTv.setText(list[item]);
		if (pw.isShowing()) {
			pw.update();
		} else {
			pw.showAtLocation(getRootView(), Gravity.CENTER, 0, 0);
		}

	}

	private void dismissPopup() {
		handler.postDelayed(runnable, 600);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (pw != null) {
				pw.dismiss();
			}
		}
	};
}
