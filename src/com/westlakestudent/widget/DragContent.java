package com.westlakestudent.widget;


import com.westlakestudent.widget.DragLayout.Status;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 *
 * DragContent
 * @author chendong
 * 2014年11月27日 下午4:35:38
 * @version 1.0.0
 *
 */
public class DragContent extends RelativeLayout {

	private DragLayout dl;

    public DragContent(Context context) {
        super(context);
    }

    public DragContent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragContent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setDragLayout(DragLayout dl) {
        this.dl = dl;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (dl.getStatus() != Status.Close) {
            return true;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (dl.getStatus() != Status.Close) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                dl.close();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }


}
