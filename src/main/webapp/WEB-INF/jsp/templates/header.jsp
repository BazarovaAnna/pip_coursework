<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/header.css"/>
</head>
<body>
<div class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <h1 style="margin-top:10px"><a href="/" class="logo">D&<span>D</span></a></h1>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-left" style="margin: 5px;">
                <li><a href="/" style="margin-top:5px;">Главная страница</a></li>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/lobby" style="margin-top:5px;">Лобби</a></li>
                    <li><a href="/newcharacter" style="margin-top:5px;">Персонажи</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="/login" style="margin-top:10px;">Войти</a></li>
                    <li><a href="/registration" style="margin-top:10px;">Регистрация</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/user" style="margin-top:10px;">${login}</a></li>
                    <li>
                        <form action="/logout" method="post" style="margin-top:8px;">
                            <input class="btn btn-danger" style="margin: 10px" type="submit" value="Выйти">
                            <sec:csrfInput/>
                        </form>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</div>
</body>
</html>