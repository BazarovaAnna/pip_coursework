<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/index.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <jsp:include page="templates/header.jsp"/>
        <div class="container">
            <div class="row center-content">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <h2>
                        Страница пользователя
                        <a href="/change">Поменять пароль</a>
                        <%if(request.getParameter("success") != null) { %>
                            <%if(request.getParameter("success").equals("true")) { %>
                                <div class="form-group" style="text-align:center">
                                    <label>Пароль был успешно изменен!</label>
                                </div>
                            <%} else {%>
                        <div class="form-group" style="text-align:center">
                            <label>Что-то пошло не так!</label>
                        </div>
                            <%}%>
                        <%}%>
                    </h2>
                    <br>
                    <form action="/uploadfile" method="post" enctype="multipart/form-data">
                        <input type="file" name="file">
                        <sec:csrfInput />
                        <input class="btn btn-info" type="submit" value="Изменить">
                    </form>
                    <div>
                        <img src="../../resources/img/${filename}"/>
                    </div>
                </div>
                <div class="col-sm-2"></div>
            </div>
            <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
            <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        </div>
    <jsp:include page="templates/footer.jsp"/>
</body>
</html>
