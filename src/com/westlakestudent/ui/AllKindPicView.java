package com.westlakestudent.ui;

import java.util.ArrayList;
import java.util.List;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.spot.SpotManager;

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
import com.westlakestudent.widget.PicKindDialog.OnKindSelectedListener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 
 * AllKindPicView
 * 
 * @author chendong 2014年11月21时间9:21:45
 * @version 1.0.0
 * 
 */
public class AllKindPicView extends FrameLayout implements OnUrlCallBack,
		OnLoadMoreListener ,OnKindSelectedListener{

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
	private String kind = null;
	

	public AllKindPicView(Context context) {
		super(context);
		setBackgroundColor(getResources().getColor(R.color.withe));
		this.context = context;
		handler = new UrlHandler(context);
		handler.setCallBack(this);
		picker = new UrlPicker(handler);
		adapter = new PicAdapter(context, urls);
		createUI();
		if(kind == null)
			picker.pick(page);
		else
			picker.pick(page, kind,false);
	}
	
	public AllKindPicView(Context context,DragLayout drag){
		super(context);
		setBackgroundColor(getResources().getColor(R.color.withe));
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
		params.gravity = Gravity.TOP;
		topbar = new TopOperateBar(context);
		topbar.setDrag(drag);
		topbar.setTitle("全部");
		topbar.setKindChangedListener(this);
		addView(topbar, params);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.TOP;
		params.topMargin = ScaleUtil.scale(78);
		mMultiColumnListView = new MultiColumnListView(context);
		mMultiColumnListView.setOnLoadMoreListener(this);
		mMultiColumnListView.setAdapter(adapter);
		mMultiColumnListView.setOnItemClickListener(mOnItemClickListener);
		addView(mMultiColumnListView, params);
		
		
		AdView adView = new AdView(context, AdSize.FIT_SCREEN);
		
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.BOTTOM;
		addView(adView, params);
		
		SpotManager.getInstance(context).loadSpotAds();
		SpotManager.getInstance(context).setShowInterval(20);// 设置20秒的显示时间间隔
		SpotManager.getInstance(context).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);

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
	public void onChangedKind(List<ImageUrl> urls) {
		this.urls.clear();
		this.urls.addAll(urls);
		adapter.notify(this.urls);
	}
	
	@Override
	public void onLoadMore() {
		if(kind == null)
			picker.pick(++page);
		else
			picker.pick(++page, kind,false);
		WestlakestudentToast.toast(context,
				context.getResources().getString(R.string.more) + page);
	}

	@Override
	public void onFailure() {
		if (repick < 10) {
			if(kind == null)
				picker.pick(page);
			else
				picker.pick(page, kind,false);
			repick++;// 每次失败则+1，10次之后就不再获取
			return;
		}
		WestlakestudentDialog.showWithListener(context, "提示",
				getResources().getString(R.string.no_net),dialogListener);
	}

	private android.content.DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if(kind == null)
				picker.pick(page);
			else
				picker.pick(page, kind,false);
			dialog.dismiss();
		}
	};

	@Override
	public void onChanged(String kind, int selected) {
		page = 0;
		picker.pick(page, kind,true);
		this.kind = kind;
		mMultiColumnListView.smoothScrollToPosition(0);
		if(topbar != null)
			topbar.onChanged(selected,kind);
	}


}
