package com.example.bsyoo.jwc.hppt;


import com.example.bsyoo.jwc.model.Model_User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Http_SignUp {

    public Integer signupinsert(Model_User user){
        String weburl = "http://192.168.0.11/jwcuser/insert";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            Model_User obj = user ;

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

    public Integer IDCheck(Model_User user){
        String weburl = "http://192.168.0.11/jwcuser/idcheck";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            Model_User obj = user ;

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

    public Integer EmailCheck(Model_User user){
        String weburl = "http://192.168.0.11/jwcuser/emailcheck";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            Model_User obj = user ;

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

    public Model_User Login(Model_User model){
        String weburl = "http://192.168.0.11/jwcuser/login";

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