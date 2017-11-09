package com.example.bsyoo.jwc.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_Camera;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Series extends android.widget.ArrayAdapter<Model_Camera> {


    public Adapter_Series(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Model_Camera> objects){
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder {
        TextView series_name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        Model_Camera camera = getItem(position);

        if(viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.series_name = (TextView) itemLayout.findViewById(R.id.series_name);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.series_name.setText(getItem(position).getSeries().toString());

        return itemLayout;
    }
}
