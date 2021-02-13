var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

// Connect to WebSocket Server.
connect();

//onConnected
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/publicChatRoom', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({ sender: username, type: 'JOIN' })
    )

    $("#connectStatus").hide();
}

//onError
function onError(error) {
    $("#connectStatus").val('Could not connect to WebSocket server. Please refresh this page to try again!');
    $("#connectStatus").css("color", "red");
}

$('#messageForm').submit((event) => {
    event.preventDefault();
    let message = $('#messageForm textarea').val().trim();
    sendMessage(message);
    $('#messageForm textarea').val("");
});

$('#messageForm textarea').on({
    keyup: (e) => {
        if (e.ctrlKey == true && e.keyCode == 13) {
            $('#messageForm textarea').val($('#messageForm textarea').val() + "\n");
        }
        if (e.ctrlKey == false && e.keyCode == 13) {
            e.preventDefault();
            let message = $('#messageForm textarea').val();
            if (message != "") {
                sendMessage(message);
                $('#messageForm textarea').val("");
            }
        }
    },
    keypress: (e) => {
        if (e.ctrlKey == false && e.keyCode == 13) {
            e.preventDefault();
        }
    },
    keydown: (e) => {
        if (e.ctrlKey == false && e.keyCode == 13) {
            if ($('#messageForm textarea').val() == "") {
                e.preventDefault();
            }
        }
    }
});

function sendMessage(message) {
    if (message && stompClient) {
        var chatMessage = {
            sender: username,
            content: message,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
    }

}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    if (message.type === 'JOIN') {
        $("#messageArea").append('<div class="d-flex justify-content-center">'
            + '<span class="text-light">' + message.sender + ' joined</span>'
            + '</div>');
    } else if (message.type === 'LEAVE') {
        $("#messageArea").append('<div class="d-flex justify-content-center">'
            + '<span class="text-light">' + message.sender + ' left</span>'
            + '</div>');
    } else {
        if (message.sender === username) {
            $("#messageArea").append('<div class="d-flex justify-content-end mb-4 w-auto">'
                + '<div class="msg-send">'
                + message.content
                + '</div>'
                + '<div class="align-self-center">'
                + '<img src="https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg"'
                + 'class="rounded-circle msg-image">'
                + '</div>'
                + '</div>');
        } else {
            $("#messageArea").append('<div class="d-flex justify-content-start mb-4 w-auto">'
                + '<div class="align-self-center">'
                + '<img src="https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg"'
                + 'class="rounded-circle msg-image">'
                + '</div>'
                + '<div class="msg-receive">'
                + message.content
                + '</div>'
                + '</div>');
        }
    }
}