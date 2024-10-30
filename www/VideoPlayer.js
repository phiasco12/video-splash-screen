var VideoPlayer = {
    play: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'VideoPlayer', 'play', []);
    }
};

module.exports = VideoPlayer;
