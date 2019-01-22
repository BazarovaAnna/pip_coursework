<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<html>
<head>
    <title>Главная страница</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/index.css"/>
</head>
<body>
    <jsp:include page="templates/header.jsp"/>
    <div class="container">
        <div class="row center-content">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <h2>
                    Доброго времени суток, дорогой путник!<br>
                    Мы рады приветствовать вас на нашем<br>
                    замечательном ресурсе.<br>
                    Надеемся, что наш проект скрасит вам день,<br>
                    подарит новые впечатления и друзей!<br>
                    Ниже вы можете ознакомиться с правилами<br>
                    Спасибо!:)<br>
                </h2>
                <br>
                <h2><a href="/">${rules}</a></h2>
            </div>
            <div class="col-sm-2"></div>
        </div>
        <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </div>
    <jsp:include page="templates/footer.jsp"/>
</body>
</html>
