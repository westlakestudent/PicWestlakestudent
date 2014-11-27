package com.westlakestudent.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.westlakestudent.constants.Constants;
import com.westlakestudent.entity.ImageUrl;

public class UrlPicker {
	private static final String TAG = "UrlPicker";
	
	private Handler handler = null;

	private List<ImageUrl> urls = new ArrayList<ImageUrl>();
	
	public UrlPicker(Handler handler){
		this.handler = handler;
	}
	
	
	public void pick(int page) {
		String url = "http://image.baidu.com/channel/listjson?" + "pn="
				+ page + "&rn=30&tag1=美女&tag2=全部&ie=utf8";
		
		HttpUtils http = new HttpUtils(10 * 1000);
		http.send(HttpMethod.GET, url, new PickTask());
	}

	public void pick(int page,String kind){
		String url = "http://image.baidu.com/channel/listjson?"
				+ "pn=" + page + "&rn=30&tag1=美女&tag2=全部&ftags=" + kind + "&ie=utf8";
		HttpUtils http = new HttpUtils(10 * 1000);
		http.send(HttpMethod.GET, url, new PickTask());
	}
	
	private class PickTask extends RequestCallBack<String> {

		@Override
		public void onFailure(HttpException arg0, String info) {
			Log.e(TAG, info);
		}

		@Override
		public void onSuccess(ResponseInfo<String> info) {
			String result = info.result;
			if (result == null)
				return;
			Log.i(TAG, result);
			urls.clear();
			try {
				JSONObject json = new JSONObject(result);
				JSONArray data = json.optJSONArray("data");
				for (int i = 0; i < data.length(); i++) {
					JSONObject obj = data.getJSONObject(i);
					String url = obj.optString("image_url");
					String width = obj.optString("image_width");
					String height = obj.optString("image_height");
					String desc = obj.optString("desc");
					ImageUrl img = new ImageUrl();
					img.setDesc(desc);
					img.setHeight(isNull(height)? 0 :Integer.valueOf(height));
					img.setUrl(url);
					img.setWidth(isNull(width)? 0 : Integer.valueOf(width));
					if(!img.isValid())
						continue;
					urls.add(img);
				}

				if(handler != null){
					Message msg = handler.obtainMessage();
					msg.obj = urls;
					msg.what = Constants.URL_DONE;
					handler.sendMessage(msg);
				}
			} catch (JSONException e) {
				Log.e(TAG, e.getMessage());
			}
		}

	}
	
	
	private boolean isNull(String str){
		return str == null || str.equals("");
	}

}
