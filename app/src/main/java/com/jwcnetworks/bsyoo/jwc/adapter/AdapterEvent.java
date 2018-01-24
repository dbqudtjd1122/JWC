package com.jwcnetworks.bsyoo.jwc.adapter;

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
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelNotice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterEvent extends ArrayAdapter<ModelNotice> {

    public ArrayList<ModelNotice> data = null;

    public AdapterEvent(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelNotice> objects) {
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
            viewHolder.school_time.setVisibility(View.GONE);
            if(getItem(position).getNotice_end().toString().equals("진행 중인 이벤트")){
                viewHolder.event_title.setText("[이벤트] "+getItem(position).getNotice_title().toString());
            } else {
                viewHolder.event_title.setText("[종료된 이벤트] "+getItem(position).getNotice_title().toString());
            }
            SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd"); // E 요일 HH 시간 mm 분 ss 초
            String datetime = data.format(getItem(position).getTime().getTime());  // 리뷰 수정 날짜, 시간
            viewHolder.event_time.setText(datetime);

            Glide.with(getContext()).load(getItem(position).getImg_title().toString()).override(800,1000).fitCenter().into(viewHolder.event_img);

            return itemLayout;
    }
}
