package com.example.greengym_admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    //listView 관련
    private ListView report_list;
    private ListAdapter ListAdapter;
    private ArrayList<item> item;
    private Button detail;
    private Button delete;

    //dialog 관련
    private TextView date_dialog;
    private TextView park_dialog;
    private TextView name_dialog;
    private TextView phone_dialog;
    private TextView content_dialog;
    private Button cancel;
    private Button report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("신고 관리");

        //listView 관련
        report_list = (ListView) findViewById(R.id.report_list);
        detail = (Button) findViewById(R.id.detail);
        delete = (Button) findViewById(R.id.delete);

        //아이템 추가 - (임의로 텍스트 넣은 것)
        item = new ArrayList<item>();
        for(int i = 1; i < 11; i++){
            item.add(new item("공원" + i, "2021-5-" + i));
        }

        //리스트뷰-어댑터 연결
        ListAdapter = new ListAdapter(ReportActivity.this, item);
        report_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        report_list.setAdapter(ListAdapter);

        //자세히 버튼
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dialog 생성
                View dialogView = getLayoutInflater().inflate(R.layout.activity_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(dialogView);
                final AlertDialog detail_dialog = builder.create();
                detail_dialog.setCancelable(false);
                detail_dialog.show();

                date_dialog = (TextView) dialogView.findViewById(R.id.date_dialog);
                park_dialog = (TextView) dialogView.findViewById(R.id.park_dialog);
                name_dialog = (TextView) dialogView.findViewById(R.id.name_dialog);
                phone_dialog = (TextView) dialogView.findViewById(R.id.phone_dialog);
                content_dialog = (TextView) dialogView.findViewById(R.id.content_dialog);
                cancel = (Button) dialogView.findViewById(R.id.cancel);
                report = (Button) dialogView.findViewById(R.id.report);

                //********************(임의로 텍스트 넣은 것)
                date_dialog.setText("2021-5-7");
                park_dialog.setText("개나리공원");
                name_dialog.setText("김하하");
                phone_dialog.setText("01035486512");
                content_dialog.setText("개나리공원 운동기구 신고합니다.");
                //********************

                //dialog 닫기 버튼
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        detail_dialog.dismiss();
                    }
                });

                //dialog 신고 버튼
                report.setOnClickListener(new View.OnClickListener() { //******************* 구청 url 넣어주세요.
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });

        //삭제 버튼
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder list_dialog = new AlertDialog.Builder(ReportActivity.this);
                list_dialog.setTitle("삭제");
                list_dialog.setMessage("정말로 삭제하시겠습니까?");

                list_dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //********************* 데이터 삭제 시 DB에도 없어지도록 해야합니다.
                        int count, checked;
                        count = ListAdapter.getCount();

                        if(count > 0){
                            checked = report_list.getCheckedItemPosition();
                            if(checked > -1 && checked < count){
                                item.remove(checked);
                                report_list.clearChoices();
                                ListAdapter.notifyDataSetChanged();
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