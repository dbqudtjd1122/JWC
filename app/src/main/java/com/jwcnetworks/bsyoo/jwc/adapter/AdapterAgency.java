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
        if(getItem(position).getAgency_Name().toString().equals("동래전자")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_donglae);
        } else if(getItem(position).getAgency_Name().toString().equals("씨씨티비서치")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_search);
        } else if(getItem(position).getAgency_Name().toString().equals("JYCCTV")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_jycctv);
        } else if(getItem(position).getAgency_Name().toString().equals("BK네트웍스")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_bknetworks);
        } else if(getItem(position).getAgency_Name().toString().equals("CCTV24")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_cctv24);
        } else if(getItem(position).getAgency_Name().toString().equals("JWC 대전지사")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_daejeon);
        } else if(getItem(position).getAgency_Name().toString().equals("디에스")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_ds);
        } else if(getItem(position).getAgency_Name().toString().equals("비앤에스컴퍼니")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_bns);
        } else if(getItem(position).getAgency_Name().toString().equals("㈜서신정보통신")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_seosin);
        } else if(getItem(position).getAgency_Name().toString().equals("애린테크")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_erin);
        } else if(getItem(position).getAgency_Name().toString().equals("영인정보기술")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_yeongin);
        } else if(getItem(position).getAgency_Name().toString().equals("유비네트워크")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_yubi);
        } else if(getItem(position).getAgency_Name().toString().equals("진원컴텍")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_jinwoncomtech);
        } else if(getItem(position).getAgency_Name().toString().equals("진원전자(테크노)")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_jinwonjeonja);
        } else if(getItem(position).getAgency_Name().toString().equals("하이텍 아이디티")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_hightech);
        } else if(getItem(position).getAgency_Name().toString().equals("현대하이테크")){
            viewHolder.img_agency_title.setImageResource(R.drawable.agency_hyeondae);
        } else if(getItem(position).getAgency_Name().toString().equals("본사")){
            viewHolder.img_agency_title.setImageResource(R.drawable.jwc_logo_red);
        } else {
            viewHolder.img_agency_title.setImageResource(R.drawable.jwc_logo_red);
        }

        return itemLayout;
    }
}
