package com.example.shems.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shems.util.algorithm.Algorithm_AC;

public class TableView extends ViewGroup { // 表格控件
    private static final int START_X = 0;// 起始X坐标
    private static final int START_Y = 0;// 起始Y坐标
    private static final int BORDER = 2;// 表格边框宽度
    private static final String OUT_HOME = "\nout";
    private static final String IN_HOME = "\ninhome";
    private int mRow;// 行数
    private int mCol;// 列数
    public int savehba[] = new int[24];

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRow = 4;// 默认行数为4
        this.mCol = 4;// 默认列数为4
    }

    @Deprecated
    public TableView(Context context, int row, int col, String[] strs) {
        super(context);
        if (row > 20 || col > 20) {
            this.mRow = 20;// 大于20行时，设置行数为20行
            this.mCol = 20;// 大于20列时，设置列数为20列
        } else if (row == 0 || col == 0) {
            this.mRow = 3;
            this.mCol = 3;
        } else {
            this.mRow = row;
            this.mCol = col;
        }
        // 添加子控件
        this.addOtherView(context, strs);
    }

    @Deprecated
    public TableView(Context context, int row, int col, int[] hba) {
        super(context);
        if (row > 20 || col > 20) {
            this.mRow = 20;// 大于20行时，设置行数为20行
            this.mCol = 20;// 大于20列时，设置列数为20列
        } else if (row == 0 || col == 0) {
            this.mRow = 3;
            this.mCol = 3;
        } else {
            this.mRow = row;
            this.mCol = col;
        }
        this.addOtherViewForAC(context, hba);
    }

    @Deprecated
    public void addOtherViewForAC(Context context, int[] hba) {
        for (int i = 1; i <= mRow; i++) {
            for (int j = 1; j <= mCol; j++) {
                this.savehba[(i - 1) * j + j - 1] = hba[(i - 1) * j + j - 1];
                TextView view = new TextView(context);
                StringBuilder sbBuilder = new StringBuilder();
                double ishome = hba[(i - 1) * mCol + j - 1];
                sbBuilder.append((i - 1) * mCol + j - 1).append(":00-\n")
                        .append((i - 1) * mCol + j).append(":00")
                        .append("\n\n").append("status:");
                if (ishome == 0)
                    sbBuilder.append(OUT_HOME);
                else
                    sbBuilder.append(IN_HOME);
                view.setText(sbBuilder.toString());
                view.setTextColor(Color.rgb(79, 129, 189));
                view.setGravity(Gravity.CENTER);

                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        String string = (String) ((TextView) arg0).getText();
                        String[] strs = string.split(":");
                        if (strs[3].equals(IN_HOME)) {
                            strs[3] = OUT_HOME;
                            arg0.setBackgroundColor(Color.rgb(219, 238, 243));
                            int start = Integer.parseInt(strs[0]);
                            Algorithm_AC.HumanBehavior[start] = 0;


                        } else {
                            strs[3] = IN_HOME;
                            arg0.setBackgroundColor(Color.rgb(235, 241, 221));
                            int start = Integer.parseInt(strs[0]);
                            Algorithm_AC.HumanBehavior[start] = 1;
                            savehba[start] = 1;
                        }
                        StringBuilder sbBuilder = new StringBuilder();
                        for (int i = 0; i < 4; i++) {
                            sbBuilder.append(strs[i]).append(":");
                        }
                        sbBuilder.deleteCharAt(sbBuilder.length() - 1);
                        ((TextView) arg0).setText(sbBuilder.toString());
                    }
                });
                if (ishome == 0) {
                    view.setBackgroundColor(Color.rgb(219, 238, 243));
                } else {
                    view.setBackgroundColor(Color.rgb(235, 241, 221));
                }
                this.addView(view);
            }
        }
    }

    @Deprecated
    public void addOtherView(Context context, String[] strings) {
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                TextView view = new TextView(context);
                view.setText(strings[i]);
                view.setTextColor(Color.rgb(79, 129, 189));
                view.setGravity(Gravity.CENTER);
                if (i % 2 == 0) {
                    view.setBackgroundColor(Color.rgb(219, 238, 243));
                } else {
                    view.setBackgroundColor(Color.rgb(235, 241, 221));
                }
                this.addView(view);
            }
        }
    }

    public void addOtherView(Context context, String[][] strings) {
        this.mRow=strings.length;
        this.mCol=strings[0].length;
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                TextView view = new TextView(context);
                view.setText(strings[i][j]);
                view.setTextColor(Color.rgb(79, 129, 189));
                view.setGravity(Gravity.CENTER);
//                view.setPadding(10,10,10,10);
                if (i  == 0) {
                    view.setBackgroundColor(Color.rgb(219, 238, 243));
                } else {
                    view.setBackgroundColor(Color.rgb(235, 241, 221));
                }
                this.addView(view);
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(BORDER);
        paint.setColor(Color.rgb(79, 129, 189));
        paint.setStyle(Style.STROKE);
        // 绘制外部边框
        canvas.drawRect(START_X, START_Y, getWidth() - START_X, getHeight()
                - START_Y, paint);
        // 画列分割线
        for (int i = 1; i < mCol; i++) {
            canvas.drawLine((getWidth() / mCol) * i, START_Y,
                    (getWidth() / mCol) * i, getHeight() - START_Y, paint);
        }
        // 画行分割线
        for (int j = 1; j < mRow; j++) {
            canvas.drawLine(START_X, (getHeight() / mRow) * j, getWidth()
                    - START_X, (getHeight() / mRow) * j, paint);
        }
        super.dispatchDraw(canvas);
    }

    /*
     * getChildCount和getChildAt方法 这两个方法用于获取该容器控件中子控件的数目和位置， 便于我们对子控件的排版和布局
     * onMeasure方法 这个方法是用来测量子控件大小的，它在onLayout方法之前被调用，
     * 测量了子控件的大小尺寸，然后可以绘制子控件在容器组件中的布局位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = START_X + BORDER;
        int y = START_Y + BORDER;
        int i = 0;
        int count = getChildCount();
        for (int j = 0; j < count; j++) {
            View child = getChildAt(j);
            child.layout(x, y, x + getWidth() / mCol - BORDER * 2, y
                    + getHeight() / mRow - BORDER * 2);
            //修改布局位置
            if (i >= (mCol - 1)) {
                i = 0;
                x = START_X + BORDER;
                y += getHeight() / mRow;
            } else {
                i++;
                x += getWidth() / mCol;
            }
        }
    }


}