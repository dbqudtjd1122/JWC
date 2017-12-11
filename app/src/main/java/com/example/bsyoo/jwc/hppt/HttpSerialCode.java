package com.example.bsyoo.jwc.hppt;

import com.example.bsyoo.jwc.model.ModelCamera;
import com.example.bsyoo.jwc.model.ModelSerialCode;
import com.example.bsyoo.jwc.model.ModelUserSerialCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;


public class HttpSerialCode {

    // 개인이 등록이 시리얼코드 리스트 가져오기
    public List<ModelUserSerialCode> getSerialCodeList(ModelUserSerialCode model){
        String weburl = "http://61.75.50.145:8187/jwcserialcode/getserialcodelist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelUserSerialCode> codelist = null ;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

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
            codelist = new Gson().fromJson(jsonInString, new TypeToken<List<ModelUserSerialCode>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return codelist;
        }
    }

    // 시리얼코드 확인 및 조회
    public ModelCamera selectSerialCode(ModelSerialCode model){
        String weburl = "http://61.75.50.145:8187/jwcserialcode/serialcodecheck";

        HttpRequest request = null;
        JSONObject response = null;
        ModelCamera camera = null ;

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
            camera = new Gson().fromJson(jsonInString, ModelCamera.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return camera;
        }
    }

    // 시리얼 등록
    public Integer InsertSerial(ModelUserSerialCode user){
        String weburl = "http://61.75.50.145:8187/jwcserialcode/insertserial";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            ModelUserSerialCode obj = user ;

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

    // 시리얼 삭제
    public Integer DeleteSerial(ModelUserSerialCode user){
        String weburl = "http://61.75.50.145:8187/jwcserialcode/deleteserial";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // Model을 json으로 변환
            ModelUserSerialCode obj = user ;

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

}
