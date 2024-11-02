/*package com.example.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import android.widget.RelativeLayout;

import android.util.Log;

public class VideoPlayerActivity extends Activity {

    public static final String EXTRA_VIDEO_URL = "videoUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full-screen mode
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        // Create a layout and add VideoView to play video
        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        ));

        VideoView videoView = new VideoView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        );
        videoView.setLayoutParams(params);

        layout.addView(videoView);
        setContentView(layout);

        // Get the video URL and play it
        String videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_URL);
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.setOnCompletionListener(mp -> finish()); // Close activity when video finishes
        videoView.start();
    }
}*/



/*package com.example.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.VideoView;
import android.widget.RelativeLayout;

public class VideoPlayerActivity extends Activity {

    public static final String EXTRA_VIDEO_URL = "videoUrl";
    private static final int FADE_OUT_DELAY = 1500; // Delay in milliseconds before fade-out starts
    private static final int FADE_OUT_DURATION = 1000; // Duration of fade-out animation in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full-screen mode
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        // Create a layout and add VideoView to play video
        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        ));

        // Set background color for the layout
        layout.setBackgroundColor(Color.parseColor("#000000")); // Black background

        // Create VideoView and set it to fill the layout but stay centered
        VideoView videoView = new VideoView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        );
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); // Center the videoView
        videoView.setLayoutParams(params);

        layout.addView(videoView);
        setContentView(layout);

        // Get the video URL and play it
        String videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_URL);
        if (videoUrl != null) {
            videoView.setVideoURI(Uri.parse(videoUrl));
        } else {
            finish(); // End the activity if no video URL
            return;
        }

        // Set listener for completion
        videoView.setOnCompletionListener(mp -> {
            // Delay fade-out animation by FADE_OUT_DELAY milliseconds
            new Handler().postDelayed(() -> {
                // Create fade-out animation
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(FADE_OUT_DURATION); // Duration of fade-out
                fadeOut.setFillAfter(true); // Keep view invisible after fade-out

                // Finish activity after fade-out
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish(); // Close activity after fade-out
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });

                // Start fade-out animation
                videoView.startAnimation(fadeOut);
            }, FADE_OUT_DELAY);
        });

        // Start playback without showing controls
        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(false); // Set looping if needed
            videoView.start();
        });
    }
}*/





package com.example.videoplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import android.widget.RelativeLayout;

public class VideoPlayerActivity extends Activity {

    public static final String EXTRA_VIDEO_URL = "videoUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup full-screen and layout configurations as before
        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        ));

        VideoView videoView = new VideoView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        );
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        videoView.setLayoutParams(params);
        layout.addView(videoView);
        setContentView(layout);

        String videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_URL);

        // Check if the video URL is provided and is local
        if (videoUrl != null) {
            videoView.setVideoURI(Uri.parse(videoUrl));
        } else {
            finish();
            return;
        }

        videoView.setOnPreparedListener(mp -> videoView.start());
        videoView.setOnCompletionListener(mp -> finish());
    }
}
