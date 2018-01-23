package com.example.bsyoo.jwc.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelCompany;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterCompany extends ArrayAdapter<ModelCompany> {

    public AdapterCompany(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelCompany> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder{

        ImageView img_work;
        TextView tv_company;
        TextView tv_teamname;
        TextView tv_title;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.img_work = (ImageView) itemLayout.findViewById(R.id.img_work);
            viewHolder.tv_company = (TextView) itemLayout.findViewById(R.id.tv_company);
            viewHolder.tv_teamname = (TextView) itemLayout.findViewById(R.id.tv_teamname);
            viewHolder.tv_title = (TextView) itemLayout.findViewById(R.id.tv_title);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.tv_company.setText(getItem(position).getTv_Company().toString());
        viewHolder.tv_teamname.setText(getItem(position).getTeam_name().toString());
        if (getItem(position).getTeam_Title() != null){
            viewHolder.tv_title.setText(getItem(position).getTeam_Title().toString());
        }

        if (getItem(position).getImg_Work() != null){
            viewHolder.img_work.setImageResource(getItem(position).getImg_Work());
        }


        return itemLayout;
    }
}
