/*var exec = require('cordova/exec');

var VideoPlayer = {
    play: function(videoUrl, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoUrl]);
    }
};

module.exports = VideoPlayer;*/

var exec = require('cordova/exec');

var VideoPlayer = {
    play: function(videoPath, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoPath]);
    }
};

module.exports = VideoPlayer;

