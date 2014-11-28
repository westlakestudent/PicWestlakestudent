package com.westlakestudent.ui;

import java.util.ArrayList;
import java.util.List;

import com.westlakestudent.R;
import com.westlakestudent.activity.ImageActivity;
import com.westlakestudent.adapter.OnUrlCallBack;
import com.westlakestudent.adapter.PicAdapter;
import com.westlakestudent.adapter.UrlHandler;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.entity.ImageUrl;
import com.westlakestudent.net.UrlPicker;
import com.westlakestudent.util.ScaleUtil;
import com.westlakestudent.util.WestlakestudentDialog;
import com.westlakestudent.util.WestlakestudentToast;
import com.westlakestudent.widget.DragLayout;
import com.westlakestudent.widget.MultiColumnListView;
import com.westlakestudent.widget.MultiColumnListView.OnLoadMoreListener;
import com.westlakestudent.widget.PLA_AdapterView;
import com.westlakestudent.widget.PLA_AdapterView.OnItemClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
	private DragLayout drag = null;
	private int page = 1;
	private int repick = 0;
	private Activity activity = null;

	public AllKindPicView(Context context) {
		super(context);
		setBackgroundColor(getResources().getColor(R.color.withe));
		setOrientation(LinearLayout.VERTICAL);
		this.context = context;
		handler = new UrlHandler(context);
		handler.setCallBack(this);
		picker = new UrlPicker(handler);
		adapter = new PicAdapter(context, urls);
		createUI();
		picker.pick(page);
	}
	
	public AllKindPicView(Context context,DragLayout drag){
		super(context);
		setBackgroundColor(getResources().getColor(R.color.withe));
		setOrientation(LinearLayout.VERTICAL);
		this.context = context;
		this.drag = drag;
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
		topbar.setDrag(drag);
		topbar.setTitle("图片");
		addView(topbar, params);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		params.topMargin = ScaleUtil.scale(10);
		mMultiColumnListView = new MultiColumnListView(context);
		mMultiColumnListView.setOnLoadMoreListener(this);
		mMultiColumnListView.setAdapter(adapter);
		mMultiColumnListView.setOnItemClickListener(mOnItemClickListener);
		addView(mMultiColumnListView, params);

	}

	private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(PLA_AdapterView<?> parent, View view,
				int position, long id) {
			if(activity == null)
				return;
			ArrayList<String> data = fix();
			Intent intent = new Intent(activity,ImageActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt(Constants.SELECTED, position);
			bundle.putStringArrayList(Constants.DATA, data);
			intent.putExtras(bundle);
			activity.startActivity(intent);
		}
	};
	
	
	private ArrayList<String> fix(){
		ArrayList<String> datas = new ArrayList<String>();
		if(urls != null && !urls.isEmpty()){
			for(ImageUrl url: urls){
				datas.add(url.getUrl());
			}
		}
		return datas;
	}
	
	public void setActivity(Activity activity){
		this.activity = activity;
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
		if (repick < 10) {
			picker.pick(page);
			repick++;// 每次失败则+1，10次之后就不再获取
			return;
		}
		WestlakestudentDialog.showWithListener(context, "提示",
				getResources().getString(R.string.no_net),dialogListener);
	}

	private android.content.DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			picker.pick(page);
			dialog.dismiss();
		}
	};

}
