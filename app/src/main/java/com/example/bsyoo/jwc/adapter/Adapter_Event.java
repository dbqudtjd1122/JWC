package com.example.bsyoo.jwc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_Event;

import java.util.ArrayList;


public class Adapter_Event extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<GroupData> groupDatas;
    private ArrayList<ArrayList<Model_Event>> childDatas;
    private LayoutInflater inflater = null;

    public Adapter_Event(Context context, ArrayList<GroupData> groupDatas, ArrayList<ArrayList<Model_Event>> childDatas){
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

        if(View == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View = inflater.inflate(R.layout.event_group_row, null);
        }
        TextView groupName = (TextView)View.findViewById(R.id.groupName);
        String group = String.valueOf(groupDatas.get(groupPosition).getGroupName());
        groupName.setText(group.toString());

        return View;
    }

    @Override
    public View getChildView(int i, int i1, boolean isLastChild, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_child_row, viewGroup, false );
        }
        TextView event_name = (TextView) view.findViewById(R.id.event_name);
        TextView event_time = (TextView) view.findViewById(R.id.event_time);
        ImageView event_img = (ImageView) view.findViewById(R.id.event_img);

        event_name.setText(childDatas.get(i).get(i1).getEvent_name());
        event_time.setText(childDatas.get(i).get(i1).getEvent_time());
        if(event_name.getText().toString().equals("[이벤트] CCTV 렌탈 서비스")){
            event_img.setImageResource(R.drawable.rental330);
        }else if(event_name.getText().toString().equals("[이벤트] JWC & Dahua 컨퍼런스")) {
            event_img.setImageResource(R.drawable.conference_330);
        }else if(event_name.getText().toString().equals("[이벤트] 협력업체 특별혜택")) {
            event_img.setImageResource(R.drawable.benefit_330);
        }else if(event_name.getText().toString().equals("[이벤트] 화질은 HD급! 가격은 Light급! JDO-4008")) {
            event_img.setImageResource(R.drawable.mp_330);
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
