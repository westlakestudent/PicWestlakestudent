package com.westlakestudent.ui;

import com.westlakestudent.widget.MultiColumnListView;
import com.westlakestudent.widget.MultiColumnListView.OnLoadMoreListener;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;


/**
 * 
 * AllKindPicView
 * 
 * @author chendong 2014年11月21时间9:21:45
 * @version 1.0.0
 * 
 */
public class AllKindPicView extends LinearLayout {

	private static final String TAG = "AllKindPicView";
	private MultiColumnListView mMultiColumnListView = null;
	private OnLoadMoreListener mOnLoadMoreListener = null;
	private Context context = null;

	public AllKindPicView(Context context,OnLoadMoreListener listener) {
		super(context);
		mOnLoadMoreListener = listener;
		this.context = context;
		createUI();
	}

	private void createUI() {
		Log.d(TAG, "createUI");
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		mMultiColumnListView = new MultiColumnListView(context);
		mMultiColumnListView.setOnLoadMoreListener(mOnLoadMoreListener);
		addView(mMultiColumnListView, params);

	}


	public void onRefreshComplete() {
		if(mMultiColumnListView != null)
			mMultiColumnListView.onLoadMoreComplete();
	}

	public void setAdapter(BaseAdapter adapter) {
		mMultiColumnListView.setAdapter(adapter);
	}

}
