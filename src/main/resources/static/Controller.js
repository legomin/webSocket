var stompClient = null;

function setConnected(connected) {
    document.getElementById('response').innerHTML = '';
}

function connect() {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function(greeting){
            //showGreeting(JSON.parse(greeting.body).type);
            showGreeting(greeting.body);
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
     stompClient.send("/app/hello", {}, JSON.stringify( getAuthMessage() ));
}

function getAuthMessage() {
    var message = { 'type': '', 'seqence_id': '', data: {'email': '', 'password': ''} };
    message.type = 'LOGIN_CUSTOMER';
    message.seqence_id = generateHexString(36);
    message.data.email = document.getElementById('email').value;
    message.data.password = document.getElementById('password').value;

    return message;
}

function generateHexString(length) {
    var ret = "";
    while (ret.length < length) {
        ret += Math.random().toString(16).substring(2);
    }
    return ret.substring(0,length);
}


function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}