package com.westlakestudent.activity;

import java.util.ArrayList;

import com.westlakestudent.constants.Constants;
import com.westlakestudent.ui.ImageShow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

/**
 *
 * ImageActivity
 * @author chendong
 * 2014年11月28日 上午11:03:56
 * @version 1.0.0
 *
 */
public class ImageActivity extends Activity {
	private static final String TAG = "ImageActivity";
	
	private ImageShow show = null;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		
		if(bundle == null){
			Log.d(TAG, "bundle = null");
			TextView error = new TextView(this);
			error.setGravity(Gravity.CENTER);
			error.setText("请返回");
			setContentView(error);
			return;
		}
		int selected = bundle.getInt(Constants.SELECTED);
		ArrayList<String> data = (ArrayList<String>) bundle.get(Constants.DATA);
		show = new ImageShow(this, data, selected);
		show.setActivity(ImageActivity.this);
		
		setContentView(show);
	}
	

}
