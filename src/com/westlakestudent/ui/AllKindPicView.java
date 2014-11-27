package com.westlakestudent.ui;

import java.util.ArrayList;
import java.util.List;

import com.westlakestudent.R;
import com.westlakestudent.adapter.OnUrlCallBack;
import com.westlakestudent.adapter.PicAdapter;
import com.westlakestudent.adapter.UrlHandler;
import com.westlakestudent.entity.ImageUrl;
import com.westlakestudent.net.UrlPicker;
import com.westlakestudent.util.ScaleUtil;
import com.westlakestudent.util.WestlakestudentToast;
import com.westlakestudent.widget.MultiColumnListView;
import com.westlakestudent.widget.MultiColumnListView.OnLoadMoreListener;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * 
 * AllKindPicView
 * 
 * @author chendong 2014年11月21时间9:21:45
 * @version 1.0.0
 * 
 */
public class AllKindPicView extends LinearLayout implements OnUrlCallBack,
		OnLoadMoreListener {

	private static final String TAG = "AllKindPicView";
	private MultiColumnListView mMultiColumnListView = null;
	private TopOperateBar topbar = null;
	private Context context = null;
	private List<ImageUrl> urls = new ArrayList<ImageUrl>();
	private PicAdapter adapter = null;
	private UrlHandler handler = null;
	private UrlPicker picker = null;
	private int page = 1;

	public AllKindPicView(Context context) {
		super(context);
		setBackgroundColor(getResources().getColor(
				R.color.withe));
		setOrientation(LinearLayout.VERTICAL);
		this.context = context;
		handler = new UrlHandler(context);
		handler.setCallBack(this);
		picker = new UrlPicker(handler);
		adapter = new PicAdapter(context, urls);
		createUI();
		picker.pick(page);
	}

	private void createUI() {
		Log.d(TAG, "createUI");
		LayoutParams params = null;

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				ScaleUtil.scale(68));
		topbar = new TopOperateBar(context);
		addView(topbar, params);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		params.topMargin = ScaleUtil.scale(10);
		mMultiColumnListView = new MultiColumnListView(context);
		mMultiColumnListView.setOnLoadMoreListener(this);
		mMultiColumnListView.setAdapter(adapter);
		addView(mMultiColumnListView, params);

	}

	@Override
	public void callBack(List<ImageUrl> urls) {
		this.urls.addAll(urls);
		adapter.notify(this.urls);
		mMultiColumnListView.onLoadMoreComplete();
	}

	@Override
	public void onLoadMore() {
		picker.pick(++page);
		WestlakestudentToast.toast(context,
				context.getResources().getString(R.string.more) + page);
	}

	@Override
	public void onFailure() {
		picker.pick(page);
	}

}
