package com.example.bsyoo.jwc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.bsyoo.jwc.adapter.Adapter_Event;
import com.example.bsyoo.jwc.adapter.GroupData;
import com.example.bsyoo.jwc.hppt.Http_Notice;
import com.example.bsyoo.jwc.model.Model_Notice;
import com.example.bsyoo.jwc.user.SignUpActivity;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private ExpandableListView ExpandableListView;
    private Adapter_Event adapter;
    private ArrayList<GroupData> groupListDatas = new ArrayList<GroupData>();
    private ArrayList<ArrayList<Model_Notice>> childListDatas = new ArrayList<ArrayList<Model_Notice>>();
    private Model_Notice notice = new Model_Notice();

    private List<Model_Notice> noticeslist = new ArrayList<Model_Notice>();
    private int sizeList = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Status bar 색상 설정. (상태바)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.RED);
        }
        getSupportActionBar().setTitle("이벤트");
        // 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ExpandableListView = (android.widget.ExpandableListView) findViewById(R.id.expandable_list);

        new EventActivity.getEventList().execute("이벤트");


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

                return false;
            }
        });

        ExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(EventActivity.this, EventInfoActivity.class);

                notice = noticeslist.get(childPosition);
                intent.putExtra("event", notice);
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

    private void setData(List<Model_Notice> list, String group) {

        groupListDatas.add(new GroupData(group.toString()));
        childListDatas.add(new ArrayList<Model_Notice>());
        for (int i = 0; i <= list.size() - 1; i++) {
            childListDatas.get(sizeList).add(new Model_Notice(list.get(i).getNotice_title().toString(), list.get(i).getTime(), list.get(i).getImg_title()));
        }
        sizeList++;
    }

    public class getEventList extends AsyncTask<String, Integer, List<Model_Notice>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(EventActivity.this);
            waitDlg.setMessage("이벤트 불러오는중 입니다.");
            waitDlg.show();
        }

        @Override
        protected List<Model_Notice> doInBackground(String... params) {

            List<String> startend = new ArrayList<String>();
            startend.add(0, "진행 중인 이벤트");
            startend.add(1, "종료된 이벤트");

            for (int i = 0; i <= 1; i++) {
                noticeslist = new Http_Notice().EventList(startend.get(i).toString(), params[0]);
                setData(noticeslist, startend.get(i).toString());
            }
            return noticeslist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Model_Notice> menulist) {
            super.onPostExecute(menulist);

            adapter = new Adapter_Event(EventActivity.this, groupListDatas, childListDatas);
            ExpandableListView.setAdapter(adapter);

            // 열려있게하기
            for(int i=0; i< adapter.getGroupCount(); i ++) {
                ExpandableListView.expandGroup(i);
            }
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }
}

