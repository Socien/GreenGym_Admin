package com.example.greengym_admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EquipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동 기구 관리");
    }
}