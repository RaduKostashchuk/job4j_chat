function drawRooms() {
    const roomList = document.getElementById('rooms');
    while (roomList.firstChild) {
        roomList.removeChild(roomList.firstChild);
    }
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open( 'GET', 'http://localhost:8080/room/' );
    xmlHttpRequest.send();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            const rooms =  JSON.parse(xmlHttpRequest.responseText);
            for (let i = 0; i < rooms.length; i++) {
                let tr = document.createElement('tr');
                let td = document.createElement('td');
                let a = document.createElement('a');
                let p = document.createElement('p');
                td.className = 'text-center';
                a.href = 'http://localhost:8080/showroom?room=' + rooms[i].id;
                if (rooms[i].id == roomId) {
                    a.className = 'btn btn-primary';
                } else {
                    a.className = 'btn btn-outline-primary';
                }
                p.innerText = rooms[i].name;
                p.className = 'text-center m-1';
                a.append(p);
                td.append(a);
                tr.append(td);
                roomList.append(tr);
            }
        }
    }
}

function isLogged() {
    const user = localStorage.getItem('user');
    return user !== null;
}

function drawLeaveMessage() {
    if (isLogged()) {
        document.getElementById('leaveMessageDiv').hidden = false;
    }
}

function processError(response, errorTab) {
    for (let element of response) {
        errorTab.innerHTML += '<p>' + element.message + '</p>';
        if (element.details !== undefined) {
            errorTab.innerHTML += '<p>' + element.details + '</p>';
        }
    }
    return errorTab;
}

const params = new URLSearchParams(document.location.search);
const roomId = params.get('room');