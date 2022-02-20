function validate(name, password) {
    let alertMessage = "";
    if (name === "") {
        alertMessage = "Необходимо указать имя пользователя\n";
    }
    if (password === "") {
        alertMessage += "Необходимо указать пароль";
    }
    if (alertMessage !== "") {
        alert(alertMessage);
        return false;
    }
    return true;
}

function handleLoginSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const name = data.get('username');
    const password = data.get('password');
    if (!validate(name, password)) {
        return;
    }
    const user = {
        name: name,
        password: password
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 200) {
                localStorage.setItem('user', name);
                localStorage.setItem('token', xmlHttpRequest.getResponseHeader('Authorization'));
                window.location.href = '/';
            } else  {
                document.getElementById('errorLoginTab').hidden = false;
            }
        }
    }
    xmlHttpRequest.open( 'POST', 'http://localhost:8080/login' );
    xmlHttpRequest.setRequestHeader('Content-Type', 'text/plain');
    xmlHttpRequest.send(JSON.stringify(user));
}

const form = document.getElementById('loginForm');
form.addEventListener('submit', handleLoginSubmit);