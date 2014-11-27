package com.westlakestudent.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.westlakestudent.R;
import com.westlakestudent.util.ScaleUtil;
import com.westlakestudent.widget.CircleImageView;

/**
 * 
 * InnerLayout
 * 
 * @author chendong 2014年11月27日 下午4:41:51
 * @version 1.0.0
 * 
 */
public class InnerLayout extends RelativeLayout {

	private CircleImageView head = null;
	private TextView name = null;
	private ListView items = null;
	private ArrayAdapter<String> adapter = null;
	private static final int LAYOUT_ID = 0xfffffff0;

	public InnerLayout(Context context) {
		super(context);
		setBackgroundColor(getResources().getColor(R.color.innerbg));
		adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.settings));
		createUI(context);
	}

	private void createUI(Context context) {
		LayoutParams params = null;

		LinearLayout layout = new LinearLayout(context);
		layout.setId(LAYOUT_ID);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.topMargin = ScaleUtil.scale(50);
		addView(layout, params);

		LinearLayout.LayoutParams lp = null;

		head = new CircleImageView(context);
		head.setImageResource(R.drawable.ic_launcher);
		lp = new LinearLayout.LayoutParams(ScaleUtil.scale(80),
				ScaleUtil.scale(80));
		lp.leftMargin = ScaleUtil.scale(20);
		layout.addView(head, lp);

		name = new TextView(context);
		name.setText(R.string.app_name);
		name.setTextSize(20);
		name.setTextColor(getResources().getColor(R.color.withe));
		name.setGravity(Gravity.CENTER);
		lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.CENTER_VERTICAL;
		lp.leftMargin = ScaleUtil.scale(20);
		layout.addView(name, lp);

		items = new ListView(context);
		items.setBackgroundColor(getResources().getColor(R.color.innerbg));
		items.setAdapter(adapter);
		items.setDivider(new ColorDrawable(getResources().getColor(
				R.color.withe)));
		items.setDividerHeight(1);
		
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW, LAYOUT_ID);
		params.topMargin = ScaleUtil.scale(20);
		addView(items, params);

	}

}
