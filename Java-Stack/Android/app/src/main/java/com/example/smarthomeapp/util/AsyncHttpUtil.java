package com.example.smarthomeapp.util;

import android.content.Context;

import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.Set;


public class AsyncHttpUtil {

    public static String TAG = AsyncHttpUtil.class.getName();
    final static int TIME_OUT = 10000;
    final private static AsyncHttpClient client;
    private static PersistentCookieStore cookieStore;
    private static Context applicationContext = null;
    private static final String IDS = "ids";

    static {
        client = new AsyncHttpClient();
    }

    private AsyncHttpUtil() {
    }

    public static void initAsyncHttpClient(Context context) {
        if (applicationContext == null) {
            applicationContext = context;

            // set default timeout
            client.setTimeout(TIME_OUT);

//            // set cookie store
//            cookieStore = new PersistentCookieStore(applicationContext);
//            client.setCookieStore(cookieStore);
        }
    }

    public static void requestJsonViaGet(Context context, String url, HttpResultProcessListener listener) {
        client.get(context, url, null, new MiFitJsonHttpResponseHandler(context, listener));
    }

    public static void requestJsonViaGetWithIDParam(Context context, String url, HttpResultProcessListener listener, Set<Integer> idSet) {
        RequestParams requestParams = new RequestParams();
        for (int id : idSet) {
            requestParams.add(IDS, id + "");
        }
        client.get(context, url, requestParams, new MiFitJsonHttpResponseHandler(context, listener));
    }

    public static void requestJson(Context context, String url, String paramJson, HttpResultProcessListener listener) {
        try {
            StringEntity stringEntity = new StringEntity(paramJson);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            client.post(context, url, stringEntity, "application/json", new MiFitJsonHttpResponseHandler(context, listener));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static <T> void requestJson(Context context, String url, HttpResultProcessListener listener) {
        requestJson(context, url, "", listener);
    }


    public static AsyncHttpClient getClient() {
        return client;
    }

}

class MiFitJsonHttpResponseHandler extends BaseJsonHttpResponseHandler {

    private static final String TAG = MiFitJsonHttpResponseHandler.class.getName();

    private HttpResultProcessListener listener;
    private Context context;

    MiFitJsonHttpResponseHandler(Context context, HttpResultProcessListener listener) {
        this(DEFAULT_CHARSET, context, listener);
    }

    MiFitJsonHttpResponseHandler(String encoding, Context context, HttpResultProcessListener listener) {
        super(encoding);
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object o) {
        listener.processing(statusCode, rawJsonResponse);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object o) {
//        listener.processing(statusCode, rawJsonResponse);
    }

    @Override
    protected Object parseResponse(String s, boolean b) throws Throwable {
        return null;
    }

}



