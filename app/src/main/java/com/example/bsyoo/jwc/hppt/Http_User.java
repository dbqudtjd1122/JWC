package com.example.bsyoo.jwc.hppt;


import com.example.bsyoo.jwc.model.Model_User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Http_User {

    public Model_User IDSearch1(Model_User model){
        String weburl = "http://192.168.0.11/jwcuser/idsearch";

        HttpRequest request = null;
        JSONObject response = null;
        Model_User user = null ;
        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                response = request.getJSONObjectResponse(); // 서버값이 리턴된다
            } else {
            }

            String jsonInString = response.toString();
            user = new Gson().fromJson(jsonInString, Model_User.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return user;
        }
    }
}
