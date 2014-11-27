package com.westlakestudent.activity;

import com.westlakestudent.ui.MainContentView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private MainContentView MainView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		
		com.westlakestudent.util.ScaleUtil.scaleInit(this, 800, 480, 240);
		MainView = new MainContentView(this);
		setContentView(MainView);

	}

}
