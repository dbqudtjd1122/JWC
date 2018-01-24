package com.jwcnetworks.bsyoo.jwc.hppt;

import com.jwcnetworks.bsyoo.jwc.model.ModelTechnology;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpTechnology {

    public List<ModelTechnology> getTechnology(String type){
        String weburl = "http://jwcctv1.cafe24.com/jwctechnology/gettechlist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelTechnology> noticeList = null;

        int httpCode = 0;
        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("CameraType", type);
            httpCode = request.post();

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }

            String jsonInString = response.toString();
            noticeList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelTechnology>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return noticeList;
        }
    }
}
