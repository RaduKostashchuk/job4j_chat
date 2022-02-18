function validate() {
    const email = $('#usernameInput').val();
    const password = $('#passwordInput').val();
    let alertMessage = "";
    if (email === "") {
        alertMessage = "Необходимо указать email\n";
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