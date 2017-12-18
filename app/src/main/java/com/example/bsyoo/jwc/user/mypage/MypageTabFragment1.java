package com.example.bsyoo.jwc.user.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.ModelUser;

public class MypageTabFragment1 extends MypageFragment{

    private View view = null;
    private LinearLayout ll_mypage1, ll_mypage2, ll_mypage3;
    private ModelUser user = new ModelUser();

    public MypageTabFragment1(){

    }

    @Override
    public void recall() {
        super.recall();
        user = getOrderuser();
        if(user.getLevel() >= 4){
            ll_mypage2.setVisibility(View.VISIBLE);
        }
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
                intent.putExtra("user", user);
                getActivity().startActivity(intent);
            }
        });
        ll_mypage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), AgencyTopicActivity.class);
                intent.putExtra("user", user);
                getActivity().startActivity(intent);
            }
        });

        ll_mypage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PwCheckActivity.class);
                intent.putExtra("account", "탈퇴");
                intent.putExtra("user", user);
                getActivity().startActivityForResult(intent, 666);
            }
        });
    }

    private void byid(){
        ll_mypage1 = (LinearLayout) view.findViewById(R.id.ll_mypage1);
        ll_mypage2 = (LinearLayout) view.findViewById(R.id.ll_mypage2);
        ll_mypage3 = (LinearLayout) view.findViewById(R.id.ll_mypage3);
    }
}
