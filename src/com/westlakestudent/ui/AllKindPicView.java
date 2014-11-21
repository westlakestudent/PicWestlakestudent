package com.westlakestudent.ui;

import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.westlakestudent.widget.MultiColumnListView;

import android.content.Context;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

/**
 * 
 * AllKindPicView
 * 
 * @author chendong 2014年11月21日 上午9:21:45
 * @version 1.0.0
 * 
 */
public class AllKindPicView extends RelativeLayout {

	private static final String TAG = "AllKindPicView";
	private MultiColumnListView mMultiColumnListView = null;
	private Context context = null;

	public AllKindPicView(Context context) {
		super(context);
		this.context = context;
		createUI();
	}

	private void createUI() {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		
		mMultiColumnListView = new MultiColumnListView(context);
		
		addView(mMultiColumnListView, params);
	}
	
	
	public void setAdapter(BaseAdapter adapter){
		mMultiColumnListView.setAdapter(adapter);
	}
	
}
