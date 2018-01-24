package com.jwcnetworks.bsyoo.jwc.hppt;

import com.jwcnetworks.bsyoo.jwc.model.ModelCases;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpCases {

    // 설치사례 가져오기
    public List<ModelCases> getCasesList(){
        String weburl = "http://jwcctv1.cafe24.com/cases/getcaseslist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCases> SchoolList = null;

        int httpCode = 0;
        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");

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
            SchoolList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCases>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return SchoolList;
        }
    }
}
