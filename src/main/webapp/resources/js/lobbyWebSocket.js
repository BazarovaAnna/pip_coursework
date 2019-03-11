var stompClient = null;

$("#share-game-btn").click(connect);

$("#start-game-btn").click(sendName);

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/joining', function (msg) {
            alert(msg.body);
        });
    });
}

function sendName() {
    stompClient.send("/app/lobby/connect", {}, {});
}
