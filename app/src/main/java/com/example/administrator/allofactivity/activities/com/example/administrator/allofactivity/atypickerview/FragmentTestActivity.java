package com.example.administrator.allofactivity.activities.com.example.administrator.allofactivity.atypickerview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.allofactivity.R;

public class FragmentTestActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenttest);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_activity_main, new TestFragment());
        fragmentTransaction.commitAllowingStateLoss();

    }
}
