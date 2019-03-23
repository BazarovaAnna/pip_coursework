<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Вход в систему</title>
    <link href="../webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/formstyle.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="content_wrap" id="center-content">
    <div class="row main-form">
        <h3>Форма для восстановления пароля</h3>
        <form method="post" action="/changepassword">
            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label">Введите новый пароль</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="password"
                               id="password" placeholder="Введите пароль" required/>
                    </div>
                </div>
            </div>
            <input type="hidden" id="activationCode" name="activationCode" value="${code}">

            <sec:csrfInput />
            <div class="form-group ">
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Изменить пароль">
            </div>
        </form>
        <%if(request.getAttribute("message") != null){%>
        <div class="alert alert-msg">
            <label class="success">${message}</label>
        </div>
        <%}%>
        <%if(request.getAttribute("error") != null){%>
        <div class="alert alert-msg">
            <h4 id="error-msg">${error}</h4>
        </div>
        <%}%>
    </div>
    <script src="../webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="../webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
