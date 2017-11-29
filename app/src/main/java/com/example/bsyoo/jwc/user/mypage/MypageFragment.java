package com.example.bsyoo.jwc.user.mypage;


import android.support.v4.app.Fragment;

import com.example.bsyoo.jwc.model.Model_User;

public class MypageFragment extends Fragment{
    protected Model_User orderuser = new Model_User();

    public Model_User getOrderuser() {
        return orderuser;
    }

    public void setOrderuser(Model_User orderuser) {
        this.orderuser = orderuser;
        recall();
    }

    public void recall(){

    }
}
