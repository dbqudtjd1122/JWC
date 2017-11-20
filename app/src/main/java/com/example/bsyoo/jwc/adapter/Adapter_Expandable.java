package com.example.bsyoo.jwc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_Notice;

import java.util.ArrayList;

public class Adapter_Expandable extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<GroupData> groupDatas;
    private ArrayList<ArrayList<Model_Notice>> childDatas;
    private LayoutInflater inflater = null;

    public Adapter_Expandable(Context context, ArrayList<GroupData> groupDatas, ArrayList<ArrayList<Model_Notice>> childDatas){
        this.context = context;
        this.groupDatas = groupDatas;
        this.childDatas = childDatas;
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return groupDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childDatas.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childDatas.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View View, ViewGroup parent) {

        /*if(View == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View = inflater.inflate(R.layout.event_group_row, null);
        }
        TextView groupName = (TextView)View.findViewById(R.id.groupName);
        String group = String.valueOf(groupDatas.get(groupPosition).getGroupName());
        groupName.setText(group.toString());*/

        return View;
    }

    @Override
    public View getChildView(int i, int i1, boolean isLastChild, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_event, viewGroup, false );
        }
        /*TextView event_name = (TextView) view.findViewById(R.id.notice_title);
        TextView event_time = (TextView) view.findViewById(R.id.time);
        ImageView event_img = (ImageView) view.findViewById(R.id.event_img);

        event_name.setText(childDatas.get(i).get(i1).getNotice_title());

        SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(childDatas.get(i).get(i1).getTime());  // 리뷰 수정 날짜, 시간
        event_time.setText(datetime);

        Glide.with(context).load(childDatas.get(i).get(i1).getImg_title()).override(800,1000).fitCenter().into(event_img);*/

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}