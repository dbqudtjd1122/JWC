package com.example.bsyoo.jwc.adapter;


import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelUserSerialCode;

import java.util.ArrayList;
import java.util.List;

public class AdapterSerialCode extends android.widget.ArrayAdapter<ModelUserSerialCode>{

    public ArrayList<ModelUserSerialCode> data = null;

    public AdapterSerialCode(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelUserSerialCode> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder {
        ImageView img_dvr;
        TextView tv_seriesname;
        TextView tv_dvrname;
        TextView tv_serialcode;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        ModelUserSerialCode code = getItem(position);

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.img_dvr = (ImageView) itemLayout.findViewById(R.id.img_dvr);
            viewHolder.tv_seriesname = (TextView) itemLayout.findViewById(R.id.tv_seriesname);
            viewHolder.tv_dvrname = (TextView) itemLayout.findViewById(R.id.tv_dvrname);
            viewHolder.tv_serialcode = (TextView) itemLayout.findViewById(R.id.tv_serialcode);

            itemLayout.setTag(viewHolder);
        }

        Glide.with(getContext()).load(getItem(position).getImg_title().toString()).override(100, 100).fitCenter().into(viewHolder.img_dvr);
        viewHolder.tv_seriesname.setText("시리즈 이름 : "+getItem(position).getOnlineseries().toString());
        viewHolder.tv_dvrname.setText("녹화기 이름 : "+getItem(position).getOnlinename().toString());
        viewHolder.tv_serialcode.setText("시리얼 코드 : "+getItem(position).getSerial_Code().toString());

        return itemLayout;
    }
}
