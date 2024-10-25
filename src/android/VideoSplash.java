package com.example.videosplash;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.content.Intent; // Add this import
import android.net.Uri;
import android.os.Bundle;

public class VideoSplash extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("show".equals(action)) {
            showSplashVideo(callbackContext);
            return true;
        }
        return false;
    }

    private void showSplashVideo(CallbackContext callbackContext) {
        Intent intent = new Intent(cordova.getActivity(), VideoSplashActivity.class);
        cordova.getActivity().startActivity(intent);
        callbackContext.success();  // Call this when the video splash is shown successfully
    }
}
