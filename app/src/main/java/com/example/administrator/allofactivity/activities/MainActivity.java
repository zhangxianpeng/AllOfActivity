package com.example.administrator.allofactivity.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.allofactivity.R;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyinterenet.activity.AtyInternet;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atypickerview.AtyPickerView;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atyweather.activity.AtyWeather;
import com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.qqaty.AtyGuide;
import static com.example.administrator.allofactivity.Utils.IntentUtil.showActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_weather,btn_QQ,btn_socket,btn_novle,btn_pic,
                   btn_wubi,btn_flower,btn_2048,btn_third_login,
                   btn_music,btn_pay,btn_pickerview,btn_internet,btn_basketball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_weather = (Button)findViewById(R.id.btn_weather);
        btn_QQ = (Button)findViewById(R.id.btn_QQ);
        btn_socket = (Button)findViewById(R.id.btn_socket_communication);
        btn_novle = (Button)findViewById(R.id.btn_novel_book);
        btn_pic = (Button)findViewById(R.id.btn_make_pictures_beautiful);
        btn_wubi = (Button)findViewById(R.id.btn_wubi_input);
        btn_flower = (Button)findViewById(R.id.btn_flower_pictures);
        btn_2048 = (Button)findViewById(R.id.btn_game_2048);
        btn_third_login = (Button)findViewById(R.id.btn_third_login);
        btn_music = (Button)findViewById(R.id.btn_flash);
        btn_pay = (Button)findViewById(R.id.btn_pay);
        btn_pickerview = (Button)findViewById(R.id.btn_pickerview);
        btn_internet = (Button)findViewById(R.id.btn_internet);
        btn_basketball = (Button)findViewById(R.id.btn_basketball);

        btn_weather.setOnClickListener(this);
        btn_QQ.setOnClickListener(this);
        btn_socket.setOnClickListener(this);
        btn_novle.setOnClickListener(this);
        btn_pic.setOnClickListener(this);
        btn_wubi.setOnClickListener(this);
        btn_flower.setOnClickListener(this);
        btn_2048.setOnClickListener(this);
        btn_third_login.setOnClickListener(this);
        btn_music.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        btn_pickerview.setOnClickListener(this);
        btn_internet.setOnClickListener(this);
        btn_basketball.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_weather:
                showActivity(this,AtyWeather.class);
                break;
            case R.id.btn_QQ:
                showActivity(this,AtyGuide.class);
                break;
            case R.id.btn_socket_communication:
                showActivity(this,AtySocket.class);
                break;
            case R.id.btn_novel_book:
                showActivity(this,AtyNovel.class);
                break;
            case R.id.btn_make_pictures_beautiful:
                showActivity(this,AtyBeautifulpictures.class);
                break;
            case R.id.btn_wubi_input:
                showActivity(this,AtyWubi.class);
                break;
            case R.id.btn_flower_pictures:
                showActivity(this,AtyFlower.class);
                break;
            case R.id.btn_game_2048:
                showActivity(this,AtyGame.class);
                break;
            case R.id.btn_third_login:
                showActivity(this,AtyWubi.class);
                break;
            case R.id.btn_flash:
                showActivity(this,AtyFlash.class);
                break;
            case R.id.btn_pay:
                showActivity(this,AtyGame.class);
                break;
            case R.id.btn_pickerview:
                showActivity(this,AtyPickerView.class);
            case R.id.btn_internet:
                showActivity(this,AtyInternet.class);
            case R.id.btn_basketball:
                showActivity(this,AtyBasketball.class);
            default:
                break;
        }
    }
}
