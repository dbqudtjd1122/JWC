package com.example.bsyoo.jwc.hppt;


import com.example.bsyoo.jwc.model.ModelAgency;
import com.example.bsyoo.jwc.model.ModelAgencyTopic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpAgency {

    public List<ModelAgency> getAgencyList(){
        String weburl = "http://61.75.50.145:8187/agency/getagencylist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelAgency> AgencyList = null;

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
            AgencyList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelAgency>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return AgencyList;
        }
    }

    public List<ModelAgencyTopic> getAgencyTopicList(){
        String weburl = "http://61.75.50.145:8187/agency/getagencytopic";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelAgencyTopic> AgencyList = null;

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
            AgencyList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelAgencyTopic>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return AgencyList;
        }
    }
}
