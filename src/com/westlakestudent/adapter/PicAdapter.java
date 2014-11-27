package com.westlakestudent.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lidroid.xutils.BitmapUtils;
import com.westlakestudent.R;
import com.westlakestudent.app.PictureApplication;
import com.westlakestudent.entity.ImageUrl;

/**
 * 
 * PicAdapter
 * 
 * @author chendong 2014年11月27日 上午9:16:22
 * @version 1.0.0
 * 
 */
public class PicAdapter extends BaseAdapter {
	private static final String TAG = "PicAdapter";

	private List<ImageUrl> urls = null;

	private Context context = null;

	private BitmapUtils bitmapUtils = null;

	public PicAdapter(Context context, List<ImageUrl> urls) {
		this.urls = urls;
		this.context = context;
		bitmapUtils = PictureApplication.bitmapUtils;
	}

	@Override
	public int getCount() {
		return this.urls.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.urls.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageItemHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
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
		Log.d(TAG, imageUrl.toString() + " ,position :" + position + " ,width:"
				+ imageUrl.getWidth() + " ,height:" + imageUrl.getHeight());
		bitmapUtils.display(holder.img, url, new ImageItemLoadTask());
		return convertView;
	}

	
	public void notify(List<ImageUrl> urls ){
		this.urls = urls;
		notifyDataSetChanged();
	}
}
