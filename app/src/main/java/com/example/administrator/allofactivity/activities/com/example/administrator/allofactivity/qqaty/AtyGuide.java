package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.qqaty;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.administrator.allofactivity.R;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.qqaty.utilsofqqayt.SpUtil;

import static com.example.administrator.allofactivity.Utils.IntentUtil.showActivity;

/**
 *欢迎页面
 * 逻辑：1、先判断程序是否是第一次启动，如果是第一次启动的话就进入功能实用导航
 *      2、如果不是，显示启动屏，2s后进入首页
 */

public class AtyGuide extends AppCompatActivity {

    public static final String FIRST_OPEN = "first_open";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //判断是否第一次开启应用
        boolean isFirstOpen = SpUtil.getBoolean(this,FIRST_OPEN);
        //如果是第一次启动，则先进入功能引导页
        if(!isFirstOpen){
            showActivity(this,AtyWelcome.class);
            finish();
            return;
        }
            //如果不是第一次启动app，则正常显示启动屏
            setContentView(R.layout.activity_aty_guide);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterQQActivity();
            }
        },2000);

    }

    private void enterQQActivity() {
        showActivity(this, AtyQQ.class);
    }

}
