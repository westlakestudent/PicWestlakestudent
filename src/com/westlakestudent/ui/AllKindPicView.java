package com.westlakestudent.ui;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.westlakestudent.net.UrlPicker;
import com.westlakestudent.widget.MultiColumnPullToRefreshListView;
import com.westlakestudent.widget.MultiColumnPullToRefreshListView.OnRefreshListener;

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
	private MultiColumnPullToRefreshListView mMultiColumnPullToRefreshListView = null;
	private Context context = null;
	private UrlPicker picker = null;
	private int page = 1;

	public AllKindPicView(Context context) {
		super(context);
		this.context = context;
		picker = UrlPicker.getInstance();
		createUI();
	}

	private void createUI() {
		Log.d(TAG, "createUI");
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		mMultiColumnPullToRefreshListView = new MultiColumnPullToRefreshListView(
				context);
		mMultiColumnPullToRefreshListView.setShowLastUpdatedText(true);
		// mMultiColumnPullToRefreshListView.setLockScrollWhileRefreshing(true);
		mMultiColumnPullToRefreshListView
				.setOnRefreshListener(mOnRefreshListener);
		addView(mMultiColumnPullToRefreshListView, params);

	}

	private OnRefreshListener mOnRefreshListener = new OnRefreshListener() {

		@Override
		public void onRefresh() {
			picker.pick(page);
		}

	};

	public void onRefreshComplete() {
		if (mMultiColumnPullToRefreshListView != null)
			mMultiColumnPullToRefreshListView.onRefreshComplete();
	}

	public void setAdapter(BaseAdapter adapter) {
		mMultiColumnPullToRefreshListView.setAdapter(adapter);
	}

}
