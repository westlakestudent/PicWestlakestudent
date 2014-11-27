package com.westlakestudent.activity;

import com.westlakestudent.ui.AllKindPicView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private AllKindPicView mAllKindPicView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		
		com.westlakestudent.util.ScaleUtil.scaleInit(this, 800, 480, 240);
		mAllKindPicView = new AllKindPicView(this);
		setContentView(mAllKindPicView);

	}

}
