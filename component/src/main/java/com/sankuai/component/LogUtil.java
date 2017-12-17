package com.sankuai.component;

import android.util.Log;

public class LogUtil {
    public final static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d("jamal.jo", "******debug*******");
        }else {
            Log.d("jamal.jo", "******release*******");
        }
    }

    public final static void i(String msg) {
        Log.d("jamal.jo", msg);
    }
}