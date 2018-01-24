package com.jwcnetworks.bsyoo.jwc.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelNotice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterNotice extends ArrayAdapter<ModelNotice> {
    
    public ArrayList<ModelNotice> data = null;

    public AdapterNotice(@NonNull Context context, @LayoutRes int resource, @IdRes int notice1, @NonNull List<ModelNotice> objects) {
        super(context, resource, notice1, objects);
    }

    class ViewHolder{
        TextView notice_title;

        TextView time;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.notice_title = (TextView) itemLayout.findViewById(R.id.notice1);
            viewHolder.time = (TextView) itemLayout.findViewById(R.id.textView9);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.notice_title.setText("[공지] "+getItem(position).getNotice_title());

        SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getTime().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.time.setText(datetime);

        return itemLayout;
    }
}
