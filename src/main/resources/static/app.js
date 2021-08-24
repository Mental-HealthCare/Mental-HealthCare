var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/stomp-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
    var fields = greeting.body.split(" #$% ");
    console.log(fields[0]);
    console.log(fields[1]);
            showGreeting(fields);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, ($("#name").val()));
}

function showGreeting(message) {
    var today = new Date();
    $("#greetings").append("<tr class=\"usernameClass\"><td>"+message[1]+"</td></tr>"+   "<tr><td class=\"messageClass\">" + message[0] + "</td></tr>" +"<tr><td class=\"hourClass\">"+ message[2] + "</td></tr>" );
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});