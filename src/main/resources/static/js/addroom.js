function handleSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const name = data.get('name');
    const room = {
        name: name
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        let errorTab = document.getElementById('errorTab');
        const successTab = document.getElementById('successTab');
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 201) {
                successTab.hidden = false;
                errorTab.hidden = true;
                drawRooms();
            } else if (xmlHttpRequest.status === 400){
                const response =  JSON.parse(xmlHttpRequest.responseText);
                errorTab = processError(response, errorTab);
                errorTab.hidden = false;
                successTab.hidden = true;
            }
        }
    }
    xmlHttpRequest.open( 'POST', 'http://localhost:8080/room/' );
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem('token'));
    xmlHttpRequest.send(JSON.stringify(room));
}

const form = document.getElementById('addRoomForm');
form.addEventListener('submit', handleSubmit);