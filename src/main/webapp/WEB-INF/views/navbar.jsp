<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light m-1">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Добро пожаловать в наш чат</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">Главная</a>
                </li>
                    <li id="createRoomLi" class="nav-item">
                        <a class="nav-link" href="/addroom">Создать комнату</a>
                    </li>
                    <li id="loginLi" class="nav-item">
                        <a class="nav-link" href="/login">Войти</a>
                    </li>
                    <li id="logoutLi" class="nav-item">
                        <a id="logoutA" class="nav-link" href="#" onclick="return logout()"></a>
                    </li>
            </ul>
        </div>
    </div>
</nav>