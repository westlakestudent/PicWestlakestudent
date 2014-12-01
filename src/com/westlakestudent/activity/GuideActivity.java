package com.westlakestudent.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.westlakestudent.R;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.util.ScaleUtil;

/**
 * 
 * WelcomeActivity
 * 
 * @author chendong 2014年12月1日 上午9:32:04
 * @version 1.0.0
 * 
 */
public class GuideActivity extends Activity {

	private ViewPager viewPager = null;

	private Button button = null;

	private List<LinearLayout> layouts = new ArrayList<LinearLayout>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUI();
		setContentView(viewPager);
	}

	private void initUI() {
		viewPager = new ViewPager(this);

		LinearLayout.LayoutParams Params = null;

		LinearLayout layout = new LinearLayout(this);
		Params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(Params);
		layout.setBackgroundResource(R.drawable.welcome1);
		layouts.add(layout);

		layout = new LinearLayout(this);
		Params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(Params);
		layout.setBackgroundResource(R.drawable.welcome2);
		layouts.add(layout);

		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		Params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(Params);
		layout.setBackgroundResource(R.drawable.welcome3);
		layouts.add(layout);

		button = new Button(this);
		button.setText("立即体验");
		button.setOnClickListener(listener);
		button.setBackgroundResource(R.drawable.welcome_selector);
		Params = new LinearLayout.LayoutParams(ScaleUtil.scale(160),
				ScaleUtil.scale(80));
		Params.gravity = Gravity.CENTER_HORIZONTAL;
		Params.topMargin = ScaleUtil.scale(640);
		layout.addView(button, Params);
		
		viewPager.setAdapter(new LayoutAdapter());
	}
	
	
	private class LayoutAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return layouts.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(layouts.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(layouts.get(position), 0);
			return layouts.get(position);
		}
		
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(GuideActivity.this,MainActivity.class);
			startActivity(intent);
			SharedPreferences pref = PreferenceManager
					.getDefaultSharedPreferences(GuideActivity.this);
			
			pref.edit().putBoolean(Constants.FIRST_STARTED, true).commit();
			finish();
		}
	};
}
