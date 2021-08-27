var stompClient = null;


function connect() {
    var socket = new SockJS('/stomp-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
    var fields = greeting.body.split(" #$% ");
    console.log(fields[0]);
    console.log(fields[1]);
            showGreeting(fields);
        });
    });
}

connect();

function sendName() {
    stompClient.send("/app/hello", {}, ($("#name").val()));
}


function showGreeting(message) {
    $("#greetings").append("<p class=\"usernameClass\">"+message[0]+"</p>"+   "<h3 class=\"messageClass\">" + message[1] + "</h3>" +"<p class=\"hourClass\">"+ message[2] + "</p>" );
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
});