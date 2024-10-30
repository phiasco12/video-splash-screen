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
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', [videoUrl]);
    },
    getLocalVideoUrl: function(fileName) {
        // Ensure the Cordova File plugin is available
        return cordova.file.applicationDirectory + 'www/' + fileName;
    }
};

module.exports = VideoPlayer;

