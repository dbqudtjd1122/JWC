package com.example.bsyoo.jwc.hppt;


import com.example.bsyoo.jwc.model.Model_Camera;
import com.example.bsyoo.jwc.model.Model_Notice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class Http_Notice {

    public List<Model_Notice> NoticeList(String type){
        String weburl = "http://192.168.0.11/notice/getnotice";

        HttpRequest request = null;
        JSONArray response = null;
        List<Model_Notice> noticeList = null;

        int httpCode = 0;
        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("notice_type", type);

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
            noticeList = new Gson().fromJson(jsonInString, new TypeToken<List<Model_Notice>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return noticeList;
        }
    }

    public List<Model_Notice> EventList(String startend, String type){
        String weburl = "http://192.168.0.11/notice/getevent";

        HttpRequest request = null;
        JSONArray response = null;
        List<Model_Notice> noticeList = null;

        int httpCode = 0;
        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("startend", startend);
            request.addParameter("type", type);

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
            noticeList = new Gson().fromJson(jsonInString, new TypeToken<List<Model_Notice>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return noticeList;
        }
    }
}
