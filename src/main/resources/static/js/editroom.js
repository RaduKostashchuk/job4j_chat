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

function handleEditSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const name = data.get('name');
    if (!validate(name)) {
        return;
    }
    const room = {
        id: roomId,
        name: name
     };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 200) {
                document.getElementById('successEditTab').hidden = false;
                drawRooms();
                drawRoom();
            } else {
                document.getElementById('errorEditTab').hidden = false;
            }
        }
    }
    xmlHttpRequest.open( 'PUT', 'http://localhost:8080/room/' );
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem('token'));
    xmlHttpRequest.send(JSON.stringify(room));
}

function handleDelete() {
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 200) {
                window.location.href = '/index?roomDeleted=true';
            } else {
                document.getElementById('errorDeleteTab').hidden = false;
            }
        }
    }
    xmlHttpRequest.open( 'DELETE', 'http://localhost:8080/room/' +  roomId);
    xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem('token'));
    xmlHttpRequest.send();
}

function showEditDiv() {
    let editDiv = document.getElementById('editRoomDiv');
    if (editDiv.hidden === true) {
        editDiv.hidden = false;
    } else {
        editDiv.hidden = true;
    }
}

const deleteButton = document.getElementById('deleteRoomButton');
deleteButton.addEventListener('click', handleDelete);

const editButton = document.getElementById('editRoomButton');
editButton.addEventListener('click', showEditDiv);

const form = document.getElementById('editRoomForm');
form.addEventListener('submit', handleEditSubmit);