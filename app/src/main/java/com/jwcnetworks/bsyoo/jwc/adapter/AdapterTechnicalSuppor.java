package com.jwcnetworks.bsyoo.jwc.adapter;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.hppt.HttpTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.mainmenu.mypage.TechnicalSupportManagerActivity;
import com.jwcnetworks.bsyoo.jwc.mainmenu.mypage.TechnicalSupportWriteActivity;
import com.jwcnetworks.bsyoo.jwc.model.ModelTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterTechnicalSuppor extends ArrayAdapter<ModelTechnicalSupport> {

    public List<ModelTechnicalSupport> data = null;
    public ModelTechnicalSupport technical = new ModelTechnicalSupport();
    public ModelUser user = new ModelUser();
    public Integer isnumber;
    public Activity mActivity;

    public AdapterTechnicalSuppor(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ModelTechnicalSupport> objects, @NonNull ModelUser user, @NonNull Integer isnumber, @NonNull Activity mActivity) {
        super(context, resource, textViewResourceId, objects);
        this.user = user;
        this.isnumber = isnumber;
        this.mActivity = mActivity;
        this.data = objects;
    }

    class ViewHolder {
        TextView tv_title, tv_question, tv_time, tv_info, tv_userid, tv_managerid, tv_managerinfo, tv_managertime;
        LinearLayout ll_technical, ll_answer, ll_manager;

        Button btn_update, btn_delete, btn_manager;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.tv_title = (TextView) itemLayout.findViewById(R.id.tv_title);
            viewHolder.tv_question = (TextView) itemLayout.findViewById(R.id.tv_question);
            viewHolder.tv_time = (TextView) itemLayout.findViewById(R.id.tv_time);
            viewHolder.tv_info = (TextView) itemLayout.findViewById(R.id.tv_info);
            viewHolder.tv_userid = (TextView) itemLayout.findViewById(R.id.tv_userid);
            viewHolder.tv_managerid = (TextView) itemLayout.findViewById(R.id.tv_managerid);
            viewHolder.tv_managerinfo = (TextView) itemLayout.findViewById(R.id.tv_managerinfo);
            viewHolder.tv_managertime = (TextView) itemLayout.findViewById(R.id.tv_managertime);

            viewHolder.ll_answer = (LinearLayout) itemLayout.findViewById(R.id.ll_answer);
            viewHolder.ll_technical = (LinearLayout) itemLayout.findViewById(R.id.ll_technical);
            viewHolder.ll_manager = (LinearLayout) itemLayout.findViewById(R.id.ll_manager);

            viewHolder.btn_update = (Button) itemLayout.findViewById(R.id.btn_update);
            viewHolder.btn_delete = (Button) itemLayout.findViewById(R.id.btn_delete);
            viewHolder.btn_manager = (Button) itemLayout.findViewById(R.id.btn_manager);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.tv_title.setText(getItem(position).getTitle().toString());
        viewHolder.tv_question.setText("질문유형 : " + getItem(position).getQuestion().toString());
        viewHolder.tv_userid.setText("작성자 : " + getItem(position).getID().toString());

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd  HH:mm"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getInserttime().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.tv_time.setText(datetime);

        viewHolder.tv_info.setText(getItem(position).getInfo().toString());

        viewHolder.tv_managerid.setText("작성자 : " + getItem(position).getManagerID().toString());
        viewHolder.tv_managerinfo.setText(getItem(position).getManagerinfo().toString());
        datetime = data.format(getItem(position).getManagertime().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.tv_managertime.setText(datetime);

        viewHolder.btn_manager.setVisibility(View.GONE);
        if (user.getLevel() >= 11) {
            viewHolder.btn_manager.setVisibility(View.VISIBLE);
        }


        viewHolder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TechnicalSupportWriteActivity.class);
                technical = getItem(position);
                intent.putExtra("user", user);
                intent.putExtra("technical", technical);
                intent.putExtra("1", 1); // 수정 확인

                mActivity.startActivityForResult(intent, 5849);
            }
        });

        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                technical = getItem(position);
                new AdapterTechnicalSuppor.deletetechnical().execute(technical);
            }
        });

        viewHolder.btn_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), TechnicalSupportManagerActivity.class);
                technical = getItem(position);
                intent.putExtra("technical", technical);
                intent.putExtra("user", user);
                mActivity.startActivityForResult(intent, 6422);

            }
        });

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.ll_technical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHolder.ll_answer.getVisibility() == View.VISIBLE) {
                    finalViewHolder.ll_answer.setVisibility(View.GONE);
                    finalViewHolder.ll_manager.setVisibility(View.GONE);
                } else {
                    finalViewHolder.ll_answer.setVisibility(View.VISIBLE);
                    if (getItem(position).getManagerinfo().toString().equals("")) {
                        finalViewHolder.ll_manager.setVisibility(View.GONE);
                    } else {
                        finalViewHolder.ll_manager.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        return itemLayout;
    }

    // 삭제(Delete Update -> 2)
    public class deletetechnical extends AsyncTask<ModelTechnicalSupport, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(ModelTechnicalSupport... params) {

            Integer count = new HttpTechnicalSupport().deleteTechnicalSupport(params[0]);

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
                data.remove(technical);
                AdapterTechnicalSuppor.this.notifyDataSetChanged();
                Toast.makeText(getContext(), "문의글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
            }
        }
    }
}