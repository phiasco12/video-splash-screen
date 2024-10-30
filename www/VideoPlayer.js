/*var exec = require('cordova/exec');

var VideoPlayer = {
    play: function(videoUrl, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoUrl]);
    }
};

module.exports = VideoPlayer;*/


var VideoPlayer = {
    play: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', []);
    }
};

module.exports = VideoPlayer;

