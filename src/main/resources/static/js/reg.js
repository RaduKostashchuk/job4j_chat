function validate(name, password, confirm) {
    let alertMessage = "";
    if (name === "") {
        alertMessage += "Необходимо указать имя пользователя\n";
    }
    if (password === "") {
        alertMessage += "Необходимо указать пароль\n";
    }
    if (password !== confirm) {
        alertMessage += "Пароли не совпадают";
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
    const password = data.get('password');
    const confirm = data.get('confirm');
    if (!validate(name, password, confirm)) {
        return;
    }
    const user = {
        name: name,
        password: password
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 409) {
                document.getElementById('errorTab').hidden = false;
            } else if (xmlHttpRequest.status === 201) {
                window.location.href = 'login?reg=true';
            }
        }
    }
    xmlHttpRequest.open( 'POST', 'http://localhost:8080/person/reg/' );
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xmlHttpRequest.send(JSON.stringify(user));
}

const form = document.getElementById('regForm');
form.addEventListener('submit', handleSubmit);