package com.example.greengym_admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button equip, report;
    private long backTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Green Gym (관리자용)");

        equip = (Button) findViewById(R.id.equip);
        report = (Button) findViewById(R.id.report);

        //운동 기구 관리
        equip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EquipActivity.class);
                startActivity(intent);
            }
        });

        //신고 관리
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() > backTime + 2000){
            backTime = System.currentTimeMillis();
            Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(System.currentTimeMillis() <= backTime + 2000){
            finish();
            return;
        }
    }
}