function validateMessage(text) {
    let alertMessage = "";
    if (text === "") {
        alertMessage += "Сообщение не может быть пустым\n";
    }
    if (alertMessage !== "") {
        alert(alertMessage);
        return false;
    }
    return true;
}

function handleMessageSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    let text = data.get('messageText');
    text = text.replace(/\r?\n|\r/g, '<br>');
    if (!validateMessage(text)) {
        return;
    }
    const message = {
        content: text
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 200) {
                const textArea = document.getElementById('messageText');
                textArea.value = '';
                drawRoom();
            } else {
                document.getElementById('errorLeaveMessageTab').hidden = false;
            }
        }
    }
    xmlHttpRequest.open( 'POST', 'http://localhost:8080/message/' + roomId );
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem('token'));
    xmlHttpRequest.send(JSON.stringify(message));
}

const messageForm = document.getElementById('leaveMessageForm');
messageForm.addEventListener('submit', handleMessageSubmit);