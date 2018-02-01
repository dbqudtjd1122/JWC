package com.jwcnetworks.bsyoo.jwc.mainimage.cctvinstall;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class CctvInstallTabFragment2 extends CctvInstallFragment {

    private View view = null;
    private ImageView appinstall_info;
    private ImageView img_install;

    public CctvInstallTabFragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_cctv_install_tab_fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        img_install = (ImageView) view.findViewById(R.id.img_install);

        appinstall_info = (ImageView) view.findViewById(R.id.appinstall_info);
        Glide.with(this).load("http://jwcnet.godohosting.com/JWCMALL/etc/mobile%20cctv.jpg").override(720,4000).fitCenter().into(appinstall_info);

        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(appinstall_info);
        photoview.update();

        img_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=kr.co.jwccctv.viewer"));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "구글스토어 연결이 불가능 합니다.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}