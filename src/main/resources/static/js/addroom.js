function validate(name) {
    let alertMessage = "";
    if (name === "") {
        alertMessage += "Необходимо указать название комнаты\n";
    }
    if (alertMessage !== "") {
        alert(alertMessage);
        return false;
    }
    return true;
}

function handleSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const name = data.get('name');
    if (!validate(name)) {
        return;
    }
    const room = {
        name: name
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 201) {
                document.getElementById('successTab').hidden = false;
                document.getElementById('errorTab').hidden = true;
                drawRooms();
            } else {
                document.getElementById('errorTab').hidden = false;
                document.getElementById('successTab').hidden = true;
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