package com.westlakestudent.adapter;

import java.util.List;

import com.westlakestudent.entity.ImageUrl;

/**
 *
 * OnUrlCallBack
 * @author chendong
 * 2014年11月27日 上午9:35:31
 * @version 1.0.0
 *
 */
public interface OnUrlCallBack {

	void callBack(List<ImageUrl> urls);
	
	void onFailure();
}
