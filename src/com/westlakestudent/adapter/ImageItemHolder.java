package com.westlakestudent.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.westlakestudent.R;

/**
 *
 * ImageItemHolder
 * @author chendong
 * 2014年11月27日 上午9:19:09
 * @version 1.0.0
 *
 */
public class ImageItemHolder {

	public ImageView img;

	public TextView desc;

	public ImageItemHolder(View v) {
		img = (ImageView) v.findViewById(R.id.img);
		desc = (TextView) v.findViewById(R.id.desc);
	}
}
