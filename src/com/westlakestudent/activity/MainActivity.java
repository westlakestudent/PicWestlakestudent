package com.westlakestudent.activity;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.westlakestudent.R;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.entity.ImageUrl;
import com.westlakestudent.net.UrlPicker;
import com.westlakestudent.ui.AllKindPicView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private UrlPicker picker = null;

	private static List<ImageUrl> urls = new ArrayList<ImageUrl>();

	private BitmapUtils mBitmapUtils = null;

	private AllKindPicView mAllKindPicView = null;

	private static PicAdapter mPicAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		com.westlakestudent.util.ScaleUtil.scaleInit(this, 800, 480, 240);

		mAllKindPicView = new AllKindPicView(this);
		mPicAdapter = new PicAdapter();
		mAllKindPicView.setAdapter(mPicAdapter);

		setContentView(mAllKindPicView);
		mBitmapUtils = new BitmapUtils(this);
		mBitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
		mBitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
		mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
		mBitmapUtils.configMemoryCacheEnabled(false);
		mBitmapUtils.configDiskCacheEnabled(false);
		picker = UrlPicker.getInstance();
		picker.registerHandler(UrlHandler);
		picker.pick(1);
	}

	private static final Handler UrlHandler = new Handler() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Constants.URL_DONE:
				urls.clear();
				urls.addAll((List<ImageUrl>) msg.obj);
				for (ImageUrl url : urls) {
					Log.d(TAG, url.toString());
				}
				mPicAdapter.notifyDataSetChanged();
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
			holder.progress.setProgress(0);
			Log.d(TAG, imageUrl.toString() + "µÚ" + position + "¸ö");
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
			holder.progress.setProgress((int) (current * 100 / total));
			holder.desc.setText((int) (current * 100 / total) + "%");
		}



		@Override
		public void onLoadCompleted(ImageView container, String uri,
				Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
			// super.onLoadCompleted(container, uri, bitmap, config, from);
			fadeInDisplay(container, bitmap);
		}

	}

	static class ImageItemHolder {
		ImageView img;

		TextView desc;

		ProgressBar progress;

		public ImageItemHolder(View v) {
			img = (ImageView) v.findViewById(R.id.img);
			desc = (TextView) v.findViewById(R.id.desc);
			progress = (ProgressBar) v.findViewById(R.id.progress);
		}
	}

	private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(
			android.R.color.transparent);

	private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {
		final TransitionDrawable transitionDrawable = new TransitionDrawable(
				new Drawable[] { TRANSPARENT_DRAWABLE,
						new BitmapDrawable(imageView.getResources(), bitmap) });
		imageView.setImageDrawable(transitionDrawable);
		transitionDrawable.startTransition(500);
	}
}
