var exec = require('cordova/exec');

var VideoSplash = {
    show: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, "VideoSplash", "show", []);
    }
};

module.exports = VideoSplash;

