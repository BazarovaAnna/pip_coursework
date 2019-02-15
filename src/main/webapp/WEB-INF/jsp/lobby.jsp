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
                            <div class="photobox">
                                <div class="photobox__previewbox" id="player-mode">
                                    <img src="../../resources/default/img/gamermode.jpg" class="photobox__preview img-thumbnail" alt="Preview">
                                    <span class="photobox__label">Быть игроком</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="photobox">
                                <div class="photobox__previewbox" id="gm-mode">
                                    <img src="../../resources/default/img/gmmode.jpg" class="photobox__preview img-thumbnail" alt="Preview">
                                    <span class="photobox__label">Быть мастером</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!-- Версия лобби для игрока -->
                        <div id="player-form" hidden>
                            <h1 class="character-list-header">Буду игроком!</h1>
                            <h2 class="character-list-header">Список доступных персонажей</h2>
                            <div id="character-list"></div>
                            <div class="row" id="player-form-2" hidden>
                                <div class="col-md-6">
                                    <div class="info-form">
                                        <h2 id="char-name" class="header-form"></h2>
                                        <div class="photobox">
                                            <div class="photobox__previewbox">
                                                <img class="character-preview img-thumbnail" id="char-avatar" alt="Preview">
                                            </div>
                                        </div>
                                        <div class="character-params">
                                            <div class="character-player-form">
                                                <label class="header-character-param" style="margin-right: 20%;">Статус :</label>
                                                <label id="char-condition" class="character-param"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label class="header-character-param" style="margin-right: 20%;">Уровень :</label>
                                                <label id="char-level" class="character-param"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label class="header-character-param" style="margin-right: 20%;">Раса :</label>
                                                <label id="char-race" class="character-param"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label class="header-character-param" style="margin-right: 20%;">Класс :</label>
                                                <label id="char-class" class="character-param"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label class="header-character-param" style="margin-right: 20%;">Пол :</label>
                                                <label id="char-sex" class="character-param"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label class="header-character-param" style="margin-right: 20%;">В кармане :</label>
                                                <label id="char-money" class="character-param"></label>
                                            </div>
                                            <div class="character-player-form">
                                                <label class="header-character-param" style="margin-right: 20%;">Могу унести</label>
                                                <label class="character-param"><span id="char-maxWeight"></span> кг</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="game-info" style="position: relative">
                                        <div id="player-dukat" class="dukat dragElement"></div>
                                    </div>
                                </div>
                            </div>
                            </div>
                        </div>
                        <!-- Версия лобби для мастера -->
                        <div id="gm-form" hidden>
                            <h1 class="character-list-header">Буду мастером!</h1>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="info-form">
                                        <h2 class="header-form" id="game-form-header"></h2>
                                        <div id="create-new-game-form" hidden>
                                            <form id="new-game-form">
                                                <div class="form-group">
                                                    <h4 class="header-form">Название игры</h4>
                                                    <div>
                                                        <input type="text" name="name" minlength="2" maxlength="12"
                                                               id="name" placeholder="Введите название игры" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <h4 class="header-form">Количество игроков</h4>
                                                    <div>
                                                        <input type="text" name="number" minlength="2" maxlength="12"
                                                               id="number" placeholder="Введите название игры" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <h4 class="header-form">Пароль</h4>
                                                    <div>
                                                        <input type="text" name="password" minlength="2" maxlength="12"
                                                               id="password" placeholder="Введите пароль">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <h4 class="header-form">Описание</h4>
                                                    <div>
                                                        <input type="text" name="description" minlength="2" maxlength="12"
                                                               id="description" placeholder="Введите название игры">
                                                    </div>
                                                </div>
                                                <input id="submit-btn" class="btn btn-lg btn-primary btn-block"
                                                       type="button" value="Создать">
                                            </form>
                                        </div>
                                        <div id="game-info" hidden></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="game-info">
                                        <div id="gm-games-slider" class="games-slider"></div>
                                        <div id="gm-dukat" class="dukat dragElement"></div>
                                    </div>
                                </div>
                            </div>
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
    <script src="webjars/jquery-ui/1.12.1/jquery-ui.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../resources/js/lobby.js"></script>
</body>
</html>
