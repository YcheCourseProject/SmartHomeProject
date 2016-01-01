/*应用程序入口
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.smarthomeapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.sqllite.MyDBhelper;
import com.example.smarthomeapp.ssoauth.WBAuthActivity;
import com.example.smarthomeapp.thirdparty.sina.AccessTokenKeeper;
//import com.example.smarthomeapp.thirdparty.sina.CalendarAccess;
import com.example.smarthomeapp.thirdparty.sina.Constants;
import com.example.smarthomeapp.thirdparty.sina.EventDetection;
//import com.example.smarthomeapp.thirdparty.sina.GetSmsInPhone;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sina.weibo.sdk.openapi.models.User;
import com.sina.weibo.sdk.utils.LogUtil;

import java.util.ArrayList;

//SMStest

/**
 * 该类主要演示了如何使用微博 OpenAPI 来获取以下内容： <li>获取最新的公共微博 <li>获取当前登录用户及其所关注用户的最新微博
 * 
 * @author SINA
 * @since 2013-11-24
 */
public class WBStatusAPIActivity extends Activity {

	private static final String TAG = WBStatusAPIActivity.class.getName();
	private static final String FILENAME = "weibodata";
	/** UI 元素：listView */
	private ListView mFuncListView;
	/** 功能列表 */
	private String[] mFuncList;
	/** 当前Token信息 */
	private Oauth2AccessToken mAccessToken;
	/** 用于获取微博信息流等操作的API */
	private StatusesAPI mStatusesAPI;
	/** 鐢ㄦ埛淇℃伅鎺ュ彛 */
	private UsersAPI mUsersAPI;
	/**
	 * @see {@link Activity#onCreate}
	 */ 	

	private LinearLayout layout_holder;
	private Context context;
	private Handler handler;
	private Button mybtn = null;
	private TextView tv;//mm
	private void findViews() {
		layout_holder = (LinearLayout) this.findViewById(R.id.layout_statuses);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_wbstatus);

		findViews();
		context = this;

		tv = (TextView)this.findViewById(R.id.sms_view);
		
		
		mAccessToken = AccessTokenKeeper.readAccessToken(this);//2

		// 对statusAPI实例化
		mStatusesAPI = new StatusesAPI(this, Constants.APP_KEY, mAccessToken);

		mStatusesAPI.userTimeline(0L, 0L, 100, 1, false, 0, false, new DIYListener(this, handler));

		// 按键跳转到授权WBAuthActivity
		mybtn = (Button) super.findViewById(R.id.authbtn);// 跳转按键授权
		mybtn.setOnClickListener(new Onclicklistener());// 跳转按键授权
	}

	private class Onclicklistener implements OnClickListener {
		public void onClick(View view) {
			Intent it = new Intent(WBStatusAPIActivity.this,
					WBAuthActivity.class);
			WBStatusAPIActivity.this.startActivityForResult(it, 1);
			 WBStatusAPIActivity.this.finish();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	}
	

	
	private class DIYListener implements RequestListener {
		Context context;
		Handler handler;
		DIYListener(Context context, Handler handler) {
			this.context = context;
			this.handler = handler;
		}

		public void onComplete(String response) {

			SQLiteDatabase db;
			String WeiboContent="\n微博内容\n";
			if (!TextUtils.isEmpty(response)) {

				LogUtil.i(TAG, response);

				if (response.startsWith("{\"statuses\"")) {

					// 调用 StatusList#parse 解析字符串成微博列表对象
					StatusList statuses = StatusList.parse(response);
					ArrayList<Status> statuslist = statuses.statusList;

					MyDBhelper myDBhelper = new MyDBhelper(context);
					db = myDBhelper.getWritableDatabase();

					for (int i = 0; i < statuslist.size(); i++) {
						Status status = statuslist.get(i);
						String time = status.created_at;
						//String location1=status.geo.longitude;//经度
						//String location2=status.geo.latitude;//纬度
					
						String text = status.text;
						User user = status.user;
						String name = user.name;
					    /**事件检测并写入数据库*/
						//String[] result = new String[EventDetection.detection(text, time, 1).size()];
						//EventDetection.detection(text, time, 1).toArray(result);
						
						ArrayList<String[]> results = EventDetection.detection(text, time, 1);
						if (results.size() > 0){
							String[] result = (String[]) results.get(0);
										
                            try {
                                    //
                                    String sqlString = "insert into socialrecord ([startTime],[endTime],[messageId],[messageContent]) values ('"
                                            + result[1] + "','" + result[2] + "','" + result[0] + "','" + text + "')";
                                    db.execSQL(sqlString);
                                    //db.close();
        //							Cursor cursor = db
        //									.rawQuery(
        //											"select [key],[event],[description] from [key_event]",
        //											null);
        //							while (cursor.moveToNext()) {
        //								String key = cursor.getString(0);
        //								Log.i("key", key);
        //								if (text.contains(key)) {
        //									String event = cursor.getString(1);
        //									Log.i("ok", "insert");
        //									db.execSQL("insert into [time_event](timestamp,event) values('"
        //											+ time + "','" + event + "')");
        //									db.close();
        //								}
        //							}
                                    Toast.makeText(WBStatusAPIActivity.this, "weibo写入成功",
                                            Toast.LENGTH_LONG).show();
                                }
                                catch (Exception e) {
                                    Toast.makeText(WBStatusAPIActivity.this, "weibo写入错误",
                                            Toast.LENGTH_LONG).show();
                                }
                                }
                                 String allString = time + ";" + text + ";" + name;
                                 WeiboContent=WeiboContent+"\n"+allString;
                                // 鏇存柊UI
        //						Message msg = new Message();
        //						Bundle bundle = new Bundle();
        //						bundle.putString("data", allString);
        //						msg.setData(bundle);
        //						handler.sendMessage(msg);
                            }
					
					/**滚动显示获取的短信+微博内容*/
			        //String CalendarContent=new CalendarAccess(context).calendarAccess();//2015 7-6读取日历
				    //String strSMS = new GetSmsInPhone(context).getSmsInPhone();
			        //tv.setMovementMethod(ScrollingMovementMethod.getInstance());
			        //tv.setText(strSMS+WeiboContent+CalendarContent);
				    /**滚动显示获取的短信+微博内容*/
					
					db.close();
					if (statuses != null && statuses.total_number > 0) {
						Toast.makeText(WBStatusAPIActivity.this,
								"获取微博信息流成功, 条数: " + statuses.statusList.size(),
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(WBStatusAPIActivity.this, response,
							Toast.LENGTH_LONG).show();
				}

			}

		}

		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub

		}

	}

}
