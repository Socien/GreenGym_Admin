package com.example.greengym_admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class EquipActivity extends AppCompatActivity {

    //listView 관련
    private ListView equip_list;
    private Equip_ListAdapter Equip_ListAdapter;
    private ArrayList equip_item;
    private Button equip_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동 기구 관리");

        equip_list = (ListView) findViewById(R.id.equip_list);
        equip_detail = (Button) findViewById(R.id.equip_detail);

        //아이템 추가 - 일부만 넣음 **************************** (서버 연결해서 넣을 수 있으면 넣어주세요.)
        equip_item = new ArrayList();
        equip_item.add(new item("가중나무어린이공원"));
        equip_item.add(new item("개나리어린이공원"));
        equip_item.add(new item("광나루로수변계류"));
        equip_item.add(new item("광남그린웨이 자투리땅"));
        equip_item.add(new item("광진숲나루"));
        equip_item.add(new item("구둘어린이공원"));
        equip_item.add(new item("구의2동마을마당"));
        equip_item.add(new item("구의2동제2마을마당"));
        equip_item.add(new item("구의어린이공원"));
        equip_item.add(new item("구의유수지"));
        equip_item.add(new item("군자동마을마당"));
        equip_item.add(new item("군자어린이공원"));
        equip_item.add(new item("금모래어린이공원"));
        equip_item.add(new item("긴고랑어린이공원"));
        equip_item.add(new item("남일어린이공원"));
        equip_item.add(new item("노유산어린이공원"));
        equip_item.add(new item("능동소공원"));
        equip_item.add(new item("대동마을공원"));
        equip_item.add(new item("동자어린이공원"));
        equip_item.add(new item("명성어린이공원"));
        equip_item.add(new item("목련어린이공원"));

        //리스트뷰-어댑터 연결
        Equip_ListAdapter = new Equip_ListAdapter(EquipActivity.this, equip_item);
        equip_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        equip_list.setAdapter(Equip_ListAdapter);

        //자세히 버튼
        equip_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Equip_detail.class);
                startActivity(intent);
            }
        });
    }
}