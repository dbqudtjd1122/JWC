package com.jwcnetworks.bsyoo.jwc.hppt;


import com.jwcnetworks.bsyoo.jwc.model.ModelPushToken;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class HttpUser {

    // 아이디찾기
    public ModelUser IDSearch1(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/idsearch";
        // String weburl = "http://61.75.50.145:8187/jwcuser/idsearch";

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

    // 비밀번호 찾기
    public ModelUser PWSearch1(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/pwsearch";

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

    // 비밀번호 업데이트
    public Integer PWSearch2(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/pwchange";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

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
            return Integer.valueOf(response);
        }
    }

    // 회원정보 가져오기
    public ModelUser getLoginInfomation(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/getuserinfo";

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

    // 회원정보 업데이트
    public Integer LoginInfoUpdate(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/loginupdate";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

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
            return Integer.valueOf(response);
        }
    }

    // 회원정보 삭제
    public Integer deleteuser(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/deleteuser";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

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
            return Integer.valueOf(response);
        }
    }

    // 로그아웃 토큰 초기화(업데이트)
    public Integer TokenUpdate(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/updatetoken";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

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
            return Integer.valueOf(response);
        }
    }

    // 푸시 로그 기록
    public Integer InsertpushLog (ModelPushToken model){
        String weburl = "http://jwcctv1.cafe24.com/jwcpush/insertpushlog";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

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
            if(response != null) {
                return Integer.valueOf(response);
            }else {
                return -1;
            }
        }
    }
}
