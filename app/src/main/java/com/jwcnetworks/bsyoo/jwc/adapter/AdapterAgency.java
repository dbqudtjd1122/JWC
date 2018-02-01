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
import com.jwcnetworks.bsyoo.jwc.model.ModelAgency;

import java.util.ArrayList;
import java.util.List;

public class AdapterAgency extends ArrayAdapter<ModelAgency>{

    public ArrayList<ModelAgency> data = null;

    public AdapterAgency(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelAgency> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder {
        TextView tv_agency_name;
        TextView tv_agency_phone;
        TextView tv_agency_info;
        TextView tv_agency_addr;
        ImageView img_agency_title;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null) {
            viewHolder = new ViewHolder();

            viewHolder.tv_agency_name = (TextView) itemLayout.findViewById(R.id.tv_agency_name);
            viewHolder.tv_agency_phone = (TextView) itemLayout.findViewById(R.id.tv_agency_phone);
            viewHolder.tv_agency_info = (TextView) itemLayout.findViewById(R.id.tv_agency_info);
            viewHolder.tv_agency_addr = (TextView) itemLayout.findViewById(R.id.tv_agency_addr);
            viewHolder.img_agency_title = (ImageView) itemLayout.findViewById(R.id.img_agency_title);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.tv_agency_name.setText(getItem(position).getAgency_Name().toString());

        viewHolder.tv_agency_info.setText(getItem(position).getInformation().toString());
        viewHolder.tv_agency_addr.setText("주소 : " + getItem(position).getAddr().toString());

        // 전화번호 설정
        if(getItem(position).getPhone().toString().equals("")) {
            viewHolder.tv_agency_phone.setText("");
        } else {
            viewHolder.tv_agency_phone.setText("TEL : " + getItem(position).getPhone().toString());
        }

        // 이미지 설정
        if(getItem(position).getImg_Title() == null || getItem(position).getImg_Title().toString().equals("")){
            Glide.with(getContext()).load("http://jwcnet.godohosting.com/app/jwc_app/img/agency/jwc_biglogo_red.png").override(400, 300).fitCenter().into(viewHolder.img_agency_title);
        }else {
            Glide.with(getContext()).load(getItem(position).getImg_Title().toString()).override(400, 300).fitCenter().into(viewHolder.img_agency_title);
        }

        return itemLayout;
    }
}
