package com.example.bsyoo.jwc.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_Notice;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Notice extends ArrayAdapter<Model_Notice> {

    public Adapter_Notice(@NonNull Context context, @LayoutRes int resource, @IdRes int notice1, @NonNull List<Model_Notice> objects) {
        super(context, resource, notice1, objects);
    }

    class ViewHolder{
        TextView notice;
        TextView day;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.notice = (TextView) itemLayout.findViewById(R.id.notice1);
            viewHolder.day = (TextView) itemLayout.findViewById(R.id.textView9);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.notice.setText(getItem(position).getNotice());
        viewHolder.day.setText(getItem(position).getDay());

        return itemLayout;
    }
}
