package com.example.bsyoo.jwc.hppt;


import com.example.bsyoo.jwc.model.ModelUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class HttpSignUp {

    public Integer signupinsert(ModelUser user){
        String weburl = "http://192.168.0.11/jwcuser/insert";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            ModelUser obj = user ;

            String data = new Gson().toJson(obj);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                response = request.getStringResponse(); // 서버값이 리턴된다
            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return Integer.valueOf(response);
    }

    public Integer IDCheck(ModelUser user){
        String weburl = "http://192.168.0.11/jwcuser/idcheck";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            ModelUser obj = user ;

            String data = new Gson().toJson(obj);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                response = request.getStringResponse(); // 서버값이 리턴된다
            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return Integer.valueOf(response);
    }

    public Integer EmailCheck(ModelUser user){
        String weburl = "http://192.168.0.11/jwcuser/emailcheck";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            ModelUser obj = user ;

            String data = new Gson().toJson(obj);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                response = request.getStringResponse(); // 서버값이 리턴된다
            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return Integer.valueOf(response);
    }

    public ModelUser Login(ModelUser model){
        String weburl = "http://192.168.0.11/jwcuser/login";

        HttpRequest request = null;
        JSONObject response = null;
        ModelUser user = null ;
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
            user = new Gson().fromJson(jsonInString, ModelUser.class);

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
