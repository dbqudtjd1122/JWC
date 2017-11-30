package com.example.bsyoo.jwc.user.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_User;

public class MypageTabFragment1 extends MypageFragment{

    private View view = null;
    private LinearLayout ll_mypage1, ll_mypage2;
    private Model_User user = new Model_User();

    public MypageTabFragment1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mypage_tab_fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        byid();

        ll_mypage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PwCheckActivity.class);
                intent.putExtra("account", "수정");
                getActivity().startActivity(intent);
            }
        });
        ll_mypage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PwCheckActivity.class);
                intent.putExtra("account", "탈퇴");
                getActivity().startActivityForResult(intent, 666);
            }
        });
    }

    private void byid(){
        ll_mypage1 = (LinearLayout) view.findViewById(R.id.ll_mypage1);
        ll_mypage2 = (LinearLayout) view.findViewById(R.id.ll_mypage2);
    }
}
