function customizeNavbar() {
    const user = localStorage.getItem('user');
    const createRoomLi = document.getElementById('createRoomLi');
    const loginLi = document.getElementById('loginLi');
    const logoutLi = document.getElementById('logoutLi');
    const logoutA = document.getElementById('logoutA');

    if (user != null) {
        createRoomLi.hidden = false;
        logoutLi.hidden = false;
        loginLi.hidden = true;
        logoutA.innerText = user + '  | Выйти';
    } else {
        createRoomLi.hidden = true;
        logoutLi.hidden = true;
        loginLi.hidden = false;
        logoutA.innerText = '';
    }
}

function logout() {
    localStorage.clear();
    customizeNavbar();
}

customizeNavbar();