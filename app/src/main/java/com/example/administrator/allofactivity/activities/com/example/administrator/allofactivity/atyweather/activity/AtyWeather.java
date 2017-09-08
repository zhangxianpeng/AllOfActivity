package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.activity;



import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.allofactivity.R;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.db.CoolWeatherDB;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.model.City;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.model.Country;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.model.Province;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.util.HttpCallbackListner;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.util.HttpUtil;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class AtyWeather extends Activity {
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTRY = 2;

    private ProgressDialog progressDialog;
    private TextView tv_title;
    private ListView lv_view;
    private ArrayAdapter<String> adapter;
    private CoolWeatherDB coolWeatherDB;
    private List<String> dataList = new ArrayList<String>();

    private List<Province> provinceList;  //省列表
    private List<City> cityList;
    protected List<Country> countryList;

    private Province selectedProvince;  //选中的省份
    private City selectedCity;
    private int currentLevel;    //当前选中的级别

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        lv_view = (ListView)findViewById(R.id.lv_view);
        tv_title = (TextView)findViewById(R.id.tv_title);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        lv_view.setAdapter(adapter);
        coolWeatherDB = coolWeatherDB.getInstance(this);
        lv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
                if(currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(index);
                    queryCities();
                }else if(currentLevel == LEVEL_CITY){
                    queryCountries();
                }
            }
        });
        queryProvince();
    }


    /**
     * 查询全国所有的省份，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryProvince() {
        provinceList = coolWeatherDB.loadProvinces();
        if(provinceList.size()>0){
            dataList.clear();
            for(Province province : provinceList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            lv_view.setSelection(0);
            tv_title.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        }else {
            queryFeomServer(null,"Province");
        }
    }

    /**
     * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryCities() {
        cityList = coolWeatherDB.loadCities(selectedProvince.getId());
        if(cityList.size()>0){
            dataList.clear();
            for(City city : cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            lv_view.setSelection(0);
            tv_title.setText(selectedProvince.getProvinceName());
            currentLevel = LEVEL_CITY;
        }else {
            queryFeomServer(selectedProvince.getProvinceCode(),"city");
        }
    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryCountries() {
        countryList = coolWeatherDB.loadCountries(selectedCity.getId());
        if(countryList.size()>0){
            dataList.clear();
                for(Country country : countryList){
                dataList.add(country.getCountryName());
            }
            adapter.notifyDataSetChanged();
            lv_view.setSelection(0);
            tv_title.setText(selectedCity.getCityName());
            currentLevel = LEVEL_COUNTRY;
        }else {
            queryFeomServer(selectedCity.getCityCode(),"country");
        }
    }

    /**
     * 根据传入的代号和类型从服务器上查询省市县数据
     * @param code
     * @param type
     */
    private void queryFeomServer(final String code,final String type) {
        String adress;
        if(!TextUtils.isEmpty(code)){
            adress = "http://www.weather.com.cn/data/list3/city"+code+".xml";
        }else {
            adress = "http://www.weather.com.cn/data/list3/city.xml";
        }
        showProgressDialog();
        HttpUtil.sendHttpRequest(adress, new HttpCallbackListner() {
            @Override
            public void OnFinish(String response) {
                boolean result = false;
                if("province".equals(type)){
                    result = Utility.handleProvincesResponse(coolWeatherDB,response);
                }else if("city".equals(type)){
                    result = Utility.handleCitiesResponse(coolWeatherDB,response,selectedProvince.getId());
                }else if("country".equals(type)){
                    result = Utility.handleCountriesResponse(coolWeatherDB,response,selectedCity.getId());
                }
                if(result){
                    //通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if("province".equals(type)){
                                queryProvince();
                            }else if("city".equals(type)){
                                queryCities();
                            }else if("country".equals(type)){
                                queryCountries();
                            }
                        }
                    });
                }
            }

            @Override
            public void OnError(Exception e) {
                //通过runOnUiThread()方法回到主线程处理逻辑
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(AtyWeather.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }



    /**
     * 显示对话框
     */
    private void showProgressDialog() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载数据。。。。。");
        }
        progressDialog.show();
    }

    /**
     * 关闭对话框
     */
    private void closeProgressDialog() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
    /**
     * 捕捉Back按键，根据当前的级别来判断，此时应该返回什么列表或是直接退出
     */
    @Override
    public void onBackPressed() {
        if(currentLevel == LEVEL_COUNTRY){
            queryCities();
        }else if(currentLevel == LEVEL_CITY){
            queryProvince();
        }else {
            finish();
        }
    }
}
