package com.example.bsyoo.jwc.fmcpush;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class FirebaseInstanceIDService extends FirebaseInstanceIdService{

    private static final String TAG = "MyFirebaseIIDService";

    public static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

    @Override
    public void onTokenRefresh() {

        // 설치할때 여기서 토큰을 자동으로 만들어 준다
        String token = FirebaseInstanceId.getInstance().getToken();

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /*private void sendPost() throws Exception {
        String url = "https://fcm.googleapis.com/fcm/send";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("content-type", "application/json");
        con.setRequestProperty("authorization", "key=api키");
        String urlParameters = "{\"data\" : " +
                              "{\"message\" : " +
                "{\"title\":\"test\",\"content\":\"testcontent\",\"imgUrl\":\"\",\"link\":\"\"}},\"to\" : \"/topics/noticeMsg\"}";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }*/
}
