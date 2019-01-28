<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<html>
<head>
    <title>Лобби</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/lobby.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="wrapper">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-sm-12 col-md-8">
                    <h1 class="character-list-header">Выбери роль!</h1>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="photobox photobox">
                                <div class="photobox__previewbox" id="gamemode">
                                    <img src="../../resources/default/img/gamermode.jpg" class="photobox__preview img-thumbnail" alt="Preview">
                                    <span class="photobox__label">Быть игроком</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="photobox photobox">
                                <div class="photobox__previewbox" id="gamemmode">
                                    <img src="../../resources/default/img/gmmode.jpg" class="photobox__preview img-thumbnail" alt="Preview">
                                    <span class="photobox__label">Быть мастером</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div id="player-form" hidden>
                            <h1 class="character-list-header">Буду игроком!</h1>
                            <h2 class="character-list-header">Список доступных персонажей</h2>
                            <div id="character-list"></div>
                            <div id="player-form-2" hidden>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="character-info">
                                            <h2 id="char-name"></h2>
                                            <div class="photobox">
                                                <div class="photobox__previewbox">
                                                    <img class="character-preview img-thumbnail" id="char-avatar" alt="Preview">
                                                </div>
                                            </div>
                                            <div class="character-player-form">
                                                <label id="char-condition"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label id="char-level"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label id="char-race"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label id="char-class"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label id="char-sex"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label id="char-money"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label id="char-maxWeight"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        Тестовый набор игр
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                        <div id="gm-form" hidden>
                            <h1 class="character-list-header">Буду мастером!</h1>
                        </div>
                        <a href="/gamefield">Тестовый вариант поля</a>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    <div class="footer">
        <jsp:include page="templates/footer.jsp"/>
    </div>
</div>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../resources/js/lobby.js"></script>
</body>
</html>
