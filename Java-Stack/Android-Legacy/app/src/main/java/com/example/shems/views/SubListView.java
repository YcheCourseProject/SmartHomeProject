package com.example.shems.views;

import android.view.View;
import android.widget.ListView;

/**
 * Created by CHEYulin on 2015/5/17.
 */
public class SubListView extends ListView {
    public SubListView(android.content.Context context,
                       android.util.AttributeSet attrs) {
        super(context, attrs);

    }


    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}