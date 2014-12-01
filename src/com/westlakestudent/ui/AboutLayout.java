package com.westlakestudent.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.westlakestudent.R;
import com.westlakestudent.ui.TopOperateBar.BackListener;
import com.westlakestudent.util.ScaleUtil;

/**
 * 
 * AboutLayout
 * 
 * @author chendong 2014年12月1日 上午10:57:47
 * @version 1.0.0
 * 
 */
public class AboutLayout extends LinearLayout implements OnClickListener {

	private static final String TAG = "AboutLayout";
	private TopOperateBar topbar = null;

	private Activity activity = null;

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public AboutLayout(Context context) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
		setBackgroundColor(Color.WHITE);
		createUI(context);
	}

	private void createUI(Context context) {
		LayoutParams params = null;

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				ScaleUtil.scale(68));
		topbar = new TopOperateBar(context);
		topbar.setMenuRes(R.drawable.back_selector);
		topbar.setBackListener(backListener);
		topbar.setTitle("关于");
		addView(topbar, params);

		ImageView img = new ImageView(context);
		img.setBackgroundResource(R.drawable.logo);

		params = new LayoutParams(ScaleUtil.scale(68), ScaleUtil.scale(68));
		params.gravity = Gravity.CENTER_HORIZONTAL;
		params.topMargin = ScaleUtil.scale(60);
		addView(img, params);

		TextView text = new TextView(context);
		text.setTextColor(Color.BLACK);
		text.setTextSize(16);
		text.setText(R.string.about);
		text.setGravity(Gravity.CENTER);
		text.setPadding(15, 15, 15, 15);

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER_HORIZONTAL;
		params.topMargin = ScaleUtil.scale(50);
		addView(text, params);

		LinearLayout author = new LinearLayout(context);

		TextView name = new TextView(context);
		name.setText("作者 : ");
		name.setPadding(35, 0, 0, 0);
		name.setTextColor(Color.BLACK);
		name.setTextSize(16);
		name.setGravity(Gravity.CENTER);

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.topMargin = ScaleUtil.scale(260);
		author.addView(name, params);

		TextView emial = new TextView(context);
		emial.setTextColor(Color.RED);
		emial.setTextSize(16);
		emial.setGravity(Gravity.CENTER);
		emial.setText("westlakestudent@163.com");
		emial.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		emial.setClickable(true);
		emial.setOnClickListener(this);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.topMargin = ScaleUtil.scale(260);
		author.addView(emial, params);

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		addView(author, params);
	}

	private BackListener backListener = new BackListener() {

		@Override
		public void onBack() {
			if (activity != null)
				activity.finish();
		}
	};

	@Override
	public void onClick(View v) {
		Log.d(TAG, "emial click");
		String[] mailto = { "westlakestudent@163.com" };
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		String emailBody = "您想对我说些什么呢";
		sendIntent.setType("message/rfc822");
		sendIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, "给westlakestudent的反馈");
		sendIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
		activity.startActivity(sendIntent);
	}
}
