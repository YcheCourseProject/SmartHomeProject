package com.example.shems.views;

import java.text.DecimalFormat;

import com.example.shems.util.UserDefined;
import com.example.smarthome.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableView2 extends ViewGroup { // ���ؼ�
	private static final int STARTX = 0;// ��ʼX����
	private static final int STARTY = 0;// ��ʼY����
	private static final int BORDER = 2;// ���߿���

	private int mRow;// ����
	private int mCol;// ����
	public double prices[]=new double[24];
 
	public TableView2(Context context, int row, int col, String[] strs) {
		super(context);
		if (row > 20 || col > 20) {
			this.mRow = 20;// ����20��ʱ����������Ϊ20��
			this.mCol = 20;// ����20��ʱ����������Ϊ20��
		} else if (row == 0 || col == 0) {
			this.mRow = 3;
			this.mCol = 3;
		} else {
			this.mRow = row;
			this.mCol = col;
		}
		// ����ӿؼ�
		this.addOtherView(context, strs);
	}

	public TableView2(Context context, int row, int col, double[] prices) {
		super(context);
		this.prices=prices;
		if (row > 20 || col > 20) {
			this.mRow = 20;// ����20��ʱ����������Ϊ20��
			this.mCol = 20;// ����20��ʱ����������Ϊ20��
		} else if (row == 0 || col == 0) {
			this.mRow = 3;
			this.mCol = 3;
		} else {
			this.mRow = row;
			this.mCol = col;
		}
		this.addOtherViewForSchedulingTable(context);
	}

	public void addOtherViewForSchedulingTable(Context context) {
		for (int i = 1; i <= mRow; i++) {
			for (int j = 1; j <= mCol; j++) {
				LayoutInflater flater = LayoutInflater.from(context);
				LinearLayout layout = (LinearLayout) flater.inflate(R.layout.item_tablerowcol, null);				
				TextView textViewTop=(TextView) layout.findViewById(R.id.textView_statement);
				TextView textViewBottom=(TextView) layout.findViewById(R.id.textView_price);
				
				StringBuilder sbBuilder = new StringBuilder();
				int index=(i - 1) * mCol + j - 1;
				double price = prices[index];
				DecimalFormat df = new DecimalFormat("0.000"); 
				String priceString=df.format(price);
				
				sbBuilder.append(index).append(":00-\n")
						.append(index+1).append(":00");
				textViewTop.setText(sbBuilder.toString());
				textViewTop.setTextColor(Color.YELLOW);
				textViewTop.setGravity(Gravity.CENTER_HORIZONTAL);	
				sbBuilder=new StringBuilder();
				
				
				
				textViewBottom.setTextColor(Color.WHITE);
		
				 
				int rgb[];
				if(price==-1)
					{
					rgb= UserDefined.rgb0;
					sbBuilder.append("expensive");
					textViewBottom.setTextScaleX(0.5f);
					}
				else if(price==-2)
					{rgb=UserDefined.rgb1;
					sbBuilder.append("NotSatisfied");
					textViewBottom.setTextScaleX(0.5f);
					}
				else if(price>0 && price<prices[18])
					{
					rgb=UserDefined.rgb2;
					sbBuilder.append("$").append(priceString);
					textViewBottom.setTextScaleX(1.0f);
					}
				else 
				{
					rgb=UserDefined.rgb3;
					sbBuilder.append("$").append(priceString);
					textViewBottom.setTextScaleX(1.0f);
				}
				textViewBottom.setText(sbBuilder.toString());
				layout.setBackgroundColor(Color.rgb(rgb[0],rgb[1],rgb[2]));
				//layout.setTextColor(Color.rgb(rgb[0],rgb[1],rgb[2]));
				//view.setGravity(Gravity.CENTER_VERTICAL);
//				layout.removeAllViews();
//				Button button=new Button(context);
//				button.setText("fdsf");
//				button.setBackgroundColor(Color.BLACK);
				//layout.addView(button,0,new LayoutParams(LayoutParams.FILL_PARENT,
//						LayoutParams.FILL_PARENT));
				//((LinearLayout) layout).setGravity(Gravity.CENTER);
				this.addView(layout,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); 
			//this.addView(layout);
			}
		}	 
	}

	public void addOtherView(Context context, String[] strs) {
		for (int i = 1; i <= mRow; i++) {
			for (int j = 1; j <= mCol; j++) {
				TextView view = new TextView(context);
				view.setText(strs[(i - 1) * mCol + j - 1]);
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

	@Override
	protected void dispatchDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStrokeWidth(BORDER);
		paint.setColor(Color.rgb(79, 129, 189));
		paint.setStyle(Style.STROKE);
		// �����ⲿ�߿�
		canvas.drawRect(STARTX, STARTY, getWidth() - STARTX, getHeight()
				- STARTY, paint);
		// ���зָ���
		for (int i = 1; i < mCol; i++) {
			canvas.drawLine((getWidth() / mCol) * i, STARTY,
					(getWidth() / mCol) * i, getHeight() - STARTY, paint);
		}
		// ���зָ���
		for (int j = 1; j < mRow; j++) {
			canvas.drawLine(STARTX, (getHeight() / mRow) * j, getWidth()
					- STARTX, (getHeight() / mRow) * j, paint);
		}
		super.dispatchDraw(canvas);
	}

	/*
	 * getChildCount��getChildAt���� �������������ڻ�ȡ�������ؼ����ӿؼ�����Ŀ��λ�ã� �������Ƕ��ӿؼ����Ű�Ͳ���
	 * onMeasure���� ������������������ӿؼ���С�ģ�����onLayout����֮ǰ�����ã�
	 * �������ӿؼ��Ĵ�С�ߴ磬Ȼ����Ի����ӿؼ�����������еĲ���λ��
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int x = STARTX + BORDER;
		int y = STARTY + BORDER;
		int i = 0;
		int count = getChildCount();
		for (int j = 0; j < count; j++) {
			View child = getChildAt(j);
			child.layout(x, y, x + getWidth() / mCol - BORDER * 2, y
					+ getHeight() / mRow - BORDER * 2);
			if (i >= (mCol - 1)) {
				i = 0;
				x = STARTX + BORDER;
				y += getHeight() / mRow;
			} else {
				i++;
				x += getWidth() / mCol;
			}
		}
	}

	public void setRow(int row) {
		this.mRow = row;
	}

	public void setCol(int col) {
		this.mCol = col;
	}
	  @Override

	   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	      // TODO Auto-generated method stub
	      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	      int childCount = getChildCount();
	      for(int i = 0; i < childCount; i++){
	         View v = getChildAt(i);
	         v.measure(widthMeasureSpec, heightMeasureSpec);
	      }   
	   }

}