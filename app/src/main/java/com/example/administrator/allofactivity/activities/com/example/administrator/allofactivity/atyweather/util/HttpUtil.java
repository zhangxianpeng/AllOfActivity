package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/9/6/006.
 * 和服务器交互工具类
 */

public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallbackListner listner){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    if(listner!=null){
                        //回调OnFinish()方法
                        listner.OnFinish(response.toString());
                    }
                }catch (Exception e){
                    if(listner!=null){
                        //回调OnError()方法
                        listner.OnError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
