package com.example.bsyoo.jwc.user.mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private LinearLayout ll_mypage1;
    private Model_User user = new Model_User();

    public MypageTabFragment1(){

    }

    @Override
    public void recall() {
        super.recall();
        user = getOrderuser();
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
                Intent intent = new Intent(getActivity(), MypageModifiedActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
    private void byid(){
        ll_mypage1 = (LinearLayout) view.findViewById(R.id.ll_mypage1);
    }
}
