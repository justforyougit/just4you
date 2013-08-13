package com.adi.justforyou;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;

public class SplashScreenActivity extends FragmentActivity implements
		AnimationListener {

	private ViewFlipper mFlipper;
	private AnimationDrawable mAnim;
	private int viewCount = 0;
	private ImageView mSplash1, mSplash2, mSplash3;

	// private Handler mHandler = new Handler() {
	// public void handleMessage(android.os.Message msg) {
	//
	// startAccountsActivity();
	// };
	// };

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		NotificationManager nMgr = (NotificationManager) getSystemService("notification");
	    nMgr.cancelAll();
		// Toast.makeText(this,"MEMORY "+Runtime.getRuntime().freeMemory(),
		// Toast.LENGTH_LONG).show();

		// setDeviceOrientation();
		lockToPortrait(this);
		setContentView(R.layout.splash_screen_layout);
		mFlipper = (ViewFlipper) findViewById(R.id.splash_screen_image);
		mSplash1 = (ImageView) findViewById(R.id.splash1);
		mSplash2 = (ImageView) findViewById(R.id.splash2);
		mSplash3 = (ImageView) findViewById(R.id.splash3);
		mSplash1.setImageResource(R.drawable.image_one);
		mSplash2.setImageResource(R.drawable.image_two);
		mSplash3.setImageResource(R.drawable.image_one);
		Animation animation = AnimationUtils.loadAnimation(this,
				R.anim.flipper_in);
		mFlipper.setInAnimation(animation);
		animation.setAnimationListener(this);
		mFlipper.setOutAnimation(this, R.anim.flipper_out);
		mFlipper.startFlipping();

		/*
		 * AlphaAnimation inAlpha = new AlphaAnimation(fromAlpha, toAlpha)
		 */
	}

	private void startMainActivity() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	public static void lockToPortrait(Activity actity) {

		actity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

	}

	// private void setDeviceOrientation() {
	// if (Utility.isTablet(this)) {
	// Utility.lockToLandscape(this);
	// } else {
	// Utility.lockToPortrait(this);
	// }
	// }

	public void onAnimationEnd(Animation animation) {
		viewCount++;
		if (viewCount == 3)
			startMainActivity();

	}

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	public void onAnimationStart(Animation animation) {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

}
