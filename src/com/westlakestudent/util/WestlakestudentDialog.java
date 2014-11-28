package com.westlakestudent.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * 
 * WestlakestudentDialog
 * 
 * @author chendong 2014年11月26日 下午5:25:41
 * @version 1.0.0
 * 
 */
public class WestlakestudentDialog {

	public static void showAlert(Context context, String title, String msg) {
		if (title == null)
			title = "提示";
		new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
				.setPositiveButton("确定", null)
				.setIcon(android.R.drawable.ic_dialog_alert).create().show();
	}

	public static void showInfo(Context context, String title, String msg) {
		if (title == null)
			title = "提示";
		new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
				.setPositiveButton("确定", null)
				.setIcon(android.R.drawable.ic_dialog_info).create().show();
	}

	public static void showNeedNetOpen(final Context context, String title,
			String msg) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
				.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						NetUtil.openWirelessSetting(context);
						arg0.dismiss();
					}
				}).setNegativeButton("取消", null)
				.setIcon(android.R.drawable.ic_dialog_alert).create().show();
	}
	
	
	public static void showWithListener(final Context context, String title,
			String msg,OnClickListener listener){
		new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
		.setPositiveButton("确定", listener).setNegativeButton("取消", null)
		.setIcon(android.R.drawable.ic_dialog_alert).create().show();
		
	}
}
