function handleMessageSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    let text = data.get('messageText');
    text = text.replace(/\r?\n|\r/g, '<br>');
    const message = {
        content: text
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 201) {
                const textArea = document.getElementById('messageText');
                textArea.value = '';
                drawRoom();
            } else if (xmlHttpRequest.status === 400) {
                const response =  JSON.parse(xmlHttpRequest.responseText);
                let errorTab = document.getElementById('errorLeaveMessageTab');
                errorTab.hidden = false;
                errorTab = processError(response, errorTab);
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