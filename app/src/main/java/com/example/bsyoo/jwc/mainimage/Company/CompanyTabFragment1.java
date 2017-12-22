package com.example.bsyoo.jwc.mainimage.Company;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bsyoo.jwc.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class CompanyTabFragment1 extends CompanyFragment {

    private View view = null;
    private ImageView companyimg;

    public CompanyTabFragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_company_tab_fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        companyimg = (ImageView) view.findViewById(R.id.companyimg);
        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(companyimg);
        photoview.update();

    }
}