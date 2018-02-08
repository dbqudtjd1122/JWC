package com.jwcnetworks.bsyoo.jwc.hppt;


import com.jwcnetworks.bsyoo.jwc.model.ModelCamera;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpCamera {

    public List<ModelCamera> getCameraList(String type) {
        String weburl = "http://jwcctv1.cafe24.com/jwccamera/cameralist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCamera> cameraList = null;

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
            cameraList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCamera>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return cameraList;
        }
    }

    public List<ModelCamera> getCameraInfoList(String series) {
        String weburl = "http://jwcctv1.cafe24.com/jwccamera/getCameraInfoList";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCamera> cameraList = null;

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
            cameraList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCamera>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return cameraList;
        }
    }

    public List<ModelCamera> getCameraNewList(String newcamera) {
        String weburl = "http://jwcctv1.cafe24.com/jwccamera/getCameraNewList";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCamera> cameraList = null;

        int httpCode = 0;
        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("Newcamera", newcamera);

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
            cameraList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCamera>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return cameraList;
        }
    }

    public List<ModelCamera> getCameraSearchList(String name, String type) {
        String weburl = "http://jwcctv1.cafe24.com/jwccamera/getCameraSearchList";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCamera> cameraList = null;

        int httpCode = 0;
        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("name", name);
            request.addParameter("type", type);

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
            cameraList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCamera>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return cameraList;
        }
    }
}
