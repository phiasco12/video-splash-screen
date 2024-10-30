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
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity {

    public static final String EXTRA_VIDEO_URL = "videoUrl";
    private VideoView leftVideoView;
    private VideoView rightVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full-screen mode
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        // Create layout and set parameters
        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        ));

        // Initialize two VideoViews
        leftVideoView = new VideoView(this);
        rightVideoView = new VideoView(this);

        // Set layout params for each VideoView to take up half of the screen
        int halfScreenWidth = getResources().getDisplayMetrics().widthPixels / 2;
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(
                halfScreenWidth,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(
                halfScreenWidth,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        // Apply clipping to each half
        leftVideoView.setLayoutParams(leftParams);
        rightVideoView.setLayoutParams(rightParams);

        // Add VideoViews to layout
        layout.addView(leftVideoView);
        layout.addView(rightVideoView);
        setContentView(layout);

        // Get the video URL and set it for both VideoViews
        String videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_URL);
        Uri videoUri = Uri.parse(videoUrl);
        leftVideoView.setVideoURI(videoUri);
        rightVideoView.setVideoURI(videoUri);

        // Synchronize playback: start both VideoViews at the same time
        leftVideoView.setOnPreparedListener(mp -> {
            rightVideoView.start();
            leftVideoView.start();
        });

        // Set listener for completion to animate disappearance
        leftVideoView.setOnCompletionListener(mp -> animateVideoDisappearance());
    }

    private void animateVideoDisappearance() {
        // Create animator set to play animations together
        AnimatorSet animatorSet = new AnimatorSet();

        // Animate left VideoView to move left
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(leftVideoView, "translationX", -leftVideoView.getWidth());
        leftAnimator.setDuration(500); // Animation duration in milliseconds

        // Animate right VideoView to move right
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(rightVideoView, "translationX", rightVideoView.getWidth());
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
