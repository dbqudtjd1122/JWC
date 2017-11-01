package com.example.bsyoo.jwc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.bsyoo.jwc.adapter.Adapter_Event;
import com.example.bsyoo.jwc.adapter.GroupData;
import com.example.bsyoo.jwc.model.Model_Event;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    private ExpandableListView ExpandableListView;
    private Adapter_Event adapter;
    private ArrayList<GroupData> groupListDatas;
    private ArrayList<ArrayList<Model_Event>> childListDatas;
    private Model_Event event = new Model_Event();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        getSupportActionBar().setTitle("이벤트");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF000000));

        ExpandableListView = (android.widget.ExpandableListView) findViewById(R.id.expandable_list);
        setData();
        adapter = new Adapter_Event(this, groupListDatas, childListDatas);
        ExpandableListView.setAdapter(adapter);
        // Group 열려있게하기
        for(int i=0; i< adapter.getGroupCount(); i++){
            ExpandableListView.expandGroup(i);
        }

        // Group / Child 체크 이벤트
        ExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long id) {
                ImageView groupswich = (ImageView) findViewById(R.id.groupswich);

                BitmapDrawable a = (BitmapDrawable) getResources().getDrawable(R.drawable.swich0);
                Bitmap bitmap1 = a.getBitmap();

                BitmapDrawable b = (BitmapDrawable)((ImageView) findViewById(R.id.groupswich)).getDrawable();
                Bitmap bitmap2 = b.getBitmap();


                groupswich.setImageResource(R.drawable.swich1);
                /*if(bitmap1.equals(bitmap2)){
                    groupswich.setImageResource(R.drawable.swich1);
                } else {
                    groupswich.setImageResource(R.drawable.swich0);
                }*/

                return false;
            }
        });
        ExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(EventActivity.this, EventInfoActivity.class);
                startActivity(intent);
                return false;
            }
        });
        // 그룹이 닫힐 경우 이벤트
        ExpandableListView.setOnGroupCollapseListener(new android.widget.ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                ImageView groupswich = (ImageView) findViewById(R.id.groupswich);

                BitmapDrawable a = (BitmapDrawable) getResources().getDrawable(R.drawable.swich0);
                Bitmap bitmap1 = a.getBitmap();

                BitmapDrawable b = (BitmapDrawable)((ImageView) findViewById(R.id.groupswich)).getDrawable();
                Bitmap bitmap2 = b.getBitmap();

                if(bitmap1.equals(bitmap2)) {
                    groupswich.setImageResource(R.drawable.swich1);
                } else {
                    groupswich.setImageResource(R.drawable.swich0);
                }
            }
        });
    }


    private void setData(){
        groupListDatas = new ArrayList<GroupData>();
        childListDatas = new ArrayList<ArrayList<Model_Event>>();
        int sizeList = 0;
        groupListDatas.add(new GroupData("진행 중인 이벤트"));
        childListDatas.add(new ArrayList<Model_Event>());

        childListDatas.get(sizeList).add(new Model_Event("[이벤트] CCTV 렌탈 서비스", "2017-08-16" , R.drawable.rental330));
        childListDatas.get(sizeList).add(new Model_Event("[이벤트] JWC & Dahua 컨퍼런스", "2017-07-21" , R.drawable.conference_330));
        childListDatas.get(sizeList).add(new Model_Event("[이벤트] 협력업체 특별혜택", "2017-06-29" , R.drawable.benefit_330));
        groupListDatas.add(new GroupData("종료된 이벤트"));
        childListDatas.add(new ArrayList<Model_Event>());
        sizeList++;
        childListDatas.get(sizeList).add(new Model_Event("[이벤트] 화질은 HD급! 가격은 Light급! JDO-4008", "2017-07-16" , R.drawable.mp_330));
        //childListDatas.get(sizeList).add(new Model_Event("딸기빙수", 9000, "맛있쪙"));
        //childListDatas.get(sizeList).add(new Model_Event("초코빙수", 8500, "달아달아"));
    }
}
