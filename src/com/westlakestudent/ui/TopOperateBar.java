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
import com.westlakestudent.util.WestlakestudentToast;
import com.westlakestudent.widget.DragLayout;

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

	private DragLayout drag = null;
	
	private BackListener backListener = null;
	
	private Context context = null;

	public TopOperateBar(Context context) {
		super(context);
		this.context = context;
		Log.d(TAG, "createUI");
		setBackgroundColor(getResources().getColor(color.topbg));
		createUI(context);
	}

	public void setDrag(DragLayout drag) {
		this.drag = drag;
	}
	
	public void setBackListener(BackListener l){
		backListener = l;
	}

	public void setMenuRes(int res) {
		if (menu == null)
			return;
		menuRes = res;
		menu.setBackgroundResource(res);
	}
	
	public void setTitle(String str){
		if(title != null){
			title.setText(str);
			if(!str.equals("预览")){
				title.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
				title.setClickable(true);
			}else{
				title.setClickable(false);
			}
		}
			
	}

	private void createUI(Context context) {
		LayoutParams params = null;

		menu = new Button(context);
		menu.setBackgroundResource(menuRes);                                           
		menu.setOnClickListener(this);
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
		title.setTextColor(getResources().getColor(color.withe));
		title.setTextSize(20);
		title.setOnClickListener(this);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		addView(title, params);

	}

	@Override
	public void onClick(View v) {
		if (v == title) {
			WestlakestudentToast.toast(context, "ceshi");
		} else if (v == menu ) {
			if(menuRes == R.drawable.menu_selector){
				if (drag != null)
					drag.open();
			}else if(menuRes == R.drawable.back_selector){
				if(backListener != null)
					backListener.onBack();
			}
			
		}
	}
	
	
	public interface BackListener{
		void onBack();
	}

}
