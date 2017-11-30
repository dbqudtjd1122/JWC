package com.example.bsyoo.jwc.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelSchool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterSchool extends ArrayAdapter<ModelSchool> {

    public ArrayList<ModelSchool> data = null;

    public AdapterSchool(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelSchool> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder{
        TextView event_title;
        TextView school_time;
        TextView event_time;
        ImageView event_img;
    }

    @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemLayout = super.getView(position, convertView, parent);
            ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

            if(viewHolder == null) {
                viewHolder = new ViewHolder();
                viewHolder.event_title = (TextView) itemLayout.findViewById(R.id.event_title);
                viewHolder.school_time = (TextView) itemLayout.findViewById(R.id.school_time);
                viewHolder.event_time = (TextView) itemLayout.findViewById(R.id.event_time);
                viewHolder.event_img = (ImageView) itemLayout.findViewById(R.id.event_img);

                itemLayout.setTag(viewHolder);
            }

            // 교육신청기간
            if(getItem(position).getStart_End().toString().equals("진행")) {
                viewHolder.school_time.setText("교육신청 기간 : "+getItem(position).getApply_Time().toString() );
            } else {
                viewHolder.school_time.setVisibility(View.GONE);
            }

            // 교육 진행중인지 확인하여 타이틀명작성
            if(getItem(position).getStart_End().toString().equals("진행")){
                viewHolder.event_title.setText("[진행중] "+getItem(position).getSchool_Title().toString());
            } else {
                viewHolder.event_title.setText("[종료] "+getItem(position).getSchool_Title().toString());
            }

            // 교육 시행날짜
            SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd"); // E 요일 HH 시간 mm 분 ss 초
            String datetime = data.format(getItem(position).getLecture_Time().getTime());  // 리뷰 수정 날짜, 시간
            viewHolder.event_time.setText("교육시행 날짜 : "+datetime);

            // 이미지타이틀
            Glide.with(getContext()).load(getItem(position).getImg_title().toString()).override(800,1000).fitCenter().into(viewHolder.event_img);

            return itemLayout;
    }
}
