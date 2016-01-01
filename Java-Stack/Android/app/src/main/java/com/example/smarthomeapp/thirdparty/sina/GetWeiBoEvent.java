package com.example.smarthomeapp.thirdparty.sina;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.example.smarthomeapp.model.ActivityType;
import com.example.smarthomeapp.model.SocialInfo;
import com.example.smarthomeapp.model.SocialSource;
import com.example.smarthomeapp.util.GsonUtil;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sina.weibo.sdk.openapi.models.User;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by 家琪 on 2015/7/19.
 */
public class GetWeiBoEvent {

    private static final String TAG4 = "GetWeiBoEvent";
    private static int WEIBO = 5;

    private static GetWeiBoEvent getWeiBoEvent;
    private static Context context;
    private static int interval;

    private static long latestId = 3866420464724979L ;

    private static Handler mHandler;


    /** 当前Token信息 */
    private static Oauth2AccessToken mAccessToken;
    /** 用于获取微博信息流等操作的API */
    private static StatusesAPI mStatusesAPI;

    private GetWeiBoEvent(Context c, Handler handler){
        context = c;
        mHandler = handler;
    }

    /**单例模式构造*/
    public static GetWeiBoEvent getInstance(Context c, Handler handler){
        if(getWeiBoEvent != null && context == c && mHandler == handler){
            return getWeiBoEvent;
        }else{
            return getWeiBoEvent = new GetWeiBoEvent(c, handler);
        }
    }

    public static void setLatestId(long id){
        latestId = id;
    }

    public static long getLatestId(){
        return latestId;
    }

    public void getWeiBoEvent(){

        mAccessToken = AccessTokenKeeper.readAccessToken(context);

        if (is_valid(mAccessToken)){

            Log.d(TAG4, "access to WeiBo");

            // 对statusAPI实例化
            mStatusesAPI = new StatusesAPI(context, Constants.APP_KEY, mAccessToken);

            mStatusesAPI.userTimeline(0L, 0L, 100, 1, false, 0, false, new DIYListener(context, mHandler));


        }else{
            Log.d(TAG4, "fail to access to WeiBo");
        }

    }

    /**
     * 判断是否有足够权限获得微博内容
     * @param oauth2AccessToken
     * @return
     */
    private boolean is_valid(Oauth2AccessToken oauth2AccessToken){

        if (!oauth2AccessToken.getUid().equals("") &&
                !oauth2AccessToken.getToken().equals("") &&
                    oauth2AccessToken.getExpiresTime()>System.currentTimeMillis()){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 内部类 处理获得的微博
     */
    private class DIYListener implements RequestListener {

        Context context;
        Handler handler;
        long new_id;

        DIYListener(Context context, Handler handler) {
            this.context = context;
            this.handler = handler;
        }

        /**
         * 进行语意分析， 并通知UI线程处理
         * @param response
         */
        public void onComplete(String response) {

            if (!TextUtils.isEmpty(response)) {

                Log.d(TAG4, response);

                if (response.startsWith("{\"statuses\"")) {

                    // 调用 StatusList#parse 解析字符串成微博列表对象

                    StatusList statuses = StatusList.parse(response);
                    ArrayList<Status> statuslist = statuses.statusList;

                    ArrayList<SocialInfo> socialList = new ArrayList<SocialInfo>();

                    new_id = latestId;

                    for (int i = 0; i < statuslist.size(); i++) {

                        Status status = statuslist.get(i);
                        String time = status.created_at;
                        String text = status.text;
                        long id = Long.valueOf(status.id);
                        User user = status.user;
                        String name = user.name;

                        if (id <= latestId){
                            Log.d(TAG4, "no new event");
                            break;
                        }else{
                            if (id>new_id){
                                new_id = id;
                            }
                            ArrayList<String[]> results = EventDetection.detection(text, time, 1);
                            if (results.size() > 0) {

                                String[] result = (String[]) results.get(0);
                                Integer activity_type_id = Integer.valueOf(result[0]);

                                SocialInfo socialInfo = new SocialInfo();
                                SocialSource socialSource = new SocialSource();
                                socialSource.setSourceTypeId(1);
                                socialInfo.setStartTime(result[1]);
                                socialInfo.setEndTime(result[2]);

                                ActivityType activityType = new ActivityType();
                                activityType.setActivityTypeId(activity_type_id);
                                socialInfo.setActivityType(activityType);

                                socialInfo.setSocialSource(socialSource);
                                socialInfo.setActivitySentTime(new Timestamp(System.currentTimeMillis()));
                                socialList.add(socialInfo);

                            }
                        }
                    }
                    latestId = new_id;
                    Gson gson = GsonUtil.create();
                    if (socialList.isEmpty()){
                        Log.d(TAG4, "empty");
                    }else{
                        String str = gson.toJson(socialList);
                        Log.d(TAG4, str);
                        Message msg = new Message();
                        msg.what = WEIBO;
                        Bundle bundle = new Bundle();
                        bundle.putString("WEIBO", str);
                        bundle.putLong("WeiBoLatestID", latestId);
                        Log.d(TAG4, String.valueOf(latestId));
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }
                } else {
                    Log.d(TAG4, "no weibo content");
                }
            }else{
                Log.d(TAG4, "weibo content empty");
            }

        }

        @Override
        public void onWeiboException(WeiboException arg0) {
            // TODO Auto-generated method stub

        }

    }

}









