package com.westlakestudent.activity;

import com.westlakestudent.R;
import com.westlakestudent.ui.MainContentView;
import com.westlakestudent.widget.dialog.Effectstype;
import com.westlakestudent.widget.dialog.NiftyDialogBuilder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private MainContentView MainView = null;
	private NiftyDialogBuilder dialogBuilder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");

		com.westlakestudent.util.ScaleUtil.scaleInit(this, 800, 480, 240);
		MainView = new MainContentView(this, MainActivity.this);
		setContentView(MainView);

		dialogBuilder = NiftyDialogBuilder.getInstance(this);
		dialogBuilder.withTitle("提示").withMessage("真的要退出吗?");

	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		dialogBuilder.withEffect(Effectstype.Slidetop)
				.withTitleColor("#FFFFFF").withDividerColor("#11000000")
				.withMessageColor("#FFFFFFFF").withDialogColor("#66B3FF")
				.withIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.withDuration(200).isCancelableOnTouchOutside(true)
				.setButton1Click(confirm).setButton2Click(cancel)
				.withButton1Text("确定").withButton2Text("取消");
		dialogBuilder.show();
	}

	private OnClickListener cancel = new OnClickListener() {

		@Override
		public void onClick(View v) {
			dialogBuilder.dismiss();
		}
	};

	private OnClickListener confirm = new OnClickListener() {

		@Override
		public void onClick(View v) {
			dialogBuilder.dismiss();
			MainActivity.this.finish();
		}
	};

}
