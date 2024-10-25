package com.example.videosplash;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

public class VideoSplash extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("show")) {
            showSplash(callbackContext);
            return true;
        }
        return false;
    }

    private void showSplash(CallbackContext callbackContext) {
        // Start the VideoSplashActivity
        Intent intent = new Intent(cordova.getActivity(), VideoSplashActivity.class);
        cordova.getActivity().startActivity(intent);
        callbackContext.success(); // Call success callback
    }
}
