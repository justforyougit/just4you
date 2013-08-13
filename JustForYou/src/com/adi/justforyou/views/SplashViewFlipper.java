package com.adi.justforyou.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class SplashViewFlipper extends ViewFlipper {
	public SplashViewFlipper(Context context) {
		super(context);
	}

	public SplashViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDetachedFromWindow() {
		try {
			super.onDetachedFromWindow();
		} catch (IllegalArgumentException e) {
			stopFlipping();
		}
	}
}