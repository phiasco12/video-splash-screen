/*var exec = require('cordova/exec');

var VideoPlayer = {
    play: function(videoUrl, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoUrl]);
    }
};

module.exports = VideoPlayer;*/


var exec = require('cordova/exec');

var VideoPlayer = {
    play: function(videoFileName, successCallback, errorCallback) {
        // Pass only the filename, like "local_splash_video.mp4"
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoFileName]);
    }
};

module.exports = VideoPlayer;

