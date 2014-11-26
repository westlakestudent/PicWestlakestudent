package com.westlakestudent.util;

import android.content.Context;
import android.widget.Toast;

/**
 *
 * WestlakestudentToast
 * @author chendong
 * 2014年11月26日 下午5:30:01
 * @version 1.0.0
 *
 */
public class WestlakestudentToast {

	public static void toast(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
