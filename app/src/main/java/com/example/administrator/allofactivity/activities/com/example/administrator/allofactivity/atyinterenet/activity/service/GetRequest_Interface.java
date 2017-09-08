package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyinterenet.activity.service;

import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyinterenet.activity.bean.CityWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/9/8/008.
 * 网络请求接口
 */

public interface GetRequest_Interface {
    /**
     * 第一部分
     * 封装网络请求地址
     */
    @GET("historyWeather/weather")
    //@get注解的作用：采用get方法发送网络请求
    Call<CityWeather> getCall(@Query("city_id") String city_id, @Query("weather_date") String weather_date,@Query("key") String key);
    //getCall() 接收网络请求数据的方法，如果想直接获得Responsebody中的内容,可以定义网络请求返回值为Call<Responsebody>
}
