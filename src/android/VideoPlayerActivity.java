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

        // Create the VideoView to fill the entire screen
        videoView = new VideoView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        videoView.setLayoutParams(params);
        layout.addView(videoView);

        // Create two overlays for the left and right halves
        leftHalf = new FrameLayout(this);
        rightHalf = new FrameLayout(this);

        RelativeLayout.LayoutParams halfParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        halfParams.width = getResources().getDisplayMetrics().widthPixels / 2;

        // Position left half on the left side
        halfParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftHalf.setLayoutParams(halfParams);
        leftHalf.setBackgroundColor(0xFF000000); // Black color
        layout.addView(leftHalf);

        // Position right half on the right side
        RelativeLayout.LayoutParams rightHalfParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        rightHalfParams.width = getResources().getDisplayMetrics().widthPixels / 2;
        rightHalfParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightHalf.setLayoutParams(rightHalfParams);
        rightHalf.setBackgroundColor(0xFF000000); // Black color
        layout.addView(rightHalf);

        // Set the content view to the layout
        setContentView(layout);

        // Get the video URL and play it
        String videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_URL);
        videoView.setVideoURI(Uri.parse(videoUrl));

        // Start playback without showing controls
        videoView.setOnPreparedListener(mp -> videoView.start());

        // Set listener for completion to animate disappearance
        videoView.setOnCompletionListener(mp -> animateVideoDisappearance());
    }

    private void animateVideoDisappearance() {
        // Create animator set to play animations together
        AnimatorSet animatorSet = new AnimatorSet();

        // Animate left overlay to move left
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(leftHalf, "translationX", -leftHalf.getWidth());
        leftAnimator.setDuration(500); // Animation duration in milliseconds

        // Animate right overlay to move right
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(rightHalf, "translationX", rightHalf.getWidth());
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
