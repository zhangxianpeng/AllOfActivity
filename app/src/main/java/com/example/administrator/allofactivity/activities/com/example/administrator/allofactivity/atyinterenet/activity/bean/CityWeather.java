package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyinterenet.activity.bean;

/**
 * Created by Administrator on 2017/9/8/008.
 * 返回数据存储的实体类
 */

public class CityWeather {
    private String city_name;  //城市名称
    private String day_weather;  //白天天气
    private String night_weatehr;  //晚上天气
    private int day_temp;    // 白天温度
    private int night_temp;   //晚上温度
    private String day_wind;   // 白天风向
    private String day_wind_comp;  // 白天风力

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDay_weather() {
        return day_weather;
    }

    public void setDay_weather(String day_weather) {
        this.day_weather = day_weather;
    }

    public String getNight_weatehr() {
        return night_weatehr;
    }

    public void setNight_weatehr(String night_weatehr) {
        this.night_weatehr = night_weatehr;
    }

    public int getDay_temp() {
        return day_temp;
    }

    public void setDay_temp(int day_temp) {
        this.day_temp = day_temp;
    }

    public int getNight_temp() {
        return night_temp;
    }

    public void setNight_temp(int night_temp) {
        this.night_temp = night_temp;
    }

    public String getDay_wind() {
        return day_wind;
    }

    public void setDay_wind(String day_wind) {
        this.day_wind = day_wind;
    }

    public String getDay_wind_comp() {
        return day_wind_comp;
    }

    public void setDay_wind_comp(String day_wind_comp) {
        this.day_wind_comp = day_wind_comp;
    }

    public String getOut(){
return "CityWeather{" +
        "city_name='" + city_name + '\'' +
        ", 白天天气='" + day_weather + '\'' +
        ", 晚上天气='" + night_weatehr + '\'' +
        ",白天气温='" + day_temp + '\'' +
        ",晚上气温='" + night_temp + '\'' +
        ",白天风向='" + day_wind + '\'' +
        ",白天风力='" + day_wind_comp + '\'' +
        '}';    }



}
