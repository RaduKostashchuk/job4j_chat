<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование сообщения</title>
    <link href="css/message.css" rel="stylesheet" />
    <link href="css/sidebar.css" rel="stylesheet" />
    <link href="css/content.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>
    <script src="js/lib.js"></script>
    <script src="js/sidebar.js" defer></script>
    <script src="js/navbar.js" defer></script>
    <script src="js/editmessage.js" defer></script>
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="row">
        <div class="col-2">
            <div class="sidebar">
                <table class="table table-borderless">
                    <thead>
                    <tr>
                        <th class="text-center">
                            Комнаты
                        </th>
                    </tr>
                    </thead>
                    <tbody id="rooms">
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-10">
            <div id="content" class="content">
                <div id="errorTab" class="alert alert-warning" role="alert" hidden>
                    "Ошибка редактирования сообщения"
                </div>
                <div style="height: 20px"></div>
                <p class="h6 text-center m-2">
                        <span class="p-3 border border-2 rounded-3 message">
                            Редактирование сообщения
                        </span>
                </p>
                <div style="height: 20px"></div>
                <div id="leaveMessageDiv" class="row" hidden>
                    <form id="editMessageForm">
                        <div class="col-10 offset-2">
                            <textarea id="messageText" class="m-1 p-1" placeholder="Новое сообщение" name="content" rows="2" cols="100" style="float:right"></textarea>
                        </div>
                        <div class="col-1 offset-11">
                            <button id="leaveMessageButton" type="submit" class="btn btn-primary m-1" title="Отправить сообщение" style="float:right">
                                <i class="bi-send-fill"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>