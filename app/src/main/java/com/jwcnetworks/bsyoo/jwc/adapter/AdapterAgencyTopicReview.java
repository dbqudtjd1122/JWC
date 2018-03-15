package com.jwcnetworks.bsyoo.jwc.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpAgency;
import com.jwcnetworks.bsyoo.jwc.model.ModelAgencyTopic;
import com.jwcnetworks.bsyoo.jwc.model.ModelAgencyTopicReview;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterAgencyTopicReview extends ArrayAdapter<ModelAgencyTopicReview> {

    private ModelUser user = new ModelUser();
    private List<ModelAgencyTopicReview> data =  null;
    private ModelAgencyTopicReview review = new ModelAgencyTopicReview();
    private ViewHolder viewHolder;
    private ModelAgencyTopic topic = new ModelAgencyTopic();

    public AdapterAgencyTopicReview(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelAgencyTopicReview> objects, @NonNull ModelUser user, @NonNull ModelAgencyTopic topic) {
        super(context, resource, textViewResourceId, objects);
        this.user = user;
        this.data = objects;
        this.topic = topic;
    }

    class ViewHolder {
        TextView tv_id, tv_time, tv_info;
        ImageView img_review_delete;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        viewHolder = (ViewHolder) itemLayout.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();

            viewHolder.tv_id = (TextView) itemLayout.findViewById(R.id.tv_id);
            viewHolder.tv_time = (TextView) itemLayout.findViewById(R.id.tv_time);
            viewHolder.tv_info = (TextView) itemLayout.findViewById(R.id.tv_info);
            viewHolder.img_review_delete = (ImageView) itemLayout.findViewById(R.id.img_review_delete);

            itemLayout.setTag(viewHolder);
        }else {
        }

        viewHolder.tv_id.setText(getItem(position).getID().toString());

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd  HH:mm"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getReview_Time().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.tv_time.setText(datetime);

        viewHolder.tv_info.setText(getItem(position).getInformation().toString());

        viewHolder.img_review_delete.setVisibility(View.GONE);
        if(getItem(position).getID().toString().equals(user.getID().toString())){
            viewHolder.img_review_delete.setVisibility(View.VISIBLE);
        }

        viewHolder.img_review_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review = getItem(position);
                new AdapterAgencyTopicReview.deletereview().execute(review);
            }
        });
        return itemLayout;
    }

    // 삭제
    public class deletereview extends AsyncTask<ModelAgencyTopicReview, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Integer doInBackground(ModelAgencyTopicReview... params) {

            Integer count = new HttpAgency().DeleteReview(params[0]);

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
            if(s==1) {
                data.remove(review);
                AdapterAgencyTopicReview.this.notifyDataSetChanged();
                Toast.makeText(getContext(), "리뷰가 삭제되었습니다.", Toast.LENGTH_SHORT).show();

            }else {
            }
        }
    }
}
