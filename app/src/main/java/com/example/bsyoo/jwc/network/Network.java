package com.example.bsyoo.jwc.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Network {

    public Boolean isNetWork(NetworkInfo mNetwork){

        if(mNetwork != null && mNetwork.isConnected()){
            /*와이파이*/
            if(mNetwork.getType() == ConnectivityManager.TYPE_WIFI/*와이파이*/ || mNetwork.getType() == ConnectivityManager.TYPE_MOBILE /*데이터*/ ){
                return true;
            }
            return true;
        } else {

            return false;
        }
    }

}
