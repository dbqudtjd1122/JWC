package com.example.bsyoo.jwc.user.mypage;


import android.support.v4.app.Fragment;

import com.example.bsyoo.jwc.model.ModelUser;

public class MypageFragment extends Fragment{
    protected ModelUser orderuser = new ModelUser();

    public ModelUser getOrderuser() {
        return orderuser;
    }

    public void setOrderuser(ModelUser orderuser) {
        this.orderuser = orderuser;
        recall();
    }

    public void recall(){

    }
}
