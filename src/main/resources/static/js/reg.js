function handleSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const name = data.get('name');
    const password = data.get('password');
    const confirm = data.get('confirm');
    const user = {
        name: name,
        password: password,
        confirm: confirm
    };
    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (xmlHttpRequest.status === 400) {
                const response =  JSON.parse(xmlHttpRequest.responseText);
                const errorTab = document.getElementById('errorTab');
                errorTab.hidden = false;
                errorTab = processError(response, errorTab);
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