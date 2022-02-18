function getGap(gapSize) {
    const gap = document.createElement('div');
    if (gapSize === 10) {
        gap.className = 'gap-10';
    } else if (gapSize === 20) {
        gap.className = 'gap-20';
    }
    return gap;
}

function getHead(room) {
    const head = document.createElement('p');
    head.className = 'text-center';
    const headSpan = document.createElement('span');
    const userBadgeHead = document.createElement('span');
    userBadgeHead.className = 'badge bg-secondary';
    userBadgeHead.innerText = room.owner;
    headSpan.innerText = 'Комната: "' + room.name + '", владелец: ';
    headSpan.append(userBadgeHead);
    headSpan.className = 'p-3 border border-2 rounded-3 message';
    head.append(headSpan);
    return head;
}

function getMessage(message) {
    const messageParagraph = document.createElement('p');
    const messageSpan = document.createElement('span');
    const userBadgeMessage = document.createElement('span');
    const button = document.createElement('button');
    const i = document.createElement('i');
    i.className = 'bi-trash-fill';
    button.type = 'button';
    button.className = 'btn btn-warning m-1 deleteMessage';
    button.title = 'Удалить сообщение';
    button.name = message.id;
    button.append(i);
    userBadgeMessage.className = 'badge bg-secondary';
    userBadgeMessage.innerText = message.author;
    messageSpan.className = 'm-1 p-2 border border-2 rounded-3 message';
    messageSpan.innerHTML = message.content + ' ';
    messageSpan.append(userBadgeMessage);
    messageParagraph.append(messageSpan);
    messageParagraph.append(button);
    return messageParagraph;
}

function deleteMessage(messageId) {
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 409) {
                document.getElementById('errorDeleteMessageTab').hidden = false;
            } else if (xmlHttpRequest.status === 200) {
                drawRoom();
            }
        }
    }
    xmlHttpRequest.open( 'DELETE', 'http://localhost:8080/message/' + messageId);
    xmlHttpRequest.send();
}

function drawRoom() {
    const roomDiv = document.getElementById('room');
    while (roomDiv.firstChild) {
        roomDiv.removeChild(roomDiv.firstChild);
    }
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open( 'GET', 'http://localhost:8080/room/' + roomId );
    xmlHttpRequest.send();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            const room =  JSON.parse(xmlHttpRequest.responseText);
            roomDiv.append(getGap(20));
            roomDiv.append(getHead(room));
            roomDiv.append(getGap(20));
            const messages = room.messages;
            for (let i = 0; i < messages.length; i++) {
                const messageParagraph = getMessage(messages[i]);
                if (i % 2 === 0) {
                    messageParagraph.className = "text-end";
                } else {
                    messageParagraph.className = "text-start";
                }
                roomDiv.append(messageParagraph);
                roomDiv.append(getGap(10));
            }
            let arrayButtons = document.getElementsByClassName('deleteMessage');
            for (let button of arrayButtons) {
                button.addEventListener('click', function (){
                    deleteMessage(button.name)
                }, false);
            }
        }
    }
}

drawRoom();
setInterval(drawRoom, 30000);