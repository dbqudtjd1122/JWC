package com.example.bsyoo.jwc.hppt;


import com.example.bsyoo.jwc.model.Model_Camera;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class Http_Camera {

    public List<Model_Camera> getCameraList(String type) {
        String weburl = "http://192.168.0.11/jwccamera/cameralist";

        HttpRequest request = null;
        JSONArray response = null;
        List<Model_Camera> cameraList = null;

        int httpCode = 0;
        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("Cameratype", type);

            httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }

            String jsonInString = response.toString();
            cameraList = new Gson().fromJson(jsonInString, new TypeToken<List<Model_Camera>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return cameraList;
        }
    }

    public List<Model_Camera> getCameraInfoList(String series) {
        String weburl = "http://192.168.0.11/jwccamera/getCameraInfoList";

        HttpRequest request = null;
        JSONArray response = null;
        List<Model_Camera> cameraList = null;

        int httpCode = 0;
        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("Onlineseries", series);

            httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }

            String jsonInString = response.toString();
            cameraList = new Gson().fromJson(jsonInString, new TypeToken<List<Model_Camera>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return cameraList;
        }
    }
}
