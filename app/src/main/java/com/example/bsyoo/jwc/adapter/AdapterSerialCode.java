package com.example.bsyoo.jwc.adapter;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.hppt.HttpSerialCode;
import com.example.bsyoo.jwc.model.ModelUserSerialCode;
import com.example.bsyoo.jwc.user.mypage.MypageActivity;
import com.example.bsyoo.jwc.user.mypage.MypageFragment;
import com.example.bsyoo.jwc.user.mypage.MypageTabFragment2;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

public class AdapterSerialCode extends ArrayAdapter<ModelUserSerialCode> {

    public List<ModelUserSerialCode> data = null;
    public ModelUserSerialCode usercode = new ModelUserSerialCode();

    public AdapterSerialCode(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelUserSerialCode> objects) {
        super(context, resource, textViewResourceId, objects);
        this.data = objects;
    }

    class ViewHolder {
        public ImageView img_dvr;
        public TextView tv_seriesname, tv_dvrname, tv_serialcode;
        public Button btn_serialcode_delete;
        public ListView listView;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        final View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.img_dvr = (ImageView) itemLayout.findViewById(R.id.img_dvr);
            viewHolder.tv_seriesname = (TextView) itemLayout.findViewById(R.id.tv_seriesname);
            viewHolder.tv_dvrname = (TextView) itemLayout.findViewById(R.id.tv_dvrname);
            viewHolder.tv_serialcode = (TextView) itemLayout.findViewById(R.id.tv_serialcode);

            viewHolder.btn_serialcode_delete = (Button) itemLayout.findViewById(R.id.btn_serialcode_delete);
            viewHolder.listView = (ListView) itemLayout.findViewById(R.id.series_code);

            itemLayout.setTag(viewHolder);
        }

        Glide.with(getContext()).load(getItem(position).getImg_title().toString()).override(100, 100).fitCenter().into(viewHolder.img_dvr);
        viewHolder.tv_seriesname.setText("시리즈 이름 : "+getItem(position).getOnlineseries().toString());
        viewHolder.tv_dvrname.setText("녹화기 이름 : "+getItem(position).getOnlinename().toString());
        viewHolder.tv_serialcode.setText("시리얼 코드 : "+getItem(position).getSerial_Code().toString());

        viewHolder.btn_serialcode_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usercode = getItem(position);
                new AdapterSerialCode.deleteserial().execute(usercode);

            }
        });
        return itemLayout;
    }

    // 시리얼정보 삭제
    public class deleteserial extends AsyncTask<ModelUserSerialCode, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(getContext());
            waitDlg.setMessage("시리얼 삭제중 입니다.");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelUserSerialCode... params) {

            Integer count = new HttpSerialCode().DeleteSerial(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer count) {
            super.onPostExecute(count);

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if(count == 1){
                data.remove(usercode);
                AdapterSerialCode.this.notifyDataSetChanged();
                Toast.makeText(getContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
            }
        }
    }
}
