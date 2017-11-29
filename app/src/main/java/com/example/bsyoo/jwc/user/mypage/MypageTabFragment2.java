package com.example.bsyoo.jwc.user.mypage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.model.Model_User;


public class MypageTabFragment2 extends MypageFragment {

    private View view = null;
    private Model_User user = new Model_User();

    public MypageTabFragment2(){

    }

    @Override
    public void recall() {
        super.recall();
        user = getOrderuser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mypage_tab_fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
