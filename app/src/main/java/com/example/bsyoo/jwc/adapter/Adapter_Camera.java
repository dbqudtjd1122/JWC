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

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_Camera;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Camera extends android.widget.ArrayAdapter<Model_Camera>{

    public ArrayList<Model_Camera> data = null;

    public Adapter_Camera(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Model_Camera> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder {
        TextView text_series;
        TextView text_cameraname;
        // TextView text_camera;
        ImageView img_camera;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        Model_Camera camera = getItem(position);

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.text_series = (TextView) itemLayout.findViewById(R.id.text_series);
            viewHolder.text_cameraname = (TextView) itemLayout.findViewById(R.id.text_cameraname);
            // viewHolder.text_camera = (TextView) itemLayout.findViewById(R.id.text_camera);
            viewHolder.img_camera = (ImageView) itemLayout.findViewById(R.id.img_camera);

            itemLayout.setTag(viewHolder);
        }
        if(camera.getOnlinename().toString().equals("JWC-L1VD")){
            viewHolder.img_camera.setImageResource(R.drawable.l1vd_100);
        } else if (camera.getOnlinename().toString().equals("JWC-L3HAF")){
            viewHolder.img_camera.setImageResource(R.drawable.l3haf_100);
        } else if (camera.getOnlinename().toString().equals("JWC-L4LPR")){
            viewHolder.img_camera.setImageResource(R.drawable.l4lpr_100);
        } else if (camera.getOnlinename().toString().equals("JSP-210A")){
            viewHolder.img_camera.setImageResource(R.drawable.jsp_210a);
        } else if (camera.getOnlinename().toString().equals("JSP-218A")){
            viewHolder.img_camera.setImageResource(R.drawable.jsp_218a);
        } else if (camera.getOnlinename().toString().equals("JWC-S1D 2.8mm")){
            viewHolder.img_camera.setImageResource(R.drawable.jwc_s1d2);
        } else if (camera.getOnlinename().toString().equals("JWC-S1D 3.6mm")){
            viewHolder.img_camera.setImageResource(R.drawable.jwc_s1d3);
        }
        // HQD 4MP
        else if (camera.getOnlinename().toString().equals("JDO-4008B (1TB)") || camera.getOnlinename().toString().equals("JDO-4008B (2TB)") || camera.getOnlinename().toString().equals("JDO-4008B (3TB)")){
            viewHolder.img_camera.setImageResource(R.drawable.jdo_4008_1tb);
        }
        // ALL-HD LITE 모델
        else if (camera.getOnlinename().toString().equals("JDO-4005B (1TB)") || camera.getOnlinename().toString().equals("JDO-8005 (1TB)") || camera.getOnlinename().toString().equals("JDO-1605 (2TB)")){
            viewHolder.img_camera.setImageResource(R.drawable.jdo_4005b_1tb);
        }

        viewHolder.text_series.setText(getItem(position).getSeries().toString());
        viewHolder.text_cameraname.setText(getItem(position).getOnlinename().toString());
        // viewHolder.text_camera.setText(getItem(position).getPerformance().toString());

        return itemLayout;
    }
}
