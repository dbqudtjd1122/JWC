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
import com.example.bsyoo.jwc.SeriesActivity;
import com.example.bsyoo.jwc.model.Model_Camera;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Camera extends android.widget.ArrayAdapter<Model_Camera>{

    public ArrayList<Model_Camera> data = null;

    public Adapter_Camera(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Model_Camera> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder {
        TextView text_cameraname;
        TextView text_cameraprice;
        ImageView img_camera;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        Model_Camera camera = getItem(position);

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.text_cameraname = (TextView) itemLayout.findViewById(R.id.text_cameraname);
            viewHolder.text_cameraprice = (TextView) itemLayout.findViewById(R.id.text_cameraprice);
            viewHolder.img_camera = (ImageView) itemLayout.findViewById(R.id.img_camera);

            itemLayout.setTag(viewHolder);
        }

        Glide.with(getContext()).load(getItem(position).getOnline_Img_title().toString()).override(100, 100).fitCenter().into(viewHolder.img_camera);
        viewHolder.text_cameraname.setText(getItem(position).getOnlinename().toString());
        viewHolder.text_cameraprice.setText(getItem(position).getLevel1price().toString());

        return itemLayout;
    }
}
