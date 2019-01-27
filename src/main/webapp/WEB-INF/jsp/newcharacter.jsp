<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Персонажи</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/newcharacter.css"/>
    <link rel="stylesheet" href="../../resources/css/herodashboard.css"/>
    <link rel="stylesheet" href="../../resources/css/photo.css"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="templates/header.jsp"/>

<div class="wrapper">
    <div class="content">
        <div id="modal-window" hidden></div>
        <div class="container-fluid">
            <div class="row center-content">
                <div class="col-sm-1 col-md-1"></div>
                <div class="col-sm-10 col-md-10">
                    <div class="row">
                        <!-- Ссылка на создателя -->
                        <!-- https://twitter.com/NikolayTalanov -->
                        <div class="demo-cont">
                            <!-- slider start -->
                            <div class="fnc-slider example-slider m--global-blending-active">
                                <div class="fnc-slider__slides">
                                    <!-- slide start -->
                                    <div class="fnc-slide m--blend-green m--active-slide">
                                        <div class="fnc-slide__inner">
                                            <div class="fnc-slide__mask">
                                                <div class="fnc-slide__mask-inner"></div>
                                            </div>
                                            <div class="fnc-slide__content">
                                                <h2 class="fnc-slide__heading">
                                                    <div class="fnc-slide__heading-line">
                                                        <span>Black</span>
                                                    </div>
                                                    <div class="fnc-slide__heading-line">
                                                        <span>Widow</span>
                                                    </div>
                                                </h2>
                                                <button type="button" class="fnc-slide__action-btn">
                                                    Credits
                                                    <span data-text="Credits">Credits</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- slide end -->
                                    <!-- slide start -->
                                    <div class="fnc-slide m--blend-dark">
                                        <div class="fnc-slide__inner">
                                            <div class="fnc-slide__mask">
                                                <div class="fnc-slide__mask-inner"></div>
                                            </div>
                                            <div class="fnc-slide__content">
                                                <h2 class="fnc-slide__heading">
                                                    <div class="fnc-slide__heading-line">
                                                        <span>Captain</span>
                                                    </div>
                                                    <div class="fnc-slide__heading-line">
                                                        <span>America</span>
                                                    </div>
                                                </h2>
                                                <button type="button" class="fnc-slide__action-btn">
                                                    Credits
                                                    <span data-text="Credits">Credits</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- slide end -->
                                    <!-- slide start -->
                                    <div class="fnc-slide m--blend-red">
                                        <div class="fnc-slide__inner">
                                            <div class="fnc-slide__mask">
                                                <div class="fnc-slide__mask-inner"></div>
                                            </div>
                                            <div class="fnc-slide__content">
                                                <h2 class="fnc-slide__heading">
                                                    <div class="fnc-slide__heading-line">
                                                        <span>Iron</span>
                                                    </div>
                                                    <div class="fnc-slide__heading-line">
                                                        <span>Man</span>
                                                    </div>
                                                </h2>
                                                <button type="button" class="fnc-slide__action-btn">
                                                    Credits
                                                    <span data-text="Credits">Credits</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- slide end -->
                                    <!-- slide start -->
                                    <div class="fnc-slide m--blend-blue">
                                        <div class="fnc-slide__inner">
                                            <div class="fnc-slide__mask">
                                                <div class="fnc-slide__mask-inner"></div>
                                            </div>
                                            <div class="fnc-slide__content">
                                                <h2 class="fnc-slide__heading">
                                                    <div class="fnc-slide__heading-line">
                                                        <span>Thor</span>
                                                    </div>
                                                    <div class="fnc-slide__heading-line">
                                                        <span>Just Thor</span>
                                                    </div>
                                                </h2>
                                                <button type="button" class="fnc-slide__action-btn">
                                                    Credits
                                                    <span data-text="Credits">Credits</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- slide end -->
                                </div>
                                <nav class="fnc-nav">
                                    <div class="fnc-nav__bgs">
                                        <div class="fnc-nav__bg m--navbg-green m--active-nav-bg"></div>
                                        <div class="fnc-nav__bg m--navbg-dark"></div>
                                        <div class="fnc-nav__bg m--navbg-red"></div>
                                        <div class="fnc-nav__bg m--navbg-blue"></div>
                                    </div>
                                    <div class="fnc-nav__controls">
                                        <button class="fnc-nav__control">
                                            Black Widow
                                            <span class="fnc-nav__control-progress"></span>
                                        </button>
                                        <button class="fnc-nav__control">
                                            Captain America
                                            <span class="fnc-nav__control-progress"></span>
                                        </button>
                                        <button class="fnc-nav__control">
                                            Iron Man
                                            <span class="fnc-nav__control-progress"></span>
                                        </button>
                                        <button class="fnc-nav__control">
                                            Thor
                                            <span class="fnc-nav__control-progress"></span>
                                        </button>
                                    </div>
                                </nav>
                            </div>
                            <!-- slider end -->
                            <!-- Панель дополнительной информации о персонаже -->
                            <div class="row main-form demo-cont__credits">
                                <div class="info-close"></div>
                                <h2>О персонаже</h2>
                                <form id="new-character-form">
                                    <div class="form-group">
                                        <label for="name" class="cols-sm-2 control-label">Имя</label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <input type="text" class="form-control" name="name" minlength="2" maxlength="12"
                                                       id="name" placeholder="Введите имя персонажа" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="userClass" class="cols-sm-2 control-label">Класс</label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <input type="text" class="form-control" name="userClass" minlength="2" maxlength="12"
                                                       id="userClass" placeholder="Введите класс персонажа" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="sex" class="cols-sm-2 control-label">Пол</label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-users" aria-hidden="true"></i></span>
                                                <label class="container-for-radio">Мужской
                                                    <input type="radio" id="sex" name="sex" value="m" required/>
                                                    <span class="checkmark"></span>
                                                </label>
                                                <label class="container-for-radio">Женский
                                                    <input type="radio" id="sex" name="sex" value="f"/>
                                                    <span class="checkmark"></span>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <sec:csrfInput />
                                    <select id="race-dropdown" name="type" class="race-select" required>
                                        <c:forEach var="name" items="${races}">
                                            <option name="type" value="${name}">${name}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="photobox photobox">
                                        <div class="photobox__previewbox">
                                            <c:set var="avatar">${avatar}</c:set>
                                            <c:if test="${empty avatar}">
                                                <img src="../../resources/default/img/man.png" class="photobox__preview img-thumbnail" id="character-avatar" alt="Preview">
                                            </c:if>
                                            <c:if test="${!empty avatar}">
                                                <img src="${avatar}" class="photobox__preview img-thumbnail" id="character-avatar" alt="Preview">
                                            </c:if>
                                            <span class="photobox__label">Выбрать фото</span>
                                        </div>
                                    </div>
                                    <input id="character-img" type="hidden" name="imgPath" value="../../resources/default/img/man.png">

                                    <div class="form-group ">
                                        <input id="submit-btn" class="btn btn-lg btn-primary btn-bl-ock"
                                               type="button" value="Создать">
                                    </div>
                                </form>
                                <div id="msg-box"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-1 col-md-1"></div>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="templates/footer.jsp"/>
    </div>

</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../resources/js/newcharacter.js"></script>
<script type="text/javascript" src="../../resources/js/herodashboard.js"></script>
</body>
</html>
