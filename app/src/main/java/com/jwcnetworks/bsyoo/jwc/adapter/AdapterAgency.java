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
import com.jwcnetworks.bsyoo.jwc.model.ModelAgency;

import java.util.List;

public class AdapterAgency extends ArrayAdapter<ModelAgency>{


    public AdapterAgency(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelAgency> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder {
        TextView area_name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null) {
            viewHolder = new ViewHolder();

            viewHolder.area_name = (TextView) itemLayout.findViewById(R.id.area_name);

            itemLayout.setTag(viewHolder);
        }

        if (getItem(position).getArea() != null && !getItem(position).getArea().toString().equals("")) {
            viewHolder.area_name.setText(getItem(position).getArea().toString());
        }

        return itemLayout;
    }
}
