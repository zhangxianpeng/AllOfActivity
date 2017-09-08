package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyinterenet.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.allofactivity.R;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyinterenet.activity.bean.CityWeather;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyinterenet.activity.service.GetRequest_Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtyInternet extends Activity {
    private Button btn_get;
    private TextView tv_preview;

    @Override
    protected void onCreate(Bundle w) {
        super.onCreate(w);
        setContentView(R.layout.aty_internet);
        btn_get = (Button)findViewById(R.id.btn_getResponse);
        tv_preview = (TextView)findViewById(R.id.tv_preview);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGetRequest();
            }
        });
    }

    /**
     * 网络请求方法
     */
    private void doGetRequest() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建网络接口实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        //封装网络请求参数
        Call<CityWeather> call = request.getCall("310","2017-07-15","0af6f21a9c2867833ecb4f2b6849dd11");
        //发送网络请求
        call.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                //请求成功，解析响应
                System.out.println(response.body().getCity_name());
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
              //请求失败，输出信息
                System.out.println("连接失败");
            }
        });
    }


}
