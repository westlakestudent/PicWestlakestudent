package com.westlakestudent.widget;


import com.westlakestudent.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;

public class PicKindDialog {

	public interface OnKindSelectedListener {
		void onChanged(String king,int selected);
	}
	
	private static final String TAG = "PicKindDialog";
	
	private String[] kinds = null;
	
	private AlertDialog dialog = null;
	
	private OnKindSelectedListener mOnKindSelectedListener = null;
	
	public PicKindDialog(Context context,int selected,OnKindSelectedListener l){
		mOnKindSelectedListener = l;
		kinds = context.getResources().getStringArray(R.array.kind);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("请选择");
		builder.setSingleChoiceItems(kinds, selected, mOnClickListener);
		dialog = builder.create();
	}
	
	public void show(){
		if(dialog != null)
			dialog.show();
	}
	
	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			Log.d(TAG, "which : " + which);
			if(mOnKindSelectedListener != null){
				mOnKindSelectedListener.onChanged(kinds[which], which);
			}
		}
	};
}
