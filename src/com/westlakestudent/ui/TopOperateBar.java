package com.westlakestudent.ui;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.westlakestudent.R;
import com.westlakestudent.R.color;
import com.westlakestudent.util.ScaleUtil;

/**
 * 
 * TopOperateBar
 * 
 * @author chendong 2014年11月27日 下午2:28:09
 * @version 1.0.0
 * 
 */
public class TopOperateBar extends LinearLayout implements OnClickListener {
	private static final String TAG = "TopOperateBar";

	private Button menu = null;

	private TextView title = null;

	private ImageView line = null;
	
	private int menuRes = R.drawable.menu_selector;

	public TopOperateBar(Context context) {
		super(context);
		Log.d(TAG, "createUI");
		setBackgroundColor(getResources().getColor(color.topbg));
		createUI(context);
	}

	public void setMenuRes(int res) {
		if(menu == null)
			return;
		menu.setBackgroundResource(res);
	}

	private void createUI(Context context) {
		LayoutParams params = null;

		menu = new Button(context);
		menu.setBackgroundResource(menuRes);
		params = new LayoutParams(ScaleUtil.scale(68), ScaleUtil.scale(68));
		params.gravity = Gravity.CENTER_VERTICAL;
		addView(menu, params);

		line = new ImageView(context);
		line.setBackgroundResource(R.drawable.line);

		params = new LayoutParams(ScaleUtil.scale(2), ScaleUtil.scale(68));
		params.leftMargin = ScaleUtil.scale(1);
		addView(line, params);

		title = new TextView(context);
		title.setGravity(Gravity.CENTER);
		title.setText("图片");
		title.setTextColor(getResources().getColor(color.withe));
		title.setTextSize(20);
		title.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		addView(title, params);

	}

	@Override
	public void onClick(View v) {
		if (v == title) {

		} else if (v == menu) {

		}
	}

}
