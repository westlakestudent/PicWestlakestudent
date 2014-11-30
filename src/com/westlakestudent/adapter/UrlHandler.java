package com.westlakestudent.adapter;

import java.util.List;

import com.westlakestudent.R;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.entity.ImageUrl;
import com.westlakestudent.util.WestlakestudentToast;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * UrlHandler
 * 
 * @author chendong 2014年11月27日 上午9:33:49
 * @version 1.0.0
 * 
 */
public class UrlHandler extends Handler {

	private Context context = null;

	private OnUrlCallBack callback = null;

	public void setCallBack(OnUrlCallBack call) {
		callback = call;
	}

	public UrlHandler(Context context) {
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {
		case Constants.URL_DONE:
			@SuppressWarnings("unchecked")
			List<ImageUrl> newUrls = (List<ImageUrl>) msg.obj;
			if (newUrls == null || newUrls.isEmpty()) {
				WestlakestudentToast.toast(context, context.getResources()
						.getString(R.string.no_url));
				return;
			}
			
			
			if (callback != null){
				if(msg.arg1 == Constants.CHANGED)
					callback.onChangedKind(newUrls);
				else if(msg.arg1 == Constants.UNCHANGED)
					callback.callBack(newUrls);
			}
				
			break;
		case Constants.URL_FAIL:
			WestlakestudentToast.toast(context, context.getResources()
					.getString(R.string.no_data));
			if (callback != null)
				callback.onFailure();
		}
	}

}
