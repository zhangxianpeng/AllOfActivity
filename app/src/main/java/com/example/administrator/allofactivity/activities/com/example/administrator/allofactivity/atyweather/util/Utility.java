package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.util;

import android.text.TextUtils;

import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.db.CoolWeatherDB;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.model.City;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.model.Country;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.model.Province;

/**
 * Created by Administrator on 2017/9/6/006.
 * 解析“代号|城市，代号|城市”的工具类
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces!=null&&allProvinces.length>0){
                for(String p:allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的城市级数据
     */
    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities!=null&&allCities.length>0){
                for(String c:allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的乡县级数据
     */
    public synchronized static boolean handleCountriesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCountries = response.split(",");
            if(allCountries!=null&&allCountries.length>0){
                for(String c:allCountries){
                    String[] array = c.split("\\|");
                    Country country = new Country();
                    country.setCountryCode(array[0]);
                    country.setCountryName(array[1]);
                    country.setCityId(cityId);
                    //将解析出来的数据存储到Province表
                    coolWeatherDB.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }

}
