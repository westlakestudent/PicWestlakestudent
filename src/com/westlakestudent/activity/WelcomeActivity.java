package com.westlakestudent.activity;

import net.youmi.android.AdManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;

import com.westlakestudent.R;
import com.westlakestudent.constants.Constants;

/**
 * 
 * WelcomeActivity
 * 
 * @author chendong 2014年12月1日 上午10:25:41
 * @version 1.0.0
 * 
 */
public class WelcomeActivity extends Activity {

	private LinearLayout layout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AdManager.getInstance(this).init("50f8dffa1a26a917",
				"7376dc001fde5b43", false);

		layout = new LinearLayout(this);
		layout.setBackgroundResource(R.drawable.welcome);
		setContentView(layout);

		com.westlakestudent.util.ScaleUtil.scaleInit(this, 800, 480, 240);
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean started = pref.getBoolean(Constants.FIRST_STARTED, false);
		if (started) {
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					Intent intent = new Intent(WelcomeActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				}
			}, 3 * 1000);
		} else {
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					Intent intent = new Intent(WelcomeActivity.this,
							GuideActivity.class);
					startActivity(intent);
					finish();
				}
			}, 3 * 1000);
		}

	}

	static Handler handler = new Handler();
}
