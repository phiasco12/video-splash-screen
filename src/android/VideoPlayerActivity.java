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



package com.example.videoplayer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity {

    public static final String EXTRA_VIDEO_URL = "videoUrl";
    private VideoView videoView;
    private FrameLayout leftHalf;
    private FrameLayout rightHalf;

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

        // Create two halves of the VideoView
        leftHalf = new FrameLayout(this);
        rightHalf = new FrameLayout(this);
        videoView = new VideoView(this);

        // Layout parameters
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        videoView.setLayoutParams(params);
        
        // Set the video view to fill the entire screen
        layout.addView(videoView);
        setContentView(layout);

        // Get the video URL and play it
        String videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_URL);
        videoView.setVideoURI(Uri.parse(videoUrl));

        // Start playback without showing controls
        videoView.setOnPreparedListener(mp -> {
            videoView.start();
        });

        // Set listener for completion to animate disappearance
        videoView.setOnCompletionListener(mp -> {
            animateVideoDisappearance();
        });
    }

    private void animateVideoDisappearance() {
        // Create two half animations
        AnimatorSet animatorSet = new AnimatorSet();

        // Create left half animation
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(videoView, "translationX", -videoView.getWidth() / 2);
        leftAnimator.setDuration(500); // Animation duration in milliseconds

        // Create right half animation
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(videoView, "translationX", videoView.getWidth() / 2);
        rightAnimator.setDuration(500); // Animation duration in milliseconds

        // Play both animations together
        animatorSet.playTogether(leftAnimator, rightAnimator);
        animatorSet.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                finish(); // Close the activity after the animation ends
            }
        });

        animatorSet.start(); // Start the animation
    }
}
