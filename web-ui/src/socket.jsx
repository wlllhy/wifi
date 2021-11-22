

const Singleton = (function () {
    let instance;

    function createInstance() {
        const io = require('socket.io-client')
        const socket = io.connect('ws://192.168.1.242:8080');
        return socket;
    }

    return {
        getInstance: function () {
            if (!instance) {
                instance = createInstance();
            }
            return instance;
        }
    };
})();

export default Singleton;