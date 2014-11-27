package com.westlakestudent.ui;

import android.content.Context;

import com.westlakestudent.widget.DragContent;

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

	public OutterLayout(Context context) {
		super(context);
		createUI(context);
	}

	private void createUI(Context context) {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		
		mAllKindPicView = new AllKindPicView(context);
		addView(mAllKindPicView, params);
	}
}
