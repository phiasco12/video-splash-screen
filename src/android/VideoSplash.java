package com.example.videosplash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class VideoSplash extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("show")) {
            showVideoSplash();
            callbackContext.success();
            return true;
        }
        return false;
    }

    private void showVideoSplash() {
        Activity activity = this.cordova.getActivity();
        Intent intent = new Intent(activity, VideoSplashActivity.class);
        activity.startActivity(intent);
    }
}
