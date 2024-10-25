/*package com.example.videosplash;

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
}*/

package com.example.videosplash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;
import android.net.Uri;
import android.widget.MediaController;

public class VideoSplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VideoView videoView = new VideoView(this);
        setContentView(videoView);

        // Use a remote URL for the video
        String videoUrl = "https://rooz-dev.co.uk/splash_video.mp4"; // Replace with your remote video URL
        Uri video = Uri.parse(videoUrl);
        videoView.setVideoURI(video);

        // Optionally add media controls
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnCompletionListener(mp -> {
            finish();  // Close the splash activity and go back to the main app
        });

        videoView.start();
    }
}

