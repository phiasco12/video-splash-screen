package com.example.videosplash;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.videosplash.R;  // Import the generated R class

public class VideoSplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VideoView videoView = new VideoView(this);
        setContentView(videoView);

        // Use the correct way to reference the raw resource
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
        videoView.setVideoURI(video);

        // Set a listener to finish the activity when the video completes
        videoView.setOnCompletionListener(mp -> {
            finish();  // Close the splash activity and go back to main
        });

        videoView.start();  // Start playing the video
    }
}
