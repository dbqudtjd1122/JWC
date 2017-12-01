package com.example.bsyoo.jwc.hppt;

import com.example.bsyoo.jwc.model.ModelSchool;
import com.example.bsyoo.jwc.model.ModelSchoolUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpSchool {

    // 교육 리스트 가져오기
    public List<ModelSchool> SchoolList(){
        String weburl = "http://192.168.0.11/school/getSchool";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelSchool> SchoolList = null;

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
            SchoolList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelSchool>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return SchoolList;
        }
    }

    // 교육신청 마무리
    public Integer insertSchoolUser(ModelSchoolUser user){
        String weburl = "http://192.168.0.11/school/insertschooluser";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            ModelSchoolUser obj = user ;

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

    // 교육신청정보 가져오기
    public Integer getSchoolUser(ModelSchoolUser model){
        String weburl = "http://192.168.0.11/school/getschooluser";

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
        }  finally {
            request.close();
            return Integer.valueOf(response);
        }
    }
}
