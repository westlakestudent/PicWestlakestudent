package com.westlakestudent.activity;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.westlakestudent.R;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.entity.ImageUrl;
import com.westlakestudent.net.UrlPicker;
import com.westlakestudent.ui.AllKindPicView;
import com.westlakestudent.util.ScaleUtil;
import com.westlakestudent.util.WestlakestudentToast;
import com.westlakestudent.widget.MultiColumnListView.OnLoadMoreListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

public class MainActivity extends Activity implements OnLoadMoreListener{

	private static final String TAG = "MainActivity";

	private UrlPicker picker = null;

	private static List<ImageUrl> urls = new ArrayList<ImageUrl>();

	private BitmapUtils mBitmapUtils = null;

	private static AllKindPicView mAllKindPicView = null;

	private static PicAdapter mPicAdapter = null;
	
	private int page = 1;
	
	private static Context context = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		com.westlakestudent.util.ScaleUtil.scaleInit(this, 800, 480, 240);

		context = this;
		
		mAllKindPicView = new AllKindPicView(this,this);
		mPicAdapter = new PicAdapter();
		mAllKindPicView.setAdapter(mPicAdapter);

		setContentView(mAllKindPicView);

		mBitmapUtils = new BitmapUtils(this);
		mBitmapUtils.configDefaultLoadingImage(R.drawable.empty_photo);
		mBitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
		mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
		mBitmapUtils.configMemoryCacheEnabled(true);
		mBitmapUtils.configDiskCacheEnabled(true);
		mBitmapUtils.configDefaultBitmapMaxSize(BitmapCommonUtils
				.getScreenSize(this).scaleDown(2));

		picker = UrlPicker.getInstance();
		picker.registerHandler(UrlHandler);
		picker.pick(page,"小清新");
	}

	private static final Handler UrlHandler = new Handler() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Constants.URL_DONE:
				List<ImageUrl> newUrls = (List<ImageUrl>) msg.obj;
				if(newUrls == null || newUrls.isEmpty()){
					WestlakestudentToast.toast(context, "亲，未获取到图片数据");
					return;
				}
				urls.addAll(newUrls);
				mPicAdapter.notifyDataSetChanged();
				mAllKindPicView.onRefreshComplete();
				break;
			}
		}

	};

	private class PicAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return urls.size();
		}

		@Override
		public Object getItem(int position) {
			return urls.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageItemHolder holder = null;

			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.pic_item, null);
				holder = new ImageItemHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ImageItemHolder) convertView.getTag();
			}
			ImageUrl imageUrl = urls.get(position);
			String desc = imageUrl.getDesc();
			String url = imageUrl.getUrl();
			holder.desc.setText(desc);
			Log.d(TAG, imageUrl.toString() + "   第" + position + "个" + "width:"
					+ imageUrl.getWidth() + "height:" + imageUrl.getHeight());
			mBitmapUtils.display(holder.img, url, new CustomBitmapLoadCallBack(
					holder));
			return convertView;
		}

	}

	class CustomBitmapLoadCallBack extends DefaultBitmapLoadCallBack<ImageView> {

		private ImageItemHolder holder = null;

		public CustomBitmapLoadCallBack(ImageItemHolder holder) {
			this.holder = holder;
		}

		@Override
		public void onLoading(ImageView container, String uri,
				BitmapDisplayConfig config, long total, long current) {
			super.onLoading(container, uri, config, total, current);
			
		}

		@Override
		public void onLoadCompleted(ImageView container, String uri,
				Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
			super.onLoadCompleted(container, uri, bitmap, config, from);
			fadeInDisplay(container, bitmap);
			Log.d(TAG, holder.desc.getText() + "");
		}

	}

	private class ImageItemHolder {
		private ImageView img;

		private TextView desc;

		public ImageItemHolder(View v) {
			img = (ImageView) v.findViewById(R.id.img);
			desc = (TextView) v.findViewById(R.id.desc);
		}
	}

	private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(
			android.R.color.transparent);

	private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {
		final TransitionDrawable transitionDrawable = new TransitionDrawable(
				new Drawable[] { TRANSPARENT_DRAWABLE,
						new BitmapDrawable(imageView.getResources(), bitmap) });
		int width = ScaleUtil.scale(240);
		float scale = bitmap.getWidth() * 1000 / width;
		float scalef = scale / 1000;
		int height = 0;
		if (scale == 0) {
			scale = width * 1000 / bitmap.getWidth();
			scalef = scale / 1000;
			if (scale == 0) {
				imageView.setVisibility(View.GONE);
				Log.d(TAG, "scale is 0");
				return;
			}
			height = (int) (bitmap.getHeight() * scalef);
		}else{
			height = (int) (bitmap.getHeight() / scalef);
		}

		
		LayoutParams params = imageView.getLayoutParams();
		params.height = height;
		params.width = width;
		imageView.setLayoutParams(params);
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setPadding(5, 5, 5, 5);

		imageView.setImageDrawable(transitionDrawable);
		transitionDrawable.startTransition(500);
	}

	@Override
	public void onLoadMore() {
		picker.pick(++page,"小清新");
		Toast.makeText(this, "..加载更多", Toast.LENGTH_SHORT).show();
	}
}
