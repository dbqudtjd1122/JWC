package com.jwcnetworks.bsyoo.jwc.hppt;

import com.jwcnetworks.bsyoo.jwc.model.ModelUser;
import com.google.gson.Gson;
import com.jwcnetworks.bsyoo.jwc.user.Encrypt.SecretCode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class HttpSignUp {

    public String pw = "";

    // 회원가입
    public Integer signupinsert(ModelUser user) {
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/insert";

        HttpRequest request = null;
        String response = null;

        try {
            pw = SecretCode.AES_Encode(user.getPW().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        user.setPW(pw);

        int httpCode = 0;
        try {
            // ModelUser을 json으로 변환
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
            if(response != null) {
                return Integer.valueOf(response);
            }else {
                return -1;
            }
        }
    }

    // 아이디 중복확인
    public Integer IDCheck(ModelUser user){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/idcheck";

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
            if(response != null) {
                return Integer.valueOf(response);
            }else {
                return -1;
            }
        }
    }

    // 이메일 중복확인
    public Integer EmailCheck(ModelUser user){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/emailcheck";

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
            if(response != null) {
                return Integer.valueOf(response);
            }else {
                return -1;
            }
        }
    }

    // 로그인
    public ModelUser Login(ModelUser model){
        String weburl = "http://jwcctv1.cafe24.com/jwcuser/login";

        HttpRequest request = null;
        JSONObject response = null;
        ModelUser user = null ;

        try {
            pw = SecretCode.AES_Encode(model.getPW().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        model.setPW(pw);

        int httpCode = 0;
        try {
            String data = new Gson().toJson(model);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){      // HttpURLConnection.HTTP_OK == 200
                response = request.getJSONObjectResponse(); // 서버값이 리턴된다
            } else {
            }

            String jsonInString = response.toString();
            user = new Gson().fromJson(jsonInString, ModelUser.class);
            try {
                String pw2 = SecretCode.AES_Decode(user.getPW().toString());
                user.setPW(pw2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }

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
