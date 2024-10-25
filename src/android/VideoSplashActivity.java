package com.example.videosplash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;
import android.net.Uri;

public class VideoSplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VideoView videoView = new VideoView(this);
        setContentView(videoView);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(mp -> {
            finish();  // Close the splash activity and go back to main
        });

        videoView.start();
    }
}