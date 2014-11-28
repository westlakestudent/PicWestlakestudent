package com.westlakestudent.ui;

import android.app.Activity;
import android.content.Context;

import com.westlakestudent.widget.DragContent;
import com.westlakestudent.widget.DragLayout;

/**
 * 
 * OutterLayout
 * 
 * @author chendong 2014年11月27日 下午4:42:51
 * @version 1.0.0
 * 
 */
public class OutterLayout extends DragContent {

	private AllKindPicView mAllKindPicView = null;

	public OutterLayout(Context context,DragLayout drag,Activity activity) {
		super(context);
		createUI(context,drag,activity);
	}

	private void createUI(Context context,DragLayout drag,Activity activity) {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		
		mAllKindPicView = new AllKindPicView(context,drag);
		mAllKindPicView.setActivity(activity);
		addView(mAllKindPicView, params);
	}
}
