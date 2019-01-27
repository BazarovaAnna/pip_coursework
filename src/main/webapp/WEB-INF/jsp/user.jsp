<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/user.css"/>
    <link rel="stylesheet" href="../../resources/css/photo.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <jsp:include page="templates/header.jsp"/>
        <div class="container-fluid">
            <div class="row center-content">
                <div class="col-sm-1 col-md-1"></div>
                <div class="col-sm-10 col-md-10">
                    <div class="row">
                        <div class="col-sm-4 col-md-4 left-col">
                            <form hidden method="POST" enctype="multipart/form-data" id="fileUploadForm">
                                <input type="file" name="file" id="file-uploader" accept="image/*"
                                           onchange="handleFiles()">
                            </form>

                            <div class="photobox photobox">
                                <div class="photobox__previewbox">
                                    <img src="${filename}" class="photobox__preview img-thumbnail" id="user-avatar" alt="Preview">
                                    <span class="photobox__label">Сменить фото</span>
                                </div>
                            </div>
                            <div style="margin: 20px;">
                                <h1>Пол:
                                    <c:set var="sex">${sex}</c:set>
                                    <c:choose>
                                        <c:when test="${sex eq 'm'}">
                                            <i class="fa fa-mars-stroke" aria-hidden="true"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa fa-venus" aria-hidden="true"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </h1>
                            </div>
                        </div>
                        <div class="col-sm-3 col-md-3 middle-col">
                            <div style="margin: 20px;">
                                <label>
                                    <span class="user-params-label">Логин: </span>
                                    <span class="user-params-value">${login}</span>
                                </label>
                            </div>
                            <div style="margin: 20px;">
                                <label>
                                    <span class="user-params-label">Пароль: </span>
                                    <span class="user-params-value">${password}</span>
                                </label>
                            </div>
                            <div style="margin: 20px;">
                                <label>
                                    <span class="user-params-label">Email: </span>
                                    <span class="user-params-value">${email}</span>
                                </label>
                            </div>
                        </div>
                        <div class="col-sm-5 col-md-5 right-col">

                        </div>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
            <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        </div>

    <script type="text/javascript" src="../../resources/js/user.js"></script>
    <jsp:include page="templates/footer.jsp"/>
</body>
</html>
