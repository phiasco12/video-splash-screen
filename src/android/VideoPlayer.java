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
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

public class VideoPlayer extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("play".equals(action)) {
            String videoUrl = "cdvfile://localhost/www/Sebis12.mp4"; // Path to video in www folder
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
}

