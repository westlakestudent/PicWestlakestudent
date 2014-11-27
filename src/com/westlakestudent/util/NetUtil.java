package com.westlakestudent.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

public class NetUtil {
	private Context mContext = null;
	private static ConnectivityManager conn_Manager = null;
	private final static String TAG = "NetUtil";

	public NetUtil(Context context) {
		Log.d(TAG, "NetUtil");
		mContext = context;
		conn_Manager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public boolean PhoneIsOnLine() {
		boolean bIsOnLine = false;
		NetworkInfo info = conn_Manager.getActiveNetworkInfo();
		if (info != null)
			bIsOnLine = info.isAvailable();
		return bIsOnLine;
	}

	public static void openWirelessSetting(Context context) {
		Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
		((Activity) context).startActivity(intent);
	}

}
