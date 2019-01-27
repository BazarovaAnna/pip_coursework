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
    <script type="text/javascript" src="../../resources/js/newcharacter.js"></script>
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
                            <div class="fnc-slider example-slider m--global-blending-active" id="character-slider"></div>
                            <!-- slider end -->
                            <!-- Панель дополнительной информации о персонаже -->
                            <div class="row main-form demo-cont__credits">
                                <div class="info-close"></div>
                                <div id="create-character" hidden>
                                    <h3>Создать персонажа</h3>
                                    <form id="new-character-form">
                                        <div class="photobox photobox">
                                            <div class="photobox__previewbox" id="character-avatar-box">
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
                                        <div class="form-group">
                                            <label for="name" class="cols-sm-2 control-label">Имя</label>
                                            <div class="cols-sm-10">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                    <input type="text" class="form-control field-value" name="name" minlength="2" maxlength="12"
                                                           id="name" placeholder="Введите имя" required/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="userClass" class="cols-sm-2 control-label">Класс</label>
                                            <div class="cols-sm-10">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                    <input type="text" class="form-control field-value" name="userClass" minlength="2" maxlength="12"
                                                           id="userClass" placeholder="Введите класс" required/>
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
                                        <input id="character-img" type="hidden" name="imgPath" value="../../resources/default/img/man.png">

                                        <div class="form-group ">
                                            <input id="submit-btn" class="btn btn-lg btn-primary btn-bl-ock"
                                                   type="button" value="Создать">
                                        </div>
                                    </form>
                                    <div id="msg-box"></div>
                                </div>
                                <div id="info-character" hidden>
                                    <h2>О персонаже</h2>
                                    <div class="photobox photobox">
                                        <div class="photobox__previewbox">
                                            <img src="../../resources/default/img/man.png" class="photobox__preview img-thumbnail" id="avatar" alt="Preview">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="cols-sm-2 control-label">Имя</label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <label class="form-control field-value" id="char-name"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="cols-sm-2 control-label">Класс</label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                    <label class="form-control field-value" id="char-class"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="cols-sm-2 control-label">Раса</label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <label class="form-control field-value" id="char-race"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="cols-sm-2 control-label">Пол</label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <label class="form-control field-value" id="char-sex"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
</body>
</html>
