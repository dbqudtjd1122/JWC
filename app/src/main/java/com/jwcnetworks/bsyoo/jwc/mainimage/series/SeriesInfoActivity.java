package com.jwcnetworks.bsyoo.jwc.mainimage.series;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelCamera;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SeriesInfoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private ModelCamera camera = new ModelCamera();

    private YouTubePlayerView youTubePlayerView;
    private String API_KEY = "";
    public static String VIDEO_ID = "J9dwKQ1yP98";
    private static final int RQS_ErrorDialog = 1;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_info);

        Intent intent = getIntent();
        camera = (ModelCamera) intent.getSerializableExtra("camera");
        level = intent.getIntExtra("level", -1);
        VIDEO_ID = camera.getYoutube().toString();
        API_KEY = camera.getYoutube().toString();

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        youTubePlayerView.initialize(API_KEY, this);

        ImageView img_series_info = (ImageView) findViewById(R.id.series_info);
        try {
            if(level == -1 || level == 1) {
                Glide.with(getApplicationContext()).load(camera.getOnline_Img_info().toString()).override(720, 4000).fitCenter().into(img_series_info);
            } else {
                Glide.with(getApplicationContext()).load(camera.getOffline_Img_info().toString()).override(720, 4000).fitCenter().into(img_series_info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 이미지 줌인, 아웃 (build.gradle 추가)
        PhotoViewAttacher photoview = new PhotoViewAttacher(img_series_info);
        photoview.setScaleType(ImageView.ScaleType.FIT_XY);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        /** Start buffering **/
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(this, RQS_ErrorDialog).show();
        } else {
            Toast.makeText(getApplicationContext(), "YouTubePlayer.onInitializationFailure(): " + result.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };
}
