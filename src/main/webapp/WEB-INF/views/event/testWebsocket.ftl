<html>
<head>
    <title>Chat WebSocket</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/0.3.4/sockjs.min.js"
            integrity="sha512-Tp59ajolL5b7bHPv8WsEfdNXqA0bq2NohJ9jK9yy+HRCvwGpjdpc8ay3F2KtbU9dC22QIOS7ek1n2Vep3ohqtQ=="
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
            integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
            crossorigin="anonymous"></script>

    <script type="text/javascript">
        var stompClient = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility
                = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }

        function connect() {
            var socket = new SockJS('/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/messages', function(messageOutput) {
                    showMessageOutput(messageOutput.body);
                });
            });
        }

        function disconnect() {
            if(stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }

        function sendMessage() {
            var from = document.getElementById('from').value;
            var text = document.getElementById('text').value;
            stompClient.send("/app/chat", {},
                JSON.stringify({'from':from, 'text':text}));
        }

        function showMessageOutput(messageOutput) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(messageOutput.from + ": "
                + messageOutput.text + " (" + messageOutput.time + ")"));
            response.appendChild(p);
        }
    </script>
</head>
<body onload="disconnect()">
<div>
    <div>
        <input type="text" id="from" placeholder="Choose a nickname"/>
    </div>
    <br />
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">
            Disconnect
        </button>
    </div>
    <br />
    <div id="conversationDiv">
        <input type="text" id="text" placeholder="Write a message..."/>
        <button id="sendMessage" onclick="sendMessage();">Send</button>
        <p id="response"></p>
    </div>
</div>

</body>
</html>