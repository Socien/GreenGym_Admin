package com.example.greengym_admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Equip_detail extends AppCompatActivity {

    //listView 관련
    private ListView equipDetail_list;
    private Equip_ListAdapter Equip_ListAdapter;
    private ArrayList equipDetail_item;
    private Button equip_add;
    private Button equip_delete;
    private Spinner equip_spinner;
    private Button equip_cancel;
    private Button real_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_detail);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("상세 정보");

        equipDetail_list = (ListView) findViewById(R.id.equipDetail_list);
        equip_add = (Button) findViewById(R.id.equip_add);
        equip_delete = (Button) findViewById(R.id.equip_delete);

        //아이템 추가 - **************************************************************** 임의로 넣음
        equipDetail_item = new ArrayList();
        equipDetail_item.add(new item("공중걷기"));
        equipDetail_item.add(new item("파도타기"));
        equipDetail_item.add(new item("3단철봉"));
        equipDetail_item.add(new item("거꾸로 매달리기"));
        equipDetail_item.add(new item("평행봉"));

        //리스트뷰-어댑터 연결
        Equip_ListAdapter = new Equip_ListAdapter(Equip_detail.this, equipDetail_item);
        equipDetail_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        equipDetail_list.setAdapter(Equip_ListAdapter);

        //운동기구 추가 버튼
        equip_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dialog 생성
                View dialogView = getLayoutInflater().inflate(R.layout.equip_spinner, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(dialogView);
                final AlertDialog add_dialog = builder.create();
                add_dialog.setCancelable(false);
                add_dialog.show();

                equip_cancel = (Button) dialogView.findViewById(R.id.equip_cancel);
                real_add = (Button) dialogView.findViewById(R.id.real_add);

                ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(Equip_detail.this, R.array.equip_add_listArray, android.R.layout.simple_spinner_dropdown_item);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                equip_spinner = (Spinner) dialogView.findViewById(R.id.equip_spinner);
                equip_spinner.setAdapter(spinnerAdapter);

                //dialog 닫기 버튼
                equip_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_dialog.dismiss();
                    }
                });

                //dialog 추가 버튼
                real_add.setOnClickListener(new View.OnClickListener() { //********************* 데이터 삭제 시 DB에도 추가되도록 해야합니다.
                    @Override
                    public void onClick(View v) {
                        equipDetail_item.add(new item(equip_spinner.getSelectedItem().toString()));
                        Equip_ListAdapter.notifyDataSetChanged();
                        add_dialog.dismiss();
                    }
                });
            }
        });

        //운동기구 삭제 버튼
        equip_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder list_dialog = new AlertDialog.Builder(Equip_detail.this);
                list_dialog.setTitle("운동기구 삭제");
                list_dialog.setMessage("정말로 삭제하시겠습니까?");

                list_dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //********************* 데이터 삭제 시 DB에도 없어지도록 해야합니다.
                        int count, checked;
                        count = Equip_ListAdapter.getCount();

                        if(count > 0){
                            checked = equipDetail_list.getCheckedItemPosition();
                            if(checked > -1 && checked < count){
                                equipDetail_item.remove(checked);
                                equipDetail_list.clearChoices();
                                Equip_ListAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

                list_dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                list_dialog.setCancelable(false);
                list_dialog.show();
            }
        });
    }
}