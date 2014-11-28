package com.westlakestudent.ui;

import com.westlakestudent.R;
import com.westlakestudent.widget.DragLayout;

import android.app.Activity;
import android.content.Context;

/**
 * 
 * MainContentView
 * 
 * @author chendong 2014年11月27日 下午4:38:29
 * @version 1.0.0
 * 
 */
public class MainContentView extends DragLayout {

	private InnerLayout innerLayout = null;

	private OutterLayout outterLayout = null;

	public MainContentView(Context context,Activity activity) {
		super(context);
		createUI(context,activity);
		setDragListener(mDragListener);
		init(innerLayout, outterLayout);
		setBackgroundColor(getResources().getColor(R.color.innerbg));
	}

	private void createUI(Context context,Activity activity) {
		LayoutParams params = null;

		innerLayout = new InnerLayout(context);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		innerLayout.setPadding(30, 50, 0, 30);
		addView(innerLayout, params);

		outterLayout = new OutterLayout(context,this,activity);
		outterLayout.setDragLayout(this);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		addView(outterLayout, params);

	}

	private DragListener mDragListener = new DragListener() {

		@Override
		public void onOpen() {

		}

		@Override
		public void onDrag(float percent) {

		}

		@Override
		public void onClose() {

		}
	};

}
