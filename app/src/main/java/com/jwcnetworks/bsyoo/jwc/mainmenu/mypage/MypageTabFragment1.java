package com.jwcnetworks.bsyoo.jwc.mainmenu.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;

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
        if(user.getLevel() >= 10){
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
                getActivity().startActivityForResult(intent, 5612);
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
                // 어플 개선사항
            }
        });
    }

    private void byid(){
        ll_mypage1 = (LinearLayout) view.findViewById(R.id.ll_mypage1);
        ll_mypage2 = (LinearLayout) view.findViewById(R.id.ll_mypage2);
        ll_mypage3 = (LinearLayout) view.findViewById(R.id.ll_mypage3);
    }
}
