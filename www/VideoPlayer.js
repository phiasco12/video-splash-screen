/*var exec = require('cordova/exec');

var VideoPlayer = {
    play: function(videoUrl, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoUrl]);
    }
};

module.exports = VideoPlayer;*/

var exec = require('cordova/exec');

var VideoPlayer = {
    play: function(videoUrl, successCallback, errorCallback) {
        // Ensure that the URL passed points to a local resource
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoUrl]);
    }
};

module.exports = VideoPlayer;

