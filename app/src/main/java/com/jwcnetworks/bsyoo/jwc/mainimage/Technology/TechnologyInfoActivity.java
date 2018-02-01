package com.jwcnetworks.bsyoo.jwc.mainimage.Technology;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jwcnetworks.bsyoo.jwc.R;
import com.jwcnetworks.bsyoo.jwc.model.ModelTechnology;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TechnologyInfoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private ModelTechnology tech = new ModelTechnology();

    private YouTubePlayerView youTubePlayerView;
    private String API_KEY = "";
    public static String VIDEO_ID = "J9dwKQ1yP98";  // url
    private static final int RQS_ErrorDialog = 2;
    private TextView tv_technology_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology_info);


        Intent intent = getIntent();
        tech = (ModelTechnology) intent.getSerializableExtra("tech");
        VIDEO_ID = tech.getYouTubeUrl().toString();
        API_KEY = tech.getYouTubeUrl().toString();

        tv_technology_title = (TextView) findViewById(R.id.tv_technology_title);
        tv_technology_title.setText(tech.getYouTubeName().toString());


        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.view_technology);
        youTubePlayerView.initialize(API_KEY, this);

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
