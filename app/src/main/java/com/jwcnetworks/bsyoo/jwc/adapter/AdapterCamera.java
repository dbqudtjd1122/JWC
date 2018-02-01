package com.jwcnetworks.bsyoo.jwc.adapter;

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
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelCamera;
import com.jwcnetworks.bsyoo.jwc.user.Login.LoginInformation;

import java.util.ArrayList;
import java.util.List;

public class AdapterCamera extends android.widget.ArrayAdapter<ModelCamera> {

    public ArrayList<ModelCamera> data = null;
    public LoginInformation login;
    public Integer level;

    public AdapterCamera(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelCamera> objects, @NonNull int islevel) {
        super(context, resource, textViewResourceId, objects);
        level = islevel;
    }

    class ViewHolder {
        TextView text_cameraname;
        TextView text_cameraprice1;
        TextView text_cameraprice2;
        ImageView img_camera;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        ModelCamera camera = getItem(position);

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.text_cameraname = (TextView) itemLayout.findViewById(R.id.text_cameraname);
            viewHolder.text_cameraprice1 = (TextView) itemLayout.findViewById(R.id.text_cameraprice1);
            viewHolder.img_camera = (ImageView) itemLayout.findViewById(R.id.img_camera);

            viewHolder.text_cameraprice2 = (TextView) itemLayout.findViewById(R.id.text_cameraprice2);

            itemLayout.setTag(viewHolder);
        }

        // int i = new LoginInformation().levelreturn();
        // viewHolder.text_cameraprice1.setText(getItem(position).getLevel1price().toString()); // 가격표시
        viewHolder.text_cameraprice1.setVisibility(View.GONE);
        if(getItem(position).getNewCamera().toString().equals("4")){
            viewHolder.text_cameraprice1.setVisibility(View.VISIBLE);
            if(level >=2){
                viewHolder.text_cameraprice1.setText("단종\n대체모델 : "+getItem(position).getFollow_Up().toString());
            }else {
                viewHolder.text_cameraprice1.setText("단종");
            }
        }

        if(level == -1 || level ==1){
            Glide.with(getContext()).load(getItem(position).getOnline_Img_title().toString()).override(100, 100).fitCenter().into(viewHolder.img_camera);
            viewHolder.text_cameraname.setText(getItem(position).getOnlinename().toString());

            // viewHolder.text_cameraprice1.setText(getItem(position).getLevel1price().toString());
        } else if (level >= 2) {
            Glide.with(getContext()).load(getItem(position).getOffline_Img_title().toString()).override(100, 100).fitCenter().into(viewHolder.img_camera);
            viewHolder.text_cameraname.setText(getItem(position).getOfflinename().toString());

            // 대리점 단가표로 바꿈
            /*viewHolder.text_cameraprice1.setText(getItem(position).getLevel1price().toString());
            viewHolder.text_cameraprice1.setPaintFlags(viewHolder.text_cameraprice1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // 텍스트 가운데 줄긋기
            viewHolder.text_cameraprice2.setVisibility(View.VISIBLE);
            viewHolder.text_cameraprice2.setText(getItem(position).getLevel2price().toString());*/
        }
        return itemLayout;
    }
}
