package com.westlakestudent.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.westlakestudent.R;
import com.westlakestudent.adapter.ImageItemLoadTask;
import com.westlakestudent.app.PictureApplication;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.ui.TopOperateBar.BackListener;
import com.westlakestudent.util.ScaleUtil;
import com.westlakestudent.util.WestlakestudentToast;
import com.westlakestudent.widget.dialog.Effectstype;
import com.westlakestudent.widget.dialog.NiftyDialogBuilder;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 
 * ImageShow
 * 
 * @author chendong 2014年11月28日 上午9:34:41
 * @version 1.0.0
 * 
 */
public class ImageShow extends LinearLayout {

	private static final String TAG = "ImageShow";

	private TopOperateBar topbar = null;

	private ViewPager viewPager = null;

	private List<String> urls = null;

	private List<ImageView> images = new ArrayList<ImageView>();

	private Activity activity = null;

	private BitmapUtils bitmapUtils = null;

	private NiftyDialogBuilder dialogBuilder = null;

	public ImageShow(Context context) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
		init(context);
		createUI(context, 0);
	}

	public ImageShow(Context context, List<String> urls, int selected) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
		this.urls = urls;
		init(context);

		createUI(context, selected);
	}

	private void init(Context context) {
		bitmapUtils = PictureApplication.bitmapUtils;
		if (urls != null && !urls.isEmpty()) {
			int size = urls.size();
			Log.d(TAG, "size : " + size);
			for (int i = 0; i < size; i++) {
				ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);
				ImageView img = new ImageView(context);
				img.setBackgroundResource(R.drawable.empty_photo);
				img.setLayoutParams(params);
				images.add(img);
			}
		}
	}

	private void createUI(Context context, int selected) {
		LayoutParams params = null;

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				ScaleUtil.scale(68));
		topbar = new TopOperateBar(context);
		topbar.setMenuRes(R.drawable.back_selector);
		topbar.setBackListener(backListener);
		topbar.setTitle("预览");
		addView(topbar, params);

		viewPager = new ViewPager(context);
		viewPager.setAdapter(new ImageAdapter());
		viewPager.setCurrentItem(selected);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		addView(viewPager, params);
	}

	private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			dialogBuilder = NiftyDialogBuilder.getInstance(activity);
			dialogBuilder.withTitle("保存图片").withMessage("保存图片?").show();

			dialogBuilder
					.withEffect(Effectstype.RotateBottom)
					.withTitleColor("#FFFFFF")
					.withDividerColor("#11000000")
					.withMessageColor("#FFFFFFFF")
					.withDialogColor("#66B3FF")
					.withIcon(
							getResources().getDrawable(R.drawable.ic_launcher))
					.withDuration(100).isCancelableOnTouchOutside(true)
					.setButton1Click(downloadlistener)
					.setButton2Click(cancelistener)
					.withButton1Text("确定")
					.withButton2Text("取消");
			return true;
		}
	};

	private OnClickListener cancelistener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (dialogBuilder != null)
				dialogBuilder.dismiss();
		}
	};
	
	
	private OnClickListener downloadlistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (dialogBuilder != null)
				dialogBuilder.dismiss();
			int current = viewPager.getCurrentItem();

			String savePath = null;
			String storageState = Environment.getExternalStorageState();
			long date = new Date().getTime();
			if (storageState.equals(Environment.MEDIA_MOUNTED)) {
				savePath = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/PIC/IMG/" + date + ".jpg";
			}
			HttpUtils http = new HttpUtils(10 * 1000);
			HttpHandler<File> handler = http.download(urls.get(current),
					savePath, new DownLoadTask());
			Log.d(TAG, handler.getState() + ", url : " + urls.get(current));
		}

	};

	private class DownLoadTask extends RequestCallBack<File> {

		@Override
		public void onFailure(HttpException arg0, String info) {
			WestlakestudentToast.toast(activity, "保存失败!");
		}

		@Override
		public void onSuccess(ResponseInfo<File> info) {
			WestlakestudentToast.toast(activity, "保存成功!");
		}

	}

	public void setActivity(Activity ac) {
		activity = ac;
	}

	private BackListener backListener = new BackListener() {

		@Override
		public void onBack() {
			if (activity != null)
				activity.finish();
		}
	};

	private class ImageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView(images.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView image = images.get(position);
			image.setOnLongClickListener(mOnLongClickListener);
			container.addView(image, 0);
			bitmapUtils.display(image, urls.get(position),
					new ImageItemLoadTask(Constants.BIG));
			return images.get(position);
		}

	}

}
