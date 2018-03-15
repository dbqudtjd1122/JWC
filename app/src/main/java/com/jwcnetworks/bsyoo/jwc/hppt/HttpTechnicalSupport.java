package com.jwcnetworks.bsyoo.jwc.hppt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jwcnetworks.bsyoo.jwc.model.ModelTechnicalSupport;
import com.jwcnetworks.bsyoo.jwc.model.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpTechnicalSupport {

    public List<ModelTechnicalSupport> getTechnicalSupport(ModelUser user){
        String weburl = "http://jwcctv1.cafe24.com/technical/getTechnicalSupport";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelTechnicalSupport> TechnicalList = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(user);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }

            String jsonInString = response.toString();
            TechnicalList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelTechnicalSupport>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return TechnicalList;
        }
    }

    // 문의글 작성
    public Integer insertTechnicalSupport(ModelTechnicalSupport technical) {
        String weburl = "http://jwcctv1.cafe24.com/technical/insertTechnicalSupport";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(technical);

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

    // 문의글 업데이트
    public Integer updateTechnicalSupport(ModelTechnicalSupport technical) {
        String weburl = "http://jwcctv1.cafe24.com/technical/updateTechnicalSupport";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(technical);

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

    // 문의글 삭제(업데이트)
    public Integer deleteTechnicalSupport(ModelTechnicalSupport technical) {
        String weburl = "http://jwcctv1.cafe24.com/technical/deleteTechnicalSupport";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(technical);

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

    // 문의글 답변(업데이트)
    public Integer updateManagerTechnical(ModelTechnicalSupport technical) {
        String weburl = "http://jwcctv1.cafe24.com/technical/updateManagerTechnical";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(technical);

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

    // 푸시 날리기
    public Integer Tokenstart(ModelTechnicalSupport technical){
        String weburl = "http://jwcctv1.cafe24.com/technicalsuppor/send";

        HttpRequest request = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(technical);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                // 리턴값 없음
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return null;
    }


    // 답변 푸시 날리기
    public Integer ManagerTokenstart(ModelTechnicalSupport technical){
        String weburl = "http://jwcctv1.cafe24.com/technicalsuppor/managersend";

        HttpRequest request = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(technical);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                // 리턴값 없음
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return null;
    }
}