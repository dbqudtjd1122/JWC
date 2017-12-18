package com.example.bsyoo.jwc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelAgencyTopic;
import com.example.bsyoo.jwc.model.ModelUser;
import com.example.bsyoo.jwc.user.mypage.AgencTopicWriteActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterAgencyTopic extends ArrayAdapter<ModelAgencyTopic>{

    public ModelUser user = new ModelUser();
    public ModelAgencyTopic topic = new ModelAgencyTopic();

    public AdapterAgencyTopic(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelAgencyTopic> objects,@NonNull ModelUser user) {
        super(context, resource, textViewResourceId, objects);
        this.user = user;
    }

    class ViewHolder {
        TextView tv_id, tv_time, tv_topicinfo, tv_love, tv_countreview;
        ImageView img_topic1, img_topic2, img_topic3;
        Button btn_update, btn_delete;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();

            viewHolder.tv_id = (TextView) itemLayout.findViewById(R.id.tv_id);
            viewHolder.tv_time = (TextView) itemLayout.findViewById(R.id.tv_time);
            viewHolder.tv_topicinfo = (TextView) itemLayout.findViewById(R.id.tv_topicinfo);
            viewHolder.tv_love = (TextView) itemLayout.findViewById(R.id.tv_love);
            viewHolder.tv_countreview = (TextView) itemLayout.findViewById(R.id.tv_countreview);

            viewHolder.img_topic1 = (ImageView) itemLayout.findViewById(R.id.img_topic1);
            viewHolder.img_topic2 = (ImageView) itemLayout.findViewById(R.id.img_topic2);
            viewHolder.img_topic3 = (ImageView) itemLayout.findViewById(R.id.img_topic3);

            viewHolder.btn_update = (Button) itemLayout.findViewById(R.id.btn_update);
            viewHolder.btn_delete = (Button) itemLayout.findViewById(R.id.btn_delete);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.tv_id.setText("작성자 : " + getItem(position).getID().toString());
        viewHolder.tv_topicinfo.setText(getItem(position).getInformation().toString());
        viewHolder.tv_love.setText("좋아요 : " + getItem(position).getLove().toString());
        viewHolder.tv_countreview.setText("리뷰 : " + getItem(position).getReview().toString());

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd  HH:mm"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getTopic_Time().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.tv_time.setText(datetime);

        if (getItem(position).getImg1() == null) {
            viewHolder.img_topic1.setVisibility(View.GONE);
            viewHolder.img_topic2.setVisibility(View.GONE);
            viewHolder.img_topic3.setVisibility(View.GONE);
        } else if (getItem(position).getImg2() == null) {
            Glide.with(getContext()).load(getItem(position).getImg1().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic1);
            viewHolder.img_topic2.setVisibility(View.GONE);
            viewHolder.img_topic3.setVisibility(View.GONE);
        } else if (getItem(position).getImg3() == null) {
            Glide.with(getContext()).load(getItem(position).getImg1().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic1);
            Glide.with(getContext()).load(getItem(position).getImg2().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic2);
            viewHolder.img_topic3.setVisibility(View.GONE);
        } else {
            Glide.with(getContext()).load(getItem(position).getImg1().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic1);
            Glide.with(getContext()).load(getItem(position).getImg2().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic2);
            Glide.with(getContext()).load(getItem(position).getImg3().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic3);
        }

        // 본인이 아니면 버튼 GONE
        if (getItem(position).getID().toString().equals(user.getID().toString())) {
        } else {
            viewHolder.btn_update.setVisibility(View.GONE);
            viewHolder.btn_delete.setVisibility(View.GONE);
        }

        viewHolder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AgencTopicWriteActivity.class);
                topic = getItem(position);
                intent.putExtra("topic", topic);
                ((Activity) getContext()).startActivityForResult(intent, 768);
            }
        });
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topic = getItem(position);
            }
        });

        return itemLayout;
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
