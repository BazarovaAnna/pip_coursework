<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <link rel="stylesheet" href="../../../resources/css/header.css"/>
    </head>
    <body>
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <h1><a href="/" class="logo">D&<span>D</span></a></h1>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-left" style="margin: 5px;">
                        <li><a href="/">Главная страница</a></li>
                        <li><a href="/user">Личный кабинет</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <sec:authorize access="!isAuthenticated()">
                            <li><a href="/login" class="ref-login">Войти</a></li>
                            <li><a href="/#" class="ref-signup">Регистрация</a></li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="/logout" class="ref-logout">Выйти</a></li>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
