/*package com.example.videoplayer;

import android.content.Intent;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

public class VideoPlayer extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("play".equals(action)) {
            String videoUrl = args.getString(0);
            playVideo(videoUrl);
            callbackContext.success();
            return true;
        }
        return false;
    }

    private void playVideo(String videoUrl) {
        Intent intent = new Intent(cordova.getActivity(), VideoPlayerActivity.class);
        intent.putExtra(VideoPlayerActivity.EXTRA_VIDEO_URL, videoUrl);
        cordova.getActivity().startActivity(intent);
    }
}*/






package com.example.videoplayer;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VideoPlayer extends CordovaPlugin {
    private static final String VIDEO_CACHE_NAME = "cached_video.mp4";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("play".equals(action)) {
            String videoUrl = args.getString(0);
            checkAndPlayVideo(videoUrl, callbackContext);
            return true;
        }
        return false;
    }

    private void checkAndPlayVideo(String videoUrl, CallbackContext callbackContext) {
        File cachedVideo = new File(cordova.getActivity().getFilesDir(), VIDEO_CACHE_NAME);
        
        if (cachedVideo.exists()) {
            // Play the cached video
            playVideo(cachedVideo.getAbsolutePath());
        } else {
            // Download the video asynchronously and then play it
            new DownloadVideoTask(videoUrl, callbackContext).execute();
        }
    }

    private void playVideo(String videoPath) {
        Intent intent = new Intent(cordova.getActivity(), VideoPlayerActivity.class);
        intent.putExtra(VideoPlayerActivity.EXTRA_VIDEO_URL, videoPath);
        cordova.getActivity().startActivity(intent);
    }

    private class DownloadVideoTask extends AsyncTask<Void, Void, String> {
        private String videoUrl;
        private CallbackContext callbackContext;

        public DownloadVideoTask(String videoUrl, CallbackContext callbackContext) {
            this.videoUrl = videoUrl;
            this.callbackContext = callbackContext;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(videoUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null; // Handle download error
                }

                InputStream input = connection.getInputStream();
                File outputFile = new File(cordova.getActivity().getFilesDir(), VIDEO_CACHE_NAME);
                FileOutputStream output = new FileOutputStream(outputFile);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }

                output.close();
                input.close();
                return outputFile.getAbsolutePath();
            } catch (Exception e) {
                Log.e("VideoPlayer", "Error downloading video", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String filePath) {
            if (filePath != null) {
                playVideo(filePath);
                callbackContext.success();
            } else {
                callbackContext.error("Failed to download video");
            }
        }
    }
}

