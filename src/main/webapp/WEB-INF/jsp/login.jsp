<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Логин</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row center-content">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <form class="form-signin" method="post" action="/login">
                <h3 class="form-signin-heading">Пожалуйста, авторизируйтесь</h3>
                <div class="alert alert-danger" role="alert">Неверные учетные данные пользователя</div>
                <p>
                    <label for="username" class="sr-only">Логин</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Логин" required autofocus>
                </p>
                <p>
                    <label for="password" class="sr-only">Пароль</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Пароль" required>
                </p>
                <sec:csrfInput />
                <button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
            </form>
        </div>
        <div class="col-sm-4"></div>
    </div>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
