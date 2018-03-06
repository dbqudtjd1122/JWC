package com.jwcnetworks.bsyoo.jwc.hppt;


import com.jwcnetworks.bsyoo.jwc.model.ModelNews;
import com.jwcnetworks.bsyoo.jwc.model.ModelNotice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jwcnetworks.bsyoo.jwc.model.ModelSchool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpNotice {

    public List<ModelNotice> NoticeList(String type){
        String weburl = "http://jwcctv1.cafe24.com/notice/getnotice";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelNotice> noticeList = null;

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
            noticeList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelNotice>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return noticeList;
        }
    }

    public List<ModelNotice> EventList(String type){
        String weburl = "http://jwcctv1.cafe24.com/notice/getevent";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelNotice> noticeList = null;

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
            noticeList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelNotice>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return noticeList;
        }
    }

    public List<ModelNews> getNewsList(){
        String weburl = "http://jwcctv1.cafe24.com/news/getnewslist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelNews> newsList = null;

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
            newsList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelNews>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return newsList;
        }
    }
}
