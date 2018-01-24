package com.jwcnetworks.bsyoo.jwc.mainimage.camera;

import android.support.v4.app.Fragment;

public class CameraFragment extends Fragment {

    protected Boolean net = true;

    public Boolean getNet() {
        return net;
    }

    public void setNet(Boolean net) {
        this.net = net;
        recall();
    }

    public void recall(){

    }
}
