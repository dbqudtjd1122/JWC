package com.example.bsyoo.jwc.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelCamera;
import com.example.bsyoo.jwc.model.ModelTechnology;
import com.example.bsyoo.jwc.user.Login.LoginInformation;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterTechnology extends android.widget.ArrayAdapter<ModelTechnology> {

    public ArrayList<ModelTechnology> data = null;

    public AdapterTechnology(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelTechnology> objects) {
        super(context, resource, textViewResourceId, objects);
    }


    class ViewHolder {
        TextView tv_technology;

        TextView time;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null){
            viewHolder = new ViewHolder();

            viewHolder.tv_technology = (TextView) itemLayout.findViewById(R.id.notice1);
            viewHolder.time = (TextView) itemLayout.findViewById(R.id.textView9);
            itemLayout.setTag(viewHolder);
        }

        viewHolder.tv_technology.setText(getItem(position).getYouTubeName().toString());

        SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getUrlTime().getTime());  // 수정 날짜, 시간
        viewHolder.time.setText(datetime);

        return itemLayout;
    }
}
