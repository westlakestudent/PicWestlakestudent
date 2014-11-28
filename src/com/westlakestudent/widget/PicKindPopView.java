package com.westlakestudent.widget;

import com.westlakestudent.R;
import com.westlakestudent.util.ScaleUtil;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * PicKindPopView
 * 
 * @author chendong 2014年11月28日 下午4:25:35
 * @version 1.0.0
 * 
 */
public class PicKindPopView extends PopupWindow {

	public interface OnKindChangedListener {
		void onChanged(String kind,int selected);
	}

	private String[] kinds = { "全部", "小清新", "诱惑", "性感美女", "写真", "长腿", "清纯",
			"校花", "比基尼" };

	private Context context = null;

	private OnKindChangedListener listener = null;

	private int selected = 0;
	
	private ListView list = null;

	public PicKindPopView(Context context,int pos) {
		this.context = context;
		selected = pos;
		setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8F84C1FF")));
		setContentView(new convertView(context));
		setWidth(ScaleUtil.scale(280));
		setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
	}
	
	public void setKindChangedListener(OnKindChangedListener l){
		listener = l;
	}

	private class convertView extends LinearLayout {

		public convertView(Context context) {
			super(context);
			LayoutParams params = null;
			setOrientation(LinearLayout.VERTICAL);
			TextView title = new TextView(context);
			title.setText("请选择类型");
			title.setTextColor(Color.WHITE);
			title.setTextSize(13);
			title.setGravity(Gravity.CENTER);

			params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER_HORIZONTAL;
			params.topMargin = ScaleUtil.scale(16);
			addView(title, params);

			list = new ListView(context);
			Adapter adapter = new Adapter();
			list.setAdapter(adapter);
			list.setOnItemClickListener(itemlistener);
			list.setDivider(null);
			Drawable d = new ColorDrawable(Color.parseColor("#E6000000"));
			list.setSelector(d);
			params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			params.topMargin = ScaleUtil.scale(10);
			addView(list, params);

		}

	}

	private class Adapter extends BaseAdapter {
		@Override
		public int getCount() {
			return kinds.length;
		}

		@Override
		public Object getItem(int position) {
			return kinds[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup arg2) {
			if(v == null){
				Titleitem item = new Titleitem(context,kinds[position]);
				if(position == selected)
					item.setSelected();
				v = item;
			}
			
			return v;
		}

	}

	private class Titleitem extends LinearLayout{

		private ImageView line = null;
		private TextView title = null;
		public Titleitem(Context context,String str) {
			super(context);
			setBackgroundResource(R.drawable.kind_selector);
			setOrientation(LinearLayout.VERTICAL);
			LayoutParams p = null;
			
			line = new ImageView(context);
			line.setBackgroundColor(Color.parseColor("#F0FFFF"));
			p = new LayoutParams(LayoutParams.MATCH_PARENT, ScaleUtil.scale(1));
			addView(line, p);
			
			title = new TextView(context);
			title.setTextSize(15);
			title.setText(str);
			title.setGravity(Gravity.CENTER);
			title.setTextColor(Color.parseColor("#FFFFFF"));
			p = new LayoutParams(LayoutParams.MATCH_PARENT, ScaleUtil.scale(60));
			addView(title, p);
			
		}
		
		public void setSelected(){
			title.setTextColor(Color.GREEN);
		}
	}
	
	
	
	private OnItemClickListener itemlistener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0,final View v, int position,
				long arg3) {
			list.setSelection(position);
			if(listener != null)
				listener.onChanged(kinds[position],position);
		}
	};
}
