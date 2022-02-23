function editMessage(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const content = data.get('content');
    const message = {
        content: content
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        const errorTab = document.getElementById('errorTab');
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 200) {
                window.location.href = '/showroom?room=' + roomId;
            } else if (xmlHttpRequest.status === 400){
                const response =  JSON.parse(xmlHttpRequest.responseText);
                errorTab.hidden = false;
                errorTab = processError(response, errorTab);
            }
        }
    }
    xmlHttpRequest.open( 'PATCH', 'http://localhost:8080/message/' + messageId );
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem('token'));
    xmlHttpRequest.send(JSON.stringify(message));
}

drawLeaveMessage();
const messageId = params.get('message');
const editMessageForm = document.getElementById('editMessageForm');
editMessageForm.addEventListener('submit', editMessage);