/*package com.example.videoplayer;

import android.app.Activity;
import android.content.Intent;
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

        // Get the video URL passed from the VideoPlayer
        String videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_URL);

        // Load the video URI directly
        Uri uri = Uri.parse(videoUrl); // This will be 'cdvfile://localhost/www/local_video.mp4'
        
        videoView.setVideoURI(uri);
        videoView.setOnCompletionListener(mp -> finish()); // Close activity when video finishes
        videoView.start();
    }
}
