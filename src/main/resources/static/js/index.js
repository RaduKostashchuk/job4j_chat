function showRoomDeleted() {
    const params = new URLSearchParams(document.location.search);
    const isRoomDeleted = params.get("roomDeleted");
    const successDiv = document.getElementById("successTab");

    if (isRoomDeleted === 'true') {
        successDiv.hidden = false;
    }
}

showRoomDeleted();