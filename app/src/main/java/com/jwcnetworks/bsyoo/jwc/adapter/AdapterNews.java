package com.jwcnetworks.bsyoo.jwc.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelNews;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterNews extends ArrayAdapter<ModelNews>{

    public ModelNews news = new ModelNews();
    public List<ModelNews> data = null;

    public AdapterNews(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ModelNews> objects) {
        super(context, resource, textViewResourceId, objects);
        this.data = objects;
    }

    class ViewHolder {
        TextView newstitle;
        TextView time;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        AdapterNews.ViewHolder viewHolder = (AdapterNews.ViewHolder) itemLayout.getTag();

        if (viewHolder == null) {
            viewHolder = new AdapterNews.ViewHolder();
            viewHolder.newstitle = (TextView) itemLayout.findViewById(R.id.notice1);
            viewHolder.time = (TextView) itemLayout.findViewById(R.id.textView9);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.newstitle.setText(getItem(position).getNewstitle().toString());

        SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getNewstime().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.time.setText(datetime);

        return itemLayout;
    }
}
