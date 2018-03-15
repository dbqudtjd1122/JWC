package com.jwcnetworks.bsyoo.jwc.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpAgency;
import com.jwcnetworks.bsyoo.jwc.model.ModelAgencyTopic;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.jwcnetworks.bsyoo.jwc.mainmenu.mypage.AgencyTopicWriteActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterAgencyTopic extends ArrayAdapter<ModelAgencyTopic> {

    public ModelUser user = new ModelUser();
    public ModelAgencyTopic topic = new ModelAgencyTopic();
    public List<ModelAgencyTopic> data = null;
    public Activity mActivity;

    public AdapterAgencyTopic(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelAgencyTopic> objects, @NonNull ModelUser user, @NonNull Activity mActivity) {
        super(context, resource, textViewResourceId, objects);
        this.user = user;
        this.data = objects;
        this.mActivity = mActivity;
    }

    class ViewHolder {
        TextView tv_id, tv_time, tv_topicinfo, tv_countreview, tv_name;
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
            viewHolder.tv_countreview = (TextView) itemLayout.findViewById(R.id.tv_countreview);
            viewHolder.tv_name = (TextView) itemLayout.findViewById(R.id.tv_name);

            viewHolder.img_topic1 = (ImageView) itemLayout.findViewById(R.id.img_topic1);
            viewHolder.img_topic2 = (ImageView) itemLayout.findViewById(R.id.img_topic2);
            viewHolder.img_topic3 = (ImageView) itemLayout.findViewById(R.id.img_topic3);

            viewHolder.btn_update = (Button) itemLayout.findViewById(R.id.btn_update);
            viewHolder.btn_delete = (Button) itemLayout.findViewById(R.id.btn_delete);

            itemLayout.setTag(viewHolder);
        }


        viewHolder.tv_id.setText("작성자 : " + getItem(position).getID().toString());
        viewHolder.tv_name.setText("제목 : " + getItem(position).getTopic_Name().toString());
        viewHolder.tv_topicinfo.setText(getItem(position).getInformation().toString());

        viewHolder.tv_countreview.setText("리뷰 " + getItem(position).getReview().toString());

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd  HH:mm"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getTopic_Time().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.tv_time.setText(datetime);

        // 이미지 로딩
        viewHolder.img_topic1.setVisibility(View.GONE);
        viewHolder.img_topic2.setVisibility(View.GONE);
        viewHolder.img_topic3.setVisibility(View.GONE);
        if (getItem(position).getImg1() != null) {
            viewHolder.img_topic1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(getItem(position).getImg1().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic1);
        }
        if (getItem(position).getImg2() != null) {
            viewHolder.img_topic2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(getItem(position).getImg2().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic2);
        }
        if (getItem(position).getImg3() != null) {
            viewHolder.img_topic3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(getItem(position).getImg3().toString()).override(250, 250).fitCenter().into(viewHolder.img_topic3);
        }

        // 본인이 아니면 수정,삭제 버튼 GONE
        viewHolder.btn_update.setVisibility(View.VISIBLE);
        viewHolder.btn_delete.setVisibility(View.VISIBLE);
        if (getItem(position).getID().toString().equals(user.getID().toString())) {
        } else {
            viewHolder.btn_update.setVisibility(View.GONE);
            viewHolder.btn_delete.setVisibility(View.GONE);
        }

        viewHolder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AgencyTopicWriteActivity.class);
                topic = getItem(position);
                intent.putExtra("topic", topic);
                intent.putExtra("1", 1);
                mActivity.startActivityForResult(intent, 178);
            }
        });
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topic = getItem(position);
                new AdapterAgencyTopic.deletetopic2().execute(topic);
            }
        });

        return itemLayout;
    }

    // 삭제(Delete Update -> 2)
    public class deletetopic2 extends AsyncTask<ModelAgencyTopic, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Integer doInBackground(ModelAgencyTopic... params) {

            Integer count = new HttpAgency().DeleteTopic(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (s == 1) {
                data.remove(topic);
                AdapterAgencyTopic.this.notifyDataSetChanged();
                Toast.makeText(getContext(), "게시글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
            }
        }
    }
}
