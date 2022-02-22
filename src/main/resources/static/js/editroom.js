function handleEditSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const name = data.get('name');
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
            } else if (xmlHttpRequest.status === 400) {
                const response =  JSON.parse(xmlHttpRequest.responseText);
                const errorEditTab = document.getElementById('errorEditTab');
                errorEditTab.innerHTML = '<p>' + response.message + '<br>' + response.details + '</p>';
                errorEditTab.hidden = false;
            }
        }
    }
    xmlHttpRequest.open( 'PATCH', 'http://localhost:8080/room/name/' );
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
            } else if (xmlHttpRequest.status === 400) {
                const response =  JSON.parse(xmlHttpRequest.responseText);
                const errorDeleteTab = document.getElementById('errorEditTab');
                errorDeleteTab.innerHTML = '<p>' + response.message + '<br>' + response.details + '</p>';
                errorDeleteTab.hidden = false;
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