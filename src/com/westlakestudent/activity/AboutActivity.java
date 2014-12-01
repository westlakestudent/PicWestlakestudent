package com.westlakestudent.activity;

import com.westlakestudent.ui.AboutLayout;

import android.app.Activity;
import android.os.Bundle;

/**
 *
 * AboutActivity
 * @author chendong
 * 2014年12月1日 上午10:53:46
 * @version 1.0.0
 *
 */
public class AboutActivity extends Activity {

	private AboutLayout layout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new AboutLayout(this);
		layout.setActivity(this);
		setContentView(layout);
	}
	

}
