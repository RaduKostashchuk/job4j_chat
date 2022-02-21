function handleLoginSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const name = data.get('username');
    const password = data.get('password');
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