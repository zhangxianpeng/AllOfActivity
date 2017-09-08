package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.util;

/**
 * Created by Administrator on 2017/9/6/006.
 * 服务器请求接口回调
 */

public interface HttpCallbackListner {
    void OnFinish(String response);
    void OnError(Exception e);
}
