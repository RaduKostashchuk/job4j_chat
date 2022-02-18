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
            if (xmlHttpRequest.status === 409) {
                document.getElementById('errorEditTab').hidden = false;
            } else if (xmlHttpRequest.status === 200) {
                document.getElementById('successEditTab').hidden = false;
                drawRooms();
                drawRoom();
            }
        }
    }
    xmlHttpRequest.open( 'PUT', 'http://localhost:8080/room/' );
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xmlHttpRequest.send(JSON.stringify(room));
}

function handleDelete() {
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 409) {
                document.getElementById('errorDeleteTab').hidden = false;
            } else if (xmlHttpRequest.status === 200) {
                window.location.href = '/index?roomDeleted=true';
            }
        }
    }
    xmlHttpRequest.open( 'DELETE', 'http://localhost:8080/room/' +  roomId);
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