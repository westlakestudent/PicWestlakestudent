package com.westlakestudent.activity;

import java.util.List;

import com.westlakestudent.R;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.entity.ImageUrl;
import com.westlakestudent.net.UrlPicker;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.app.Activity;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	
	private UrlPicker picker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		picker = UrlPicker.getInstance();
		picker.registerHandler(UrlHandler);
		picker.pick(1);
	}

	
	private static final Handler UrlHandler = new Handler(){

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Constants.URL_DONE:
				List<ImageUrl> urls = (List<ImageUrl>) msg.obj;
				for(ImageUrl url : urls){
					Log.d(TAG, url.toString());
				}
				break;
			}
		}
		
	};
	
}
