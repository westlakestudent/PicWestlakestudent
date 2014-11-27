package com.westlakestudent.app;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.westlakestudent.R;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

/**
 *
 * PictureApplication
 * @author chendong
 * 2014年11月27日 上午9:12:08
 * @version 1.0.0
 *
 */
public class PictureApplication extends Application {

	public static BitmapUtils bitmapUtils = null;
	
	private static final String TAG = "PictureApplication";

	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d(TAG, "onCreate");
		
		bitmapUtils = new BitmapUtils(this);
		bitmapUtils.configDefaultLoadingImage(R.drawable.empty_photo);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
		bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
		bitmapUtils.configMemoryCacheEnabled(true);
		bitmapUtils.configDiskCacheEnabled(true);
		bitmapUtils.configDefaultBitmapMaxSize(BitmapCommonUtils
				.getScreenSize(this).scaleDown(2));
	}

	public BitmapUtils getBitmapUtils() {
		return bitmapUtils;
	}

	
	
}
