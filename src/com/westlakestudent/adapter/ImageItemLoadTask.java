package com.westlakestudent.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.westlakestudent.util.ScaleUtil;

/**
 * 
 * ImageItemLoadTask
 * 
 * @author chendong 2014年11月27日 上午9:23:41
 * @version 1.0.0
 * 
 */
public class ImageItemLoadTask extends DefaultBitmapLoadCallBack<ImageView> {

	private static final String TAG = "ImageItemLoadTask";
	
	public ImageItemLoadTask(){
		
	}
	
	@Override
	public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap,
			BitmapDisplayConfig config, BitmapLoadFrom from) {
		super.onLoadCompleted(container, uri, bitmap, config, from);
		fadeInDisplay(container, bitmap);
		
		Log.i(TAG, "onLoadCompleted");
	}

	@Override
	public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
		super.onLoadFailed(container, uri, drawable);
		Log.i(TAG, "onLoadFailed");
	}

	@Override
	public void onLoading(ImageView container, String uri,
			BitmapDisplayConfig config, long total, long current) {
		super.onLoading(container, uri, config, total, current);
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
		} else {
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

}
