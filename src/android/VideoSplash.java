package com.example.videosplash;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;

public class VideoSplash extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("show".equals(action)) {
            showSplashVideo(callbackContext);
            return true;
        }
        callbackContext.error("Invalid action: " + action);
        return false;
    }

    private void showSplashVideo(CallbackContext callbackContext) {
        Intent intent = new Intent(cordova.getActivity(), VideoSplashActivity.class);
        cordova.getActivity().startActivity(intent);
        callbackContext.success("Video splash shown successfully."); // Indicate success
    }
}
