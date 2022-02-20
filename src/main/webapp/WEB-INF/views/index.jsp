<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Главная</title>
    <link href="css/sidebar.css" rel="stylesheet" />
    <link href="css/content.css" rel="stylesheet" />
    <link href="css/message.css" rel="stylesheet" />
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
    <script src="js/index.js" defer></script>
    <script src="js/navbar.js" defer></script>
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
            <div class="content">
                <div style="height: 20px"></div>
                <p class="h6 text-center m-2">
                    <span class="p-3 border border-2 rounded-3 message">
                        Выберите существующую комнату или создайте новую
                    </span>
                </p>
                <div style="height: 20px"></div>
            </div>
            <div id="successTab" class="alert alert-info" role="alert" hidden>
                "Комната успешно удалена"
            </div>
        </div>
    </div>
</div>
</body>
</html>