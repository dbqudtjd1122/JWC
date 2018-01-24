package com.jwcnetworks.bsyoo.jwc.hppt;


import com.jwcnetworks.bsyoo.jwc.model.ModelAgency;
import com.jwcnetworks.bsyoo.jwc.model.ModelAgencyTopic;
import com.jwcnetworks.bsyoo.jwc.model.ModelAgencyTopicReview;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpAgency {

    // 대리점 목록 및 내용
    public List<ModelAgency> getAgencyList(){
        String weburl = "http://jwcctv1.cafe24.com/agency/getagencylist";

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

    // 대리점 게시판 리스트 가져오기
    public List<ModelAgencyTopic> getAgencyTopicList(){
        String weburl = "http://jwcctv1.cafe24.com/agency/getagencytopic";

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

    // 게시글 작성
    public Integer InsertTopic(ModelAgencyTopic topic){
        String weburl = "http://jwcctv1.cafe24.com/agency/inserttopic";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(topic);

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

    // 게시글 업데이트
    public Integer UpdateTopic(ModelAgencyTopic topic){
        String weburl = "http://jwcctv1.cafe24.com/agency/updatetopic";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(topic);

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

    // 게시글 삭제
    public Integer DeleteTopic(ModelAgencyTopic topic){
        String weburl = "http://jwcctv1.cafe24.com/agency/deletetopic";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(topic);

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

    // 대리점 리뷰 리스트 가져오기
    public List<ModelAgencyTopicReview> getAgencyReviewList(ModelAgencyTopic topic){
        String weburl = "http://jwcctv1.cafe24.com/agency/getreview";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelAgencyTopicReview> ReviewList = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(topic);

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
            ReviewList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelAgencyTopicReview>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return ReviewList;
        }
    }

    // 리뷰 작성
    public Integer InsertReview(ModelAgencyTopicReview review){
        String weburl = "http://jwcctv1.cafe24.com/agency/insertreview";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(review);

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

    // 리뷰 삭제
    public Integer DeleteReview(ModelAgencyTopicReview review){
        String weburl = "http://jwcctv1.cafe24.com/agency/deletereview";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(review);

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

    // 푸시 날리기
    public Integer Tokenstart(){
        String weburl = "http://jwcctv1.cafe24.com/send";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");

            httpCode = request.post();

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
