package com.example.bsyoo.jwc.hppt;

import com.example.bsyoo.jwc.model.ModelSerialCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;


public class HttpSerialCode {

    // 로그인
    public List<ModelSerialCode> getSerialCodeList(ModelSerialCode model){
        String weburl = "http://192.168.0.11/jwcserialcode/getserialsodelist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelSerialCode> codelist = null ;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }


            String jsonInString = response.toString();
            codelist = new Gson().fromJson(jsonInString, new TypeToken<List<ModelSerialCode>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return codelist;
        }
    }
}
