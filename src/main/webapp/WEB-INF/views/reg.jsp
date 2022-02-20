<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Регистрация</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>
    <script src="js/reg.js" defer></script>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="col-4 offset-4">
            <div class="card">
                <div class="card-header">
                    <p class="h5">Регистрация</p>
                </div>
                <div class="card-body">
                    <form id="regForm" method="post">
                        <div id="errorTab" class="alert alert-warning" role="alert" hidden>
                            "Пользователь с таким именем уже существует"
                        </div>
                        <div class="form-group m-1">
                            <label>Имя</label>
                            <input type="text" class="form-control" id="nameInput" name="name">
                        </div>
                        <div class="form-group m-1">
                            <label>Пароль</label>
                            <input type="password" class="form-control" id="passwordInput" name="password">
                        </div>
                        <div class="form-group m-1">
                            <label>Подтверждение пароля</label>
                            <input type="password" class="form-control" id="confirmInput" name="confirm" >
                        </div>
                        <button type="submit" class="btn btn-primary m-1">Отправить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>