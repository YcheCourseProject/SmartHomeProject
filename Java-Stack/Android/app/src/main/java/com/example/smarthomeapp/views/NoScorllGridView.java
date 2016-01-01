package com.example.smarthomeapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Yulin on 2015/7/21.
 */
public class NoScorllGridView extends GridView {


    public NoScorllGridView(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public NoScorllGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public NoScorllGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
